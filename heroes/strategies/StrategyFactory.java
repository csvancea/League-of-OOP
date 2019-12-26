package heroes.strategies;

import heroes.BasicHero;

public final class StrategyFactory {
    private StrategyFactory() { }

    public static BasicStrategy createStrategy(final BasicHero hero) {
        switch (hero.getHeroType()) {
            case KNIGHT:
                return new KnightStrategy(hero);
            case PYROMANCER:
                return new PyromancerStrategy(hero);
            default:
                return null;
        }
    }
}
