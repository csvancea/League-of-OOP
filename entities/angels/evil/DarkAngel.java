package entities.angels.evil;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class DarkAngel extends BasicAngel {
    private static final int KNIGHT_HP = 40;
    private static final int PYROMANCER_HP = 30;
    private static final int ROGUE_HP = 10;
    private static final int WIZARD_HP = 20;

    public DarkAngel(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.EVIL;
        name = this.getClass().getSimpleName();
    }

    private void apply(final BasicHero receiver, final int angelHP) {
        if (!receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
            receiver.setLastAttacker(this);
            receiver.decreaseHP(angelHP);
        }
    }

    public void apply(final Knight receiver) {
        apply(receiver, KNIGHT_HP);
    }
    public void apply(final Pyromancer receiver) {
        apply(receiver, PYROMANCER_HP);
    }
    public void apply(final Rogue receiver) {
        apply(receiver, ROGUE_HP);
    }
    public void apply(final Wizard receiver) {
        apply(receiver, WIZARD_HP);
    }
}
