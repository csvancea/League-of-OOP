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
    private static final int MAX_PERCENT = 70;

    private static final float KNIGHT_MODIFIER = 1.40f;
    private static final float PYROMANCER_MODIFIER = 1.30f;
    private static final float ROGUE_MODIFIER = 1.20f;
    private static final float WIZARD_MODIFIER = 1.00f;

    private static final float PERCENT = 1.00f / 100.00f;

    private BasicHero attacker;

    public Deflect(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        return 0.00f;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float adjustedHeroModifier = heroModifier + getAttacker().getAdditiveModifier();
        float percent = BASE_PERCENT + getAttacker().getLevel() * PERCENT_MULTIPLIER;
        percent = Math.min(percent, MAX_PERCENT) * PERCENT;

        int damageTaken = 0;
        float damageGiven;

        for (IAbility ability : attacked.getAbilities()) {
            damageTaken += Math.round(ability.computeDamageWithoutModifiers()
                                        * attacked.getLandModifier());
        }

        damageGiven = percent * damageTaken * adjustedHeroModifier * getAttacker().getLandModifier();
        attacked.increaseDamageTaken(Math.round(damageGiven));
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
