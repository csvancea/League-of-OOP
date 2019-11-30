package heroes;

import map.surface.SurfaceType;

public final class Rogue extends BasicHero {
    private static final int INITIAL_HP = 600;
    private static final int HP_BONUS_PER_LEVEL = 40;
    private static final int LAND_MODIFIER = 15;

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
    public int getLandModifier(final SurfaceType surfaceType) {
        if (surfaceType == SurfaceType.WOODS) {
            return LAND_MODIFIER;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "R";
    }
}
