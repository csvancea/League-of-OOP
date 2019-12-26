package entities.angels.good;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class Spawner extends BasicAngel {
    private static final int KNIGHT_HP = 200;
    private static final int PYROMANCER_HP = 150;
    private static final int ROGUE_HP = 180;
    private static final int WIZARD_HP = 120;

    public Spawner(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.GOOD;
        name = this.getClass().getSimpleName();
    }

    private void apply(final BasicHero receiver, final int angelHP) {
        if (receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
            receiver.setHP(angelHP);
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
