package heroes;

import map.GameMap;
import map.entity.IMapEntity;
import map.entity.MapEntityType;

public abstract class BasicHero implements IMapEntity {
    private static final int BASE_XP_FOR_LEVEL_UP = 250;
    private static final int MULTIPLIER_FOR_LEVEL_UP = 50;

    private int x, y;
    private int xp;
    private int level;
    private int hp;
    private GameMap map;

    public BasicHero() {
        setMap(null);
        setPosition(-1, -1);
        setXP(0);
        setLevel(0);
        setHP(getMaxHP());
    }

    @Override
    public abstract String toString();
    public abstract HeroType getHeroType();
    public abstract int getInitialHP();
    public abstract int getHPBonusPerLevel();

    public final int getMaxHP() {
        return getInitialHP() + getLevel() * getHPBonusPerLevel();
    }
    public final boolean isDead() {
        return getHP() == 0;
    }

    @Override
    public final MapEntityType getMapEntityType() {
        return MapEntityType.HERO;
    }

    public final void goUp() {
        setPosition(getX(), getY() - 1);
    }
    public final void goDown() {
        setPosition(getX(), getY() + 1);
    }
    public final void goLeft() {
        setPosition(getX() - 1, getY());
    }
    public final void goRight() {
        setPosition(getX() + 1, getY());
    }
    public final int getX() {
        return x;
    }
    public final int getY() {
        return y;
    }
    public final void setPosition(final int newX, final int newY) {
        if (getMap() != null) {
            if (this.x != -1 && this.y != -1) {
                getMap().getEntities(this.x, this.y).remove(this);
            }
            if (newX != -1 && newY != -1) {
                getMap().getEntities(newX, newY).add(this);
            }
        }

        this.x = newX;
        this.y = newY;
    }

    public final int getXP() {
        return xp;
    }
    public final void setXP(final int newXP) {
        this.xp = newXP;

        while (newXP >= BASE_XP_FOR_LEVEL_UP + getLevel() * MULTIPLIER_FOR_LEVEL_UP) {
            increaseLevel();
        }
    }
    public final void increaseXP(final int amount) {
        setXP(getXP() + amount);
    }

    public final int getLevel() {
        return level;
    }
    public final void setLevel(final int newLevel) {
        this.level = newLevel;
    }
    public final void increaseLevel() {
        setLevel(getLevel() + 1);
    }

    public final int getHP() {
        return hp;
    }
    public final void setHP(final int newHP) {
        this.hp = Math.max(0, newHP);
    }
    public final void increaseHP(final int amount) {
        setHP(getHP() + amount);
    }
    public final void decreaseHP(final int amount) {
        setHP(getHP() - amount);
    }

    public final GameMap getMap() {
        return map;
    }
    public final void setMap(final GameMap map) {
        this.map = map;
    }
}
