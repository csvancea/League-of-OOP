package main;

import abilities.IAbility;
import angels.BasicAngel;
import heroes.BasicHero;
import map.GameMap;
import map.entity.IMapEntity;
import map.entity.MapEntityType;

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
        for (List<Character> round : roundMoves) {
            doHeroMoves(round);
            applyPassivePenalties();

            List<BasicHero> aliveHeroes = getAliveHeroesList();

            attackEachOthers(aliveHeroes);
            applyDamage(aliveHeroes);
            levelUp(aliveHeroes);
        }
    }

    private void attackEachOthers(final List<BasicHero> aliveHeroes) {
        for (BasicHero attacker : aliveHeroes) {
            for (IMapEntity otherEntity : gameMap.getEntities(attacker.getX(), attacker.getY())) {
                if (otherEntity.getMapEntityType() == MapEntityType.HERO) {
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
