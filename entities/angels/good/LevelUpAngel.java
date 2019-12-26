package entities.angels.good;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class LevelUpAngel extends BasicAngel {
    private static final float KNIGHT_MODIFIER = 0.10f;
    private static final float PYROMANCER_MODIFIER = 0.20f;
    private static final float ROGUE_MODIFIER = 0.15f;
    private static final float WIZARD_MODIFIER = 0.25f;

    public LevelUpAngel(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.GOOD;
        name = "LevelUpAngel";
    }

    private void apply(final BasicHero receiver, final float angelModifier) {
        if (!receiver.isDead()) {
            int bonusXP = receiver.getNeededXPForLevelUp() - receiver.getXP();
            assert (bonusXP > 0);
            // TODO: bonusul se aplica dupa levelup ul de la kill sau inainte?
            support.firePropertyChange("interact", null, receiver);
            receiver.increaseXP(bonusXP);
            receiver.levelUp();
            receiver.increaseAdditiveModifier(angelModifier);
        }
    }

    public void apply(final Knight receiver) {
        apply(receiver, KNIGHT_MODIFIER);
    }
    public void apply(final Pyromancer receiver) {
        apply(receiver, PYROMANCER_MODIFIER);
    }
    public void apply(final Rogue receiver) {
        apply(receiver, ROGUE_MODIFIER);
    }
    public void apply(final Wizard receiver) {
        apply(receiver, WIZARD_MODIFIER);
    }
}
