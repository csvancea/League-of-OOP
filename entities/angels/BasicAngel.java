package entities.angels;

import entities.heroes.Knight;
import entities.heroes.Pyromancer;
import entities.heroes.Rogue;
import entities.heroes.Wizard;
import map.GameMap;
import entities.IEntity;
import entities.EntityType;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class BasicAngel implements IEntity {
    private final int x, y;
    private final GameMap map;
    protected String name;
    protected AngelType type;
    private boolean active;
    protected final PropertyChangeSupport support;

    public BasicAngel(final int x, final int y, final GameMap map) {
        this.x = x;
        this.y = y;
        this.map = map;
        this.support = new PropertyChangeSupport(this);

        setActive(false);
        map.getEntities(x, y).add(this);
    }

    @Override
    public final EntityType getEntityType() {
        return EntityType.ANGEL;
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

    public final void setActive(final boolean active) {
        support.firePropertyChange("active", this.active, active);
        this.active = active;
    }

    public final boolean isActive() {
        return active;
    }

    public final void addPropertyChangeListener(final PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public final void removePropertyChangeListener(final PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
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
