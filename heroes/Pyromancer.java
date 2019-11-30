package heroes;

public final class Pyromancer extends BasicHero {
    private static final int INITIAL_HP = 500;
    private static final int HP_BONUS_PER_LEVEL = 50;

    public Pyromancer(final int x, final int y) {
        super(x, y);
    }

    @Override
    public HeroType getHeroType() {
        return HeroType.PYROMANCER;
    }

    @Override
    public int getInitialHP() {
        return INITIAL_HP;
    }

    @Override
    public int getHPBonusPerLevel() {
        return HP_BONUS_PER_LEVEL;
    }

    @Override
    public String toString() {
        return "P";
    }
}
