package entities.angels.good;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class XPAngel extends BasicAngel {
    private static final int KNIGHT_XP = 45;
    private static final int PYROMANCER_XP = 50;
    private static final int ROGUE_XP = 40;
    private static final int WIZARD_XP = 60;

    public XPAngel(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.GOOD;
        name = this.getClass().getSimpleName();
    }

    private void apply(final BasicHero receiver, final int angelXP) {
        if (!receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
            receiver.increaseXP(angelXP);
            receiver.levelUp();
        }
    }

    public void apply(final Knight receiver) {
        apply(receiver, KNIGHT_XP);
    }
    public void apply(final Pyromancer receiver) {
        apply(receiver, PYROMANCER_XP);
    }
    public void apply(final Rogue receiver) {
        apply(receiver, ROGUE_XP);
    }
    public void apply(final Wizard receiver) {
        apply(receiver, WIZARD_XP);
    }
}
