package abilities;

import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface IAbility {
    /**
     * Aplica abilitatea unui erou de tip X.
     * Implementare cu: double-dispatch
     */
    void apply(Knight attacked);
    void apply(Pyromancer attacked);
    void apply(Rogue attacked);
    void apply(Wizard attacked);

    float getHeroModifier(Knight attacked);
    float getHeroModifier(Pyromancer attacked);
    float getHeroModifier(Rogue attacked);
    float getHeroModifier(Wizard attacked);

    BasicHero getAttacker();
    void nextTurn();

    int getBaseDamage();
    int getDamageLevelMultiplier();

    float computeDamageWithoutModifiers();
    default int computeDamageWithLevelMultiplier() {
        return getBaseDamage() + getAttacker().getLevel() * getDamageLevelMultiplier();
    }
}
