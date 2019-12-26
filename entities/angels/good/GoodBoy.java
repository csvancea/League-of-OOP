package entities.angels.good;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class GoodBoy extends BasicAngel {
    private static final float KNIGHT_MODIFIER = 0.40f;
    private static final float PYROMANCER_MODIFIER = 0.50f;
    private static final float ROGUE_MODIFIER = 0.40f;
    private static final float WIZARD_MODIFIER = 0.30f;

    private static final int KNIGHT_HP = 20;
    private static final int PYROMANCER_HP = 30;
    private static final int ROGUE_HP = 40;
    private static final int WIZARD_HP = 50;

    public GoodBoy(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.GOOD;
        name = this.getClass().getSimpleName();
    }

    private void apply(final BasicHero receiver, final float angelModifier, final int angelHP) {
        if (!receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
            receiver.increaseAdditiveModifier(angelModifier);
            receiver.increaseHP(angelHP);
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
