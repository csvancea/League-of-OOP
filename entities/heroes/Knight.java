package entities.heroes;

import abilities.IAbility;
import abilities.knight.Execute;
import abilities.knight.Slam;
import entities.angels.BasicAngel;
import map.surface.SurfaceType;

public final class Knight extends BasicHero {
    private static final int INITIAL_HP = 900;
    private static final int HP_BONUS_PER_LEVEL = 80;
    private static final float LAND_MODIFIER = 1.15f;

    public Knight() {
        super();
        getAbilities().add(new Execute(this));
        getAbilities().add(new Slam(this));
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
    public float getLandModifier() {
        SurfaceType surfaceType = getSurface().getSurfaceType();
        if (surfaceType == SurfaceType.LAND) {
            return LAND_MODIFIER;
        }
        return 1.0f;
    }

    @Override
    public void acceptAbility(final IAbility ability) {
        ability.apply(this);
        setLastAttacker(ability.getAttacker());
    }

    @Override
    public void acceptAngel(final BasicAngel angel) {
        angel.apply(this);
    }
}
