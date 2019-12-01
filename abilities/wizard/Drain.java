package abilities.wizard;

import abilities.IAbility;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Drain implements IAbility {
    private static final int BASE_PERCENT = 20;
    private static final int PERCENT_MULTIPLIER = 5;

    private BasicHero attacker;

    public Drain(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        return 0.0f;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float percent = (float) (BASE_PERCENT + getAttacker().getLevel() * PERCENT_MULTIPLIER) / 100.f;
        percent *= heroModifier * getAttacker().getLandModifier();

        float baseHP = Math.min(0.3f * attacked.getMaxHP(), attacked.getHP());
        float damage = percent * baseHP;

        attacked.increaseDamageTaken(Math.round(damage));
    }

    @Override
    public void apply(final Knight attacked) {
        apply(attacked, getHeroModifier(attacked));
    }

    @Override
    public void apply(final Pyromancer attacked) {
        apply(attacked, getHeroModifier(attacked));
    }

    @Override
    public void apply(final Rogue attacked) {
        apply(attacked, getHeroModifier(attacked));
    }

    @Override
    public void apply(final Wizard attacked) {
        apply(attacked, getHeroModifier(attacked));
    }

    @Override
    public float getHeroModifier(final Knight attacked) {
        return 1.20f;
    }

    @Override
    public float getHeroModifier(final Pyromancer attacked) {
        return 0.90f;
    }

    @Override
    public float getHeroModifier(final Rogue attacked) {
        return 0.80f;
    }

    @Override
    public float getHeroModifier(final Wizard attacked) {
        return 1.05f;
    }

    @Override
    public BasicHero getAttacker() {
        return attacker;
    }

    @Override
    public int getBaseDamage() {
        return 0;
    }

    @Override
    public int getDamageLevelMultiplier() {
        return 0;
    }

    @Override
    public void nextTurn() {
    }
}
