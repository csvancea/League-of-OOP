package abilities.knight;

import abilities.IAbility;
import abilities.IPassive;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Slam implements IAbility {
    private static final int BASE_DAMAGE = 100;
    private static final int DAMAGE_MULTIPLIER = 40;

    private static final float KNIGHT_MODIFIER = 1.20f;
    private static final float PYROMANCER_MODIFIER = 0.90f;
    private static final float ROGUE_MODIFIER = 0.80f;
    private static final float WIZARD_MODIFIER = 1.05f;

    private static final int PASSIVE_PENALTY_ROUNDS = 1;

    private BasicHero attacker;

    public Slam(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        float damage = computeDamageWithLevelMultiplier();
        return damage;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float adjustedHeroModifier = heroModifier + getAttacker().getAdditiveModifier();
        float damage = computeDamageWithoutModifiers();
        damage *= adjustedHeroModifier * getAttacker().getLandModifier();

        attacked.setPassivePenalty(PASSIVE_PENALTY_ROUNDS, new IPassive() {
            private int x = attacked.getX();
            private int y = attacked.getY();

            @Override
            public void apply(final BasicHero attacked) {
                attacked.setPosition(x, y); // stun
            }
        }, attackedHero -> attackedHero.setStunned(false));

        attacked.setStunned(true);
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
