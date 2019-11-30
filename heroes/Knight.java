package heroes;

import map.surface.SurfaceType;

public final class Knight extends BasicHero {
    private static final int INITIAL_HP = 900;
    private static final int HP_BONUS_PER_LEVEL = 80;
    private static final int LAND_MODIFIER = 15;

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
    public int getLandModifier(final SurfaceType surfaceType) {
        if (surfaceType == SurfaceType.LAND) {
            return LAND_MODIFIER;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "K";
    }
}
