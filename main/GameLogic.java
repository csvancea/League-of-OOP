package main;

import abilities.IAbility;
import entities.angels.BasicAngel;
import fileio.implementations.FileWriter;
import entities.heroes.BasicHero;
import entities.heroes.strategies.BasicStrategy;
import entities.heroes.strategies.StrategyFactory;
import map.GameMap;
import entities.IEntity;
import entities.EntityType;
import observers.HeroObserver;
import observers.AngelObserver;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

public final class GameLogic {
    private final GameMap gameMap;
    private final List<BasicHero> heroesList;
    private final List<List<Character>> roundMoves;
    private final List<List<BasicAngel>> roundAngels;

    private final FileWriter fileWriter;
    private final AngelObserver angelObserver;
    private final HeroObserver heroObserver;

    public GameLogic(final IGameLoader gameLoader, final FileWriter fileWriter) {
        gameMap = gameLoader.getGameMap();
        heroesList = gameLoader.getHeroesList();
        roundMoves = gameLoader.getRoundMoves();
        roundAngels = gameLoader.getRoundAngels();

        this.fileWriter = fileWriter;
        this.angelObserver = new AngelObserver(fileWriter);
        this.heroObserver = new HeroObserver(fileWriter);

        addObservers();
    }

    public void play() throws IOException {
        int roundIdx = 0;
        for (List<Character> round : roundMoves) {
            fileWriter.writeWord(String.format("~~ Round %d ~~", roundIdx + 1));
            fileWriter.writeNewLine();

            applyPassivePenalties();
            doHeroMoves(round);

            List<BasicHero> aliveHeroes = getAliveHeroesList();

            applyStrategies(aliveHeroes);
            attackEachOthers(aliveHeroes);
            applyDamage(aliveHeroes);
            levelUp(aliveHeroes);
            applyAngelEffects(roundIdx);

            roundIdx++;

            fileWriter.writeNewLine();
        }
    }

    public void printResults() throws IOException {
        fileWriter.writeWord("~~ Results ~~");
        fileWriter.writeNewLine();

        for (BasicHero hero : getHeroesList()) {
            String line;
            char heroChar = hero.getHeroType().toString().charAt(0);

            if (hero.isDead()) {
                line = String.format("%c dead", heroChar);
            } else {
                line = String.format("%c %d %d %d %d %d",
                        heroChar, hero.getLevel(), hero.getXP(),
                        hero.getHP(), hero.getY(), hero.getX());
            }

            fileWriter.writeWord(line);
            fileWriter.writeNewLine();
        }
    }

    private void applyStrategies(final List<BasicHero> aliveHeroes) {
        for (BasicHero hero : aliveHeroes) {
            if (!hero.isStunned()) {
                BasicStrategy strategy = StrategyFactory.createStrategy(hero);

                hero.increaseHP((int) (strategy.getHPModifier() * hero.getHP()));
                hero.increaseAdditiveModifier(strategy.getHeroModifier());
            }
        }
    }

    private void attackEachOthers(final List<BasicHero> aliveHeroes) {
        for (BasicHero attacker : aliveHeroes) {
            for (IEntity otherEntity : gameMap.getEntities(attacker.getX(), attacker.getY())) {
                if (otherEntity.getEntityType() == EntityType.HERO) {
                    if (otherEntity != attacker) {
                        BasicHero attacked = (BasicHero) otherEntity;
                        if (!attacked.isDead()) {
                            for (IAbility ability : attacker.getAbilities()) {
                                ability.nextTurn();
                                attacked.acceptAbility(ability);
                            }
                        }
                    }
                }
            }
        }
    }

    private void applyDamage(final List<BasicHero> aliveHeroes) {
        HashSet<BasicHero> visitedHeroes = new HashSet<BasicHero>();
        for (BasicHero h : aliveHeroes) {
            if (visitedHeroes.contains(h)) {
                continue;
            }

            Map<BasicHero, Integer> initialXP = new HashMap<BasicHero, Integer>();
            List<BasicHero> aliveHeroesInCell = gameMap.getEntities(h.getX(), h.getY())
                    .stream()
                    .filter(entity -> entity.getEntityType() == EntityType.HERO)
                    .map(entity -> (BasicHero) entity)
                    .filter(hero -> !hero.isDead())
                    .sorted(Comparator.comparingInt(BasicHero::getId).reversed())
                    .collect(Collectors.toList());

            aliveHeroesInCell.forEach(hero -> {
                initialXP.put(hero, hero.getXP());
                visitedHeroes.add(hero);
            });
            aliveHeroesInCell.forEach(BasicHero::applyDamageTaken);

            long aliveHeroesCountAfterDamage = aliveHeroesInCell
                    .stream()
                    .filter(hero -> !hero.isDead())
                    .count();

            if (aliveHeroesCountAfterDamage == 0) {
                aliveHeroesInCell
                        .forEach(hero -> hero.setXP(initialXP.get(hero)));
            }

        }
    }

    private void levelUp(final List<BasicHero> aliveHeroes) {
        for (BasicHero hero : aliveHeroes) {
            hero.levelUp();
        }
    }

    private void applyAngelEffects(final int roundIdx) {
        for (BasicAngel angel : roundAngels.get(roundIdx)) {
            angel.setActive(true);

            gameMap.getEntities(angel.getX(), angel.getY())
                    .stream()
                    .filter(entity -> entity.getEntityType() == EntityType.HERO)
                    .map(entity -> (BasicHero) entity)
                    .sorted(Comparator.comparingInt(BasicHero::getId))
                    .forEach(hero -> hero.acceptAngel(angel));

            angel.setActive(false);
        }
    }

    private void applyPassivePenalties() {
        for (BasicHero hero : heroesList) {
            hero.setLastAttacker(null);
            hero.applyPassivePenalty();
        }
    }

    private void doHeroMoves(final List<Character> moves) {
        int heroIdx = 0;
        for (char move : moves) {
            BasicHero hero = heroesList.get(heroIdx);

            switch (move) {
                case 'U':
                    hero.goUp();
                    break;
                case 'D':
                    hero.goDown();
                    break;
                case 'L':
                    hero.goLeft();
                    break;
                case 'R':
                    hero.goRight();
                    break;
                default:
            }

            heroIdx++;
        }
    }

    private void addObservers() {
        for (BasicHero hero : heroesList) {
            hero.addPropertyChangeListener(heroObserver);
        }

        for (List<BasicAngel> angelsList : roundAngels) {
            for (BasicAngel angel : angelsList) {
                angel.addPropertyChangeListener(angelObserver);
            }
        }
    }

    private List<BasicHero> getAliveHeroesList() {
        List<BasicHero> aliveHeroes = heroesList
                .stream()
                .filter(basicHero -> !basicHero.isDead())
                .collect(Collectors.toList());

        return aliveHeroes;
    }

    public List<BasicHero> getHeroesList() {
        return heroesList;
    }
}
