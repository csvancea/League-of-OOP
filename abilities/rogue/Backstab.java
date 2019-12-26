package abilities.rogue;

import abilities.IAbility;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.surface.SurfaceType;

public final class Backstab implements IAbility {
    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_MULTIPLIER = 20;

    private static final float KNIGHT_MODIFIER = 0.90f;
    private static final float PYROMANCER_MODIFIER = 1.25f;
    private static final float ROGUE_MODIFIER = 1.20f;
    private static final float WIZARD_MODIFIER = 1.25f;

    private static final int SPECIAL_HIT_EACH_X_ROUNDS = 3;
    private static final float SPECIAL_HIT_MODIFIER = 1.50f;

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
        if (hitCount % SPECIAL_HIT_EACH_X_ROUNDS == 0
                && getAttacker().getSurface().getSurfaceType() == SurfaceType.WOODS) {
            damage *= SPECIAL_HIT_MODIFIER;
        }

        return damage;
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float adjustedHeroModifier = heroModifier + getAttacker().getAdditiveModifier();
        float damage = computeDamageWithoutModifiers();
        damage *= adjustedHeroModifier * getAttacker().getLandModifier();

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
        hitCount += hitCountThisTurn;
        hitCountThisTurn = 0;
    }
}
