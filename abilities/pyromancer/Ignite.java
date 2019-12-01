package abilities.pyromancer;

import abilities.IAbility;
import abilities.IPassive;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

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
    public float computeDamageWithoutModifiers() {
        float damage = computeDamageWithLevelMultiplier();
        return damage;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float modifiers = heroModifier * getAttacker().getLandModifier();
        float damage = computeDamageWithoutModifiers() * modifiers;
        float passiveDamage = (PASSIVE_PENALTY_BASE_DAMAGE
                + getAttacker().getLevel() * PASSIVE_PENALTY_MULTIPLIER) * modifiers;

        attacked.setPassivePenalty(getAttacker(), new IPassive() {
            private int r = PASSIVE_PENALTY_ROUNDS;
            private int dmg = Math.round(passiveDamage);
            @Override
            public void apply(final BasicHero attacked) {
                if (r-- > 0) {
                    attacked.decreaseHP(dmg); // DoT
                }
            }
        });

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
