package abilities.pyromancer;

import abilities.IAbility;
import abilities.IPassive;
import abilities.Utils;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;

public final class Ignite implements IAbility {
    private static final int BASE_DAMAGE = 150;
    private static final int DAMAGE_MULTIPLIER = 20;

    private static final float KNIGHT_MODIFIER = 1.20f;
    private static final float PYROMANCER_MODIFIER = 0.90f;
    private static final float ROGUE_MODIFIER = 0.80f;
    private static final float WIZARD_MODIFIER = 1.05f;

    private static final int PASSIVE_PENALTY_ROUNDS = 2;
    private static final int PASSIVE_PENALTY_BASE_DAMAGE = 50;
    private static final int PASSIVE_PENALTY_MULTIPLIER = 30;

    private BasicHero attacker;

    public Ignite(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public int computeDamageWithoutModifiers() {
        int damage = computeDamageWithLevelMultiplier();
        return damage;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float adjustedHeroModifier = Utils.adjustHeroModifier(
                heroModifier, getAttacker().getAdditiveModifier());
        int damage = computeDamageWithoutModifiers();
        damage = Math.round(getAttacker().getLandModifier() * damage);
        damage = Math.round(adjustedHeroModifier * damage);

        int passiveDamage = PASSIVE_PENALTY_BASE_DAMAGE + getAttacker().getLevel() * PASSIVE_PENALTY_MULTIPLIER;
        passiveDamage = Math.round(getAttacker().getLandModifier() * passiveDamage);
        passiveDamage = Math.round(adjustedHeroModifier * passiveDamage);

        final int finalPassiveDamage = passiveDamage;
        attacked.setPassivePenalty(PASSIVE_PENALTY_ROUNDS, new IPassive() {
            private int dmg = finalPassiveDamage;

            @Override
            public void apply(final BasicHero attacked) {
                attacked.decreaseHP(dmg); // DoT
            }
        }, null);

        attacked.increaseDamageTaken(damage);
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
