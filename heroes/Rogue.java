package heroes;

import abilities.IAbility;
import abilities.rogue.Backstab;
import abilities.rogue.Paralysis;
import map.surface.SurfaceType;

public final class Rogue extends BasicHero {
    private static final int INITIAL_HP = 600;
    private static final int HP_BONUS_PER_LEVEL = 40;
    private static final float LAND_MODIFIER = 1.15f;

    public Rogue() {
        super();
        getAbilities().add(new Backstab(this));
        getAbilities().add(new Paralysis(this));
    }

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
    public float getLandModifier() {
        SurfaceType surfaceType = getSurface().getSurfaceType();
        if (surfaceType == SurfaceType.WOODS) {
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
