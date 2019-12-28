package abilities.rogue;

import abilities.IAbility;
import abilities.IPassive;
import abilities.Utils;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.surface.SurfaceType;

public final class Paralysis implements IAbility {
    private static final int BASE_DAMAGE = 40;
    private static final int DAMAGE_MULTIPLIER = 10;

    private static final float KNIGHT_MODIFIER = 0.79f;
    private static final float PYROMANCER_MODIFIER = 1.20f;
    private static final float ROGUE_MODIFIER = 0.89f;
    private static final float WIZARD_MODIFIER = 1.25f;

    private static final int PASSIVE_PENALTY_MIN_ROUNDS = 3;
    private static final int PASSIVE_PENALTY_MAX_ROUNDS = 6;

    private BasicHero attacker;

    public Paralysis(final BasicHero attacker) {
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

        int numRounds;
        if (attacked.getSurface().getSurfaceType() == SurfaceType.WOODS) {
            numRounds = PASSIVE_PENALTY_MAX_ROUNDS;
        } else {
            numRounds = PASSIVE_PENALTY_MIN_ROUNDS;
        }

        final int finalDamage = damage;
        attacked.setPassivePenalty(numRounds, new IPassive() {
            private int x = attacked.getX();
            private int y = attacked.getY();
            private int d = finalDamage;

            @Override
            public void apply(final BasicHero attacked) {
                attacked.decreaseHP(d); // DoT
                attacked.setPosition(x, y); // stun
            }
        }, heroAttacked -> heroAttacked.setStunned(false));

        attacked.setStunned(true);
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
