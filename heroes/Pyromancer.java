package heroes;

import abilities.IAbility;
import map.surface.SurfaceType;

public final class Pyromancer extends BasicHero {
    private static final int INITIAL_HP = 500;
    private static final int HP_BONUS_PER_LEVEL = 50;
    private static final float LAND_MODIFIER = 1.25f;

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
    public float getLandModifier() {
        SurfaceType surfaceType = getSurface().getSurfaceType();
        if (surfaceType == SurfaceType.VOLCANIC) {
            return LAND_MODIFIER;
        }
        return 1.0f;
    }

    @Override
    public void acceptAbility(final IAbility ability) {
        ability.apply(this);
        setLastAttacker(ability.getAttacker());
    }
}
