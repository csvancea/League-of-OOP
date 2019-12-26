package heroes.strategies;

import heroes.BasicHero;

public final class KnightStrategy extends BasicStrategy {
    private static final float UPPER_LIMIT_MAXHP_MULTIPLIER = 1.00f / 2.00f;
    private static final float LOWER_LIMIT_MAXHP_MULTIPLIER = 1.00f / 3.00f;

    private static final float HIGHHP_HEALTH_MODIFIER = 1.00f;
    private static final float HIGHHP_HERO_ADDITIVE_MODIFIER = 0.00f;

    private static final float MIDHP_HEALTH_MODIFIER = 4.00f / 5.00f;
    private static final float MIDHP_HERO_ADDITIVE_MODIFIER = 0.50f;

    private static final float LOWHP_HEALTH_MODIFIER = 5.00f / 4.00f;
    private static final float LOWHP_HERO_ADDITIVE_MODIFIER = -0.20f;

    public KnightStrategy(final BasicHero hero) {
        super(hero);
    }

    @Override
    protected float getUpperLimitMaxHPMultiplier() {
        return UPPER_LIMIT_MAXHP_MULTIPLIER;
    }

    @Override
    protected float getLowerLimitMaxHPMultiplier() {
        return LOWER_LIMIT_MAXHP_MULTIPLIER;
    }

    @Override
    protected float getHighHPHealthModifier() {
        return HIGHHP_HEALTH_MODIFIER;
    }

    @Override
    protected float getHighHPHeroAdditiveModifier() {
        return HIGHHP_HERO_ADDITIVE_MODIFIER;
    }

    @Override
    protected float getMidHPHealthModifier() {
        return MIDHP_HEALTH_MODIFIER;
    }

    @Override
    protected float getMidHPHeroAdditiveModifier() {
        return MIDHP_HERO_ADDITIVE_MODIFIER;
    }

    @Override
    protected float getLowHPHealthModifier() {
        return LOWHP_HEALTH_MODIFIER;
    }

    @Override
    protected float getLowHPHeroAdditiveModifier() {
        return LOWHP_HERO_ADDITIVE_MODIFIER;
    }
}
