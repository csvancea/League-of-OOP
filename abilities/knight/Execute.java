package abilities.knight;

import abilities.IAbility;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;

public final class Execute implements IAbility {
    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_MULTIPLIER = 30;

    private static final float KNIGHT_MODIFIER = 1.00f;
    private static final float PYROMANCER_MODIFIER = 1.10f;
    private static final float ROGUE_MODIFIER = 1.15f;
    private static final float WIZARD_MODIFIER = 0.80f;

    private static final int INSTANTKILL_BASE = 20;
    private static final int INSTANTKILL_MULTIPLIER = 1;
    private static final int INSTANTKILL_MAX = 40;
    private static final float PERCENT = 1.00f / 100.00f;

    private BasicHero attacker;

    public Execute(final BasicHero attacker) {
        this.attacker = attacker;
    }

    @Override
    public float computeDamageWithoutModifiers() {
        float damage = computeDamageWithLevelMultiplier();
        return damage;
    }

    private boolean isInstantKillAvailable(final BasicHero attacked) {
        float percent = INSTANTKILL_BASE + INSTANTKILL_MULTIPLIER * getAttacker().getLevel();
        percent = Math.min(percent, INSTANTKILL_MAX) * PERCENT;

        return (attacked.getHP() <= percent * attacked.getMaxHP());
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float adjustedHeroModifier = heroModifier + getAttacker().getAdditiveModifier();
        float damage;

        if (isInstantKillAvailable(attacked)) {
            damage = attacked.getHP();
        } else {
            damage = computeDamageWithoutModifiers();
            damage *= adjustedHeroModifier * getAttacker().getLandModifier();
        }

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
