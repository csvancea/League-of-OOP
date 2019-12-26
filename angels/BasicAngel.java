package angels;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;
import map.GameMap;
import map.entity.IMapEntity;
import map.entity.MapEntityType;

public abstract class BasicAngel implements IMapEntity {
    private final int x, y;
    private final GameMap map;
    protected String name;
    protected AngelType type;

    public BasicAngel(final int x, final int y, final GameMap map) {
        this.x = x;
        this.y = y;
        this.map = map;

        map.getEntities(x, y).add(this);
    }

    @Override
    public final MapEntityType getMapEntityType() {
        return MapEntityType.ANGEL;
    }

    public final String getName() {
        return name;
    }

    public final AngelType getType() {
        return type;
    }

    public final String getAction() {
        switch (getType()) {
            case GOOD:
                return "helped";
            case EVIL:
                return "hit";
            default:
                return "unknown";
        }
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    /**
     * Aplica functionalitatea unui erou de tip X.
     * Implementare cu: double-dispatch
     */
    public abstract void apply(Knight receiver);
    public abstract void apply(Pyromancer receiver);
    public abstract void apply(Rogue receiver);
    public abstract void apply(Wizard receiver);
}
