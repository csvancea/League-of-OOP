package heroes;

public final class Wizard extends BasicHero {
    private static final int INITIAL_HP = 400;
    private static final int HP_BONUS_PER_LEVEL = 30;

    public Wizard(final int x, final int y) {
        super(x, y);
    }

    @Override
    public HeroType getHeroType() {
        return HeroType.WIZARD;
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
        return "W";
    }
}
