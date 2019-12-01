package abilities.rogue;

import abilities.IAbility;
import abilities.IPassive;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import map.surface.SurfaceType;

public final class Paralysis implements IAbility {
    private static final int BASE_DAMAGE = 40;
    private static final int DAMAGE_MULTIPLIER = 10;

    private BasicHero attacker;

    public Paralysis(final BasicHero attacker) {
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

        int numRounds;
        if (attacked.getSurface().getSurfaceType() == SurfaceType.WOODS) {
            numRounds = 6;
        } else {
            numRounds = 3;
        }

        float finalDamage = damage;
        attacked.setPassivePenalty(getAttacker(), new IPassive() {
            private int x = attacked.getX();
            private int y = attacked.getY();
            private int r = numRounds;
            private int d = Math.round(finalDamage);
            @Override
            public void apply(final BasicHero attacked) {
                if (r-- > 0) {
                    attacked.decreaseHP(d); // DoT
                    attacked.setPosition(x, y); // stun
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
        return 0.80f;
    }

    @Override
    public float getHeroModifier(final Pyromancer attacked) {
        return 1.20f;
    }

    @Override
    public float getHeroModifier(final Rogue attacked) {
        return 0.90f;
    }

    @Override
    public float getHeroModifier(final Wizard attacked) {
        return 1.25f;
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
