package heroes;

public final class Knight extends BasicHero {
    private static final int INITIAL_HP = 900;
    private static final int HP_BONUS_PER_LEVEL = 80;

    public Knight(final int x, final int y) {
        super(x, y);
    }

    @Override
    public HeroType getHeroType() {
        return HeroType.KNIGHT;
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
        return "K";
    }
}
