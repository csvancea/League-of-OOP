package angels.good;

import angels.AngelType;
import angels.BasicAngel;
import heroes.BasicHero;
import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import map.GameMap;

public final class LifeGiver extends BasicAngel {
    private static final int KNIGHT_HP = 100;
    private static final int PYROMANCER_HP = 80;
    private static final int ROGUE_HP = 90;
    private static final int WIZARD_HP = 120;

    public LifeGiver(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.GOOD;
        name = "LifeGiver";
    }

    private void apply(final BasicHero receiver, final int angelHP) {
        if (!receiver.isDead()) {
            receiver.increaseHP(angelHP);
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
