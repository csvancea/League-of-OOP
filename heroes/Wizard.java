package heroes;

import map.surface.SurfaceType;

public final class Wizard extends BasicHero {
    private static final int INITIAL_HP = 400;
    private static final int HP_BONUS_PER_LEVEL = 30;
    private static final int LAND_MODIFIER = 10;

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
    public int getLandModifier(final SurfaceType surfaceType) {
        if (surfaceType == SurfaceType.DESERT) {
            return LAND_MODIFIER;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "W";
    }
}
