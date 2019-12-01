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

    /**
     * Returneaza *hero modifier*-ul ce trebuie aplicat conform tipului victimei.
     * @param attacked: eroul atacat
     * @return hero modifier
     */
    float getHeroModifier(Knight attacked);
    float getHeroModifier(Pyromancer attacked);
    float getHeroModifier(Rogue attacked);
    float getHeroModifier(Wizard attacked);

    /**
     * Returneaza eroul ce detine aceasta abilitate.
     * @return BasicHero
     */
    BasicHero getAttacker();

    /**
     * Instiinteaza abilitatea ca tocmai s-a incheiat o tura/runda.
     */
    void nextTurn();

    /**
     * Damage fara modificatori.
     * @return damage
     */
    int getBaseDamage();

    /**
     * Multiplicatorul de nivel pentru damages.
     * @return multiplicator
     */
    int getDamageLevelMultiplier();

    /**
     * Damage-ul ce ar fi facut de abilitate, fara hero/land modifiers.
     * @return damage.
     * Pentru abilitatile la care damage-ul depinde de victima (wizard!), retureneaza 0.
     */
    float computeDamageWithoutModifiers();

    /**
     * Calculeaza damage-ul cu multiplicatorul de nivel.
     * @return damage
     */
    default int computeDamageWithLevelMultiplier() {
        return getBaseDamage() + getAttacker().getLevel() * getDamageLevelMultiplier();
    }
}
