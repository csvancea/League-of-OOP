package entities.angels.good;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class SmallAngel extends BasicAngel {
    private static final float KNIGHT_MODIFIER = 0.10f;
    private static final float PYROMANCER_MODIFIER = 0.15f;
    private static final float ROGUE_MODIFIER = 0.05f;
    private static final float WIZARD_MODIFIER = 0.10f;

    private static final int KNIGHT_HP = 10;
    private static final int PYROMANCER_HP = 15;
    private static final int ROGUE_HP = 20;
    private static final int WIZARD_HP = 25;

    public SmallAngel(final int x, final int y, final GameMap map) {
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
