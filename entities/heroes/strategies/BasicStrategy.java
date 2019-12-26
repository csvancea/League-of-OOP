package entities.heroes.strategies;

import entities.heroes.BasicHero;

public abstract class BasicStrategy {
    private final BasicHero hero;
    private float heroModifier;
    private float hpModifier;

    public BasicStrategy(final BasicHero hero) {
        this.hero = hero;
        recomputeModifiers();
    }

    private void recomputeModifiers() {
        int hp = hero.getHP();
        int maxHP = hero.getMaxHP();

        if (hp >= getUpperLimitMaxHPMultiplier() * maxHP) {
            hpModifier   = getHighHPHealthModifier();
            heroModifier = getHighHPHeroAdditiveModifier();
        } else if (hp >= getLowerLimitMaxHPMultiplier() * maxHP) {
            hpModifier   = getMidHPHealthModifier();
            heroModifier = getMidHPHeroAdditiveModifier();
        } else {
            hpModifier   = getLowHPHealthModifier();
            heroModifier = getLowHPHeroAdditiveModifier();
        }
    }

    public final float getHeroModifier() {
        return heroModifier;
    }

    public final float getHPModifier() {
        return hpModifier;
    }

    protected abstract float getUpperLimitMaxHPMultiplier();
    protected abstract float getLowerLimitMaxHPMultiplier();

    protected abstract float getHighHPHealthModifier();
    protected abstract float getHighHPHeroAdditiveModifier();

    protected abstract float getMidHPHealthModifier();
    protected abstract float getMidHPHeroAdditiveModifier();

    protected abstract float getLowHPHealthModifier();
    protected abstract float getLowHPHeroAdditiveModifier();
}
