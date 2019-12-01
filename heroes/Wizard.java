package heroes;

import map.surface.SurfaceType;

public final class Wizard extends BasicHero {
    private static final int INITIAL_HP = 400;
    private static final int HP_BONUS_PER_LEVEL = 30;
    private static final float LAND_MODIFIER = 1.10f;

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
    public float getLandModifier() {
        SurfaceType surfaceType = getSurface().getSurfaceType();
        if (surfaceType == SurfaceType.DESERT) {
            return LAND_MODIFIER;
        }
        return 1.0f;
    }

    @Override
    public String toString() {
        return "W";
    }
}
