package entities.heroes.strategies;

import entities.heroes.BasicHero;

public final class RogueStrategy extends BasicStrategy {
    private static final float UPPER_LIMIT_MAXHP_MULTIPLIER = 1.00f / 5.00f;
    private static final float LOWER_LIMIT_MAXHP_MULTIPLIER = 1.00f / 7.00f;

    private static final float HIGHHP_HEALTH_MODIFIER = 1.00f;
    private static final float HIGHHP_HERO_ADDITIVE_MODIFIER = 0.00f;

    private static final float MIDHP_HEALTH_MODIFIER = 6.00f / 7.00f;
    private static final float MIDHP_HERO_ADDITIVE_MODIFIER = 0.40f;

    private static final float LOWHP_HEALTH_MODIFIER = 3.00f / 2.00f;
    private static final float LOWHP_HERO_ADDITIVE_MODIFIER = -0.10f;

    public RogueStrategy(final BasicHero hero) {
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
