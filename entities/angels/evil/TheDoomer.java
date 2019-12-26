package entities.angels.evil;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class TheDoomer extends BasicAngel {
    public TheDoomer(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.EVIL;
        name = this.getClass().getSimpleName();
    }

    private void applyEffect(final BasicHero receiver) {
        if (!receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
            receiver.setLastAttacker(this);
            receiver.setHP(0);
        }
    }

    public void apply(final Knight receiver) {
        applyEffect(receiver);
    }
    public void apply(final Pyromancer receiver) {
        applyEffect(receiver);
    }
    public void apply(final Rogue receiver) {
        applyEffect(receiver);
    }
    public void apply(final Wizard receiver) {
        applyEffect(receiver);
    }
}
