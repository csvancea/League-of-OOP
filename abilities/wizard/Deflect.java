package abilities.wizard;

import abilities.IAbility;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Deflect implements IAbility {
    private static final int BASE_PERCENT = 35;
    private static final int PERCENT_MULTIPLIER = 2;

    private BasicHero attacker;

    public Deflect(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        return 0.0f;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float percent = (float) (Math.min(70, BASE_PERCENT + getAttacker().getLevel() * PERCENT_MULTIPLIER)) / 100.f;
        int damageTaken = 0;
        float damage;

        for (IAbility ability : attacked.getAbilities()) {
            damageTaken += Math.round(ability.computeDamageWithoutModifiers() * attacked.getLandModifier());
        }

        damage = percent * damageTaken * heroModifier * getAttacker().getLandModifier();
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
    }

    @Override
    public float getHeroModifier(final Knight attacked) {
        return 1.40f;
    }

    @Override
    public float getHeroModifier(final Pyromancer attacked) {
        return 1.30f;
    }

    @Override
    public float getHeroModifier(final Rogue attacked) {
        return 1.20f;
    }

    @Override
    public float getHeroModifier(final Wizard attacked) {
        return 1.0f;
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
