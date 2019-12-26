package entities.angels.evil;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class Dracula extends BasicAngel {
    private static final float KNIGHT_MODIFIER = 0.20f;
    private static final float PYROMANCER_MODIFIER = 0.30f;
    private static final float ROGUE_MODIFIER = 0.10f;
    private static final float WIZARD_MODIFIER = 0.40f;

    private static final int KNIGHT_HP = 60;
    private static final int PYROMANCER_HP = 40;
    private static final int ROGUE_HP = 35;
    private static final int WIZARD_HP = 20;

    public Dracula(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.EVIL;
        name = this.getClass().getSimpleName();
    }

    private void apply(final BasicHero receiver, final float angelModifier, final int angelHP) {
        if (!receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
            receiver.setLastAttacker(this);
            receiver.decreaseAdditiveModifier(angelModifier);
            receiver.decreaseHP(angelHP);
        }
    }

    public void apply(final Knight receiver) {
        apply(receiver, KNIGHT_MODIFIER, KNIGHT_HP);
    }
    public void apply(final Pyromancer receiver) {
        apply(receiver, PYROMANCER_MODIFIER, PYROMANCER_HP);
    }
    public void apply(final Rogue receiver) {
        apply(receiver, ROGUE_MODIFIER, ROGUE_HP);
    }
    public void apply(final Wizard receiver) {
        apply(receiver, WIZARD_MODIFIER, WIZARD_HP);
    }
}
