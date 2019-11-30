package heroes;

public final class Rogue extends BasicHero {
    private static final int INITIAL_HP = 600;
    private static final int HP_BONUS_PER_LEVEL = 40;

    @Override
    public HeroType getHeroType() {
        return HeroType.ROGUE;
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
        return "R";
    }
}
