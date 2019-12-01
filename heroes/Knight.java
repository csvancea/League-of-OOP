package heroes;

import map.surface.SurfaceType;

public final class Knight extends BasicHero {
    private static final int INITIAL_HP = 900;
    private static final int HP_BONUS_PER_LEVEL = 80;
    private static final float LAND_MODIFIER = 1.15f;

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
    public float getLandModifier() {
        SurfaceType surfaceType = getSurface().getSurfaceType();
        if (surfaceType == SurfaceType.LAND) {
            return LAND_MODIFIER;
        }
        return 1.0f;
    }

    @Override
    public String toString() {
        return "K";
    }
}
