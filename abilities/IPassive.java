package abilities;

import heroes.BasicHero;

/**
 * Pentru a aplica o abilitate pasiva, se suprascrie `apply` cu o metoda ce aplica efectul
 * pasiv asupra eroului `attacked`.
 *
 * attacked.setPassivePenalty(attacker, new IPassive() {
 *     // suprascriere `apply`
 * });
 */
public interface IPassive {
    /**
     * @param attacked eroul caruia i se aplica actiunea
     */
    void apply(BasicHero attacked);
}

