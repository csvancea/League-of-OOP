package main;

import abilities.IAbility;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.strategies.BasicStrategy;
import entities.heroes.strategies.StrategyFactory;
import map.GameMap;
import entities.IEntity;
import entities.EntityType;

import java.util.List;
import java.util.stream.Collectors;

public final class GameLogic {
    private final GameMap gameMap;
    private final List<BasicHero> heroesList;
    private final List<List<Character>> roundMoves;
    private final List<List<BasicAngel>> roundAngels;

    public GameLogic(final IGameLoader gameLoader) {
        gameMap = gameLoader.getGameMap();
        heroesList = gameLoader.getHeroesList();
        roundMoves = gameLoader.getRoundMoves();
        roundAngels = gameLoader.getRoundAngels();
    }

    public void play() {
        int roundIdx = 0;
        for (List<Character> round : roundMoves) {
            doHeroMoves(round);
            applyPassivePenalties();

            List<BasicHero> aliveHeroes = getAliveHeroesList();

            applyStrategies(aliveHeroes);
            attackEachOthers(aliveHeroes);
            applyDamage(aliveHeroes);
            levelUp(aliveHeroes);
            applyAngelEffects(roundIdx);

            roundIdx++;
        }
    }

    private void applyStrategies(final List<BasicHero> aliveHeroes) {
        for (BasicHero hero : aliveHeroes) {
            if (!hero.isStunned()) {
                BasicStrategy strategy = StrategyFactory.createStrategy(hero);

                // TODO: check this round!
                hero.setHP(Math.round(hero.getHP() * strategy.getHPModifier()));
                hero.increaseAdditiveModifier(strategy.getHeroModifier());
            }
        }
    }

    private void attackEachOthers(final List<BasicHero> aliveHeroes) {
        for (BasicHero attacker : aliveHeroes) {
            for (IEntity otherEntity : gameMap.getEntities(attacker.getX(), attacker.getY())) {
                if (otherEntity.getEntityType() == EntityType.HERO) {
                    if (otherEntity != attacker) {
                        for (IAbility ability : attacker.getAbilities()) {
                            ability.nextTurn();
                            ((BasicHero) otherEntity).acceptAbility(ability);
                        }
                    }
                }
            }
        }
    }

    private void applyDamage(final List<BasicHero> aliveHeroes) {
        for (BasicHero attacked : aliveHeroes) {
            attacked.applyDamageTaken();

            if (attacked.isDead()) {
                BasicHero attacker = attacked.getLastAttacker();
                attacker.onKill(attacked);
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
            // TODO: notify

            for (IEntity otherEntity : gameMap.getEntities(angel.getX(), angel.getY())) {
                if (otherEntity.getEntityType() == EntityType.HERO) {
                    ((BasicHero) otherEntity).acceptAngel(angel);
                }
            }
        }
    }

    private void applyPassivePenalties() {
        for (BasicHero hero : heroesList) {
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
