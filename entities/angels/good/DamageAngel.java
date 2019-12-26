package entities.angels.good;

import entities.angels.AngelType;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;

public final class DamageAngel extends BasicAngel {
    private static final float KNIGHT_MODIFIER = 0.15f;
    private static final float PYROMANCER_MODIFIER = 0.20f;
    private static final float ROGUE_MODIFIER = 0.30f;
    private static final float WIZARD_MODIFIER = 0.40f;

    public DamageAngel(final int x, final int y, final GameMap map) {
        super(x, y, map);

        type = AngelType.GOOD;
        name = "DamageAngel";
    }

    private void apply(final BasicHero receiver, final float angelModifier) {
        if (!receiver.isDead()) {
            support.firePropertyChange("interact", null, receiver);
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
