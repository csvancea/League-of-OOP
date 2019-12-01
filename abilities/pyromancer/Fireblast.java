package abilities.pyromancer;

import abilities.IAbility;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Fireblast implements IAbility {
    private static final int BASE_DAMAGE = 350;
    private static final int DAMAGE_MULTIPLIER = 50;

    private BasicHero attacker;

    public Fireblast(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        float damage = computeDamageWithLevelMultiplier();
        return damage;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float damage = computeDamageWithoutModifiers();
        damage *= heroModifier * getAttacker().getLandModifier();

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
        return BASE_DAMAGE;
    }

    @Override
    public int getDamageLevelMultiplier() {
        return DAMAGE_MULTIPLIER;
    }

    @Override
    public void nextTurn() {
    }
}
