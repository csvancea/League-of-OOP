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

    private static final float KNIGHT_MODIFIER = 1.20f;
    private static final float PYROMANCER_MODIFIER = 0.90f;
    private static final float ROGUE_MODIFIER = 0.80f;
    private static final float WIZARD_MODIFIER = 1.05f;

    private static final float MAX_HP_MODIFIER = 0.30f;
    private static final float PERCENT = 1.00f / 100.00f;

    private BasicHero attacker;

    public Drain(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        return 0.00f;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float percent = (BASE_PERCENT + getAttacker().getLevel() * PERCENT_MULTIPLIER) * PERCENT;
        percent *= heroModifier * getAttacker().getLandModifier();

        float baseHP = Math.min(MAX_HP_MODIFIER * attacked.getMaxHP(), attacked.getHP());
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
        return KNIGHT_MODIFIER;
    }

    @Override
    public float getHeroModifier(final Pyromancer attacked) {
        return PYROMANCER_MODIFIER;
    }

    @Override
    public float getHeroModifier(final Rogue attacked) {
        return ROGUE_MODIFIER;
    }

    @Override
    public float getHeroModifier(final Wizard attacked) {
        return WIZARD_MODIFIER;
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
