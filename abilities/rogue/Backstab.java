package abilities.rogue;

import abilities.IAbility;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import map.surface.SurfaceType;

public final class Backstab implements IAbility {
    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_MULTIPLIER = 20;

    private BasicHero attacker;
    private int hitCount;
    private int hitCountThisTurn;

    public Backstab(final BasicHero attacker) {
        this.attacker = attacker;
        hitCount = 0;
        hitCountThisTurn = 0;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        float damage = computeDamageWithLevelMultiplier();
        if (hitCount % 3 == 0 && getAttacker().getSurface().getSurfaceType() == SurfaceType.WOODS) {
            damage *= 1.5f;
        }

        return damage;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float damage = computeDamageWithoutModifiers();
        damage *= heroModifier * getAttacker().getLandModifier();

        hitCountThisTurn++;
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
        return 0.9f;
    }

    @Override
    public float getHeroModifier(final Pyromancer attacked) {
        return 1.25f;
    }

    @Override
    public float getHeroModifier(final Rogue attacked) {
        return 1.20f;
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
        hitCount += hitCountThisTurn;
        hitCountThisTurn = 0;
    }
}
