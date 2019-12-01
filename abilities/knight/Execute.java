package abilities.knight;

import abilities.IAbility;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public final class Execute implements IAbility {
    private static final int BASE_DAMAGE = 200;
    private static final int DAMAGE_MULTIPLIER = 30;

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
        float percent = Math.max(40.0f, 20.0f + getAttacker().getLevel()) / 100.0f;
        return (attacked.getHP() <= percent * attacked.getMaxHP());
    }

    private void apply(final BasicHero attacked, final float heroModifier) {
        float damage;

        if (isInstantKillAvailable(attacked)) {
            damage = attacked.getHP();
        } else {
            damage = computeDamageWithoutModifiers();
            damage *= heroModifier * getAttacker().getLandModifier();
        }

        // TODO: instakill cum se comporta in combinatie cu Deflect?

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
        return 1.0f;
    }

    @Override
    public float getHeroModifier(final Pyromancer attacked) {
        return 1.10f;
    }

    @Override
    public float getHeroModifier(final Rogue attacked) {
        return 1.15f;
    }

    @Override
    public float getHeroModifier(final Wizard attacked) {
        return 0.80f;
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
