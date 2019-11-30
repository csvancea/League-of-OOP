package heroes;

import map.entity.IMapEntity;
import map.entity.MapEntityType;

public abstract class BasicHero implements IMapEntity {
    private static final int BASE_XP_FOR_LEVEL_UP = 250;
    private static final int MULTIPLIER_FOR_LEVEL_UP = 50;

    private int x, y;
    private int xp;
    private int level;
    private int hp;

    public BasicHero() {
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

    final int getMaxHP() {
        return getInitialHP() + getLevel() * getHPBonusPerLevel();
    }
    final boolean isDead() {
        return getHP() == 0;
    }

    @Override
    public final MapEntityType getMapEntityType() {
        return MapEntityType.HERO;
    }

    final void goUp() {
        setPosition(getX(), getY() - 1);
    }
    final void goDown() {
        setPosition(getX(), getY() + 1);
    }
    final void goLeft() {
        setPosition(getX() - 1, getY());
    }
    final void goRight() {
        setPosition(getX() + 1, getY());
    }
    final int getX() {
        return x;
    }
    final int getY() {
        return y;
    }
    final void setPosition(final int newX, final int newY) {
        this.x = newX;
        this.y = newY;
    }

    final int getXP() {
        return xp;
    }
    final void setXP(final int newXP) {
        this.xp = newXP;

        while (newXP >= BASE_XP_FOR_LEVEL_UP + getLevel() * MULTIPLIER_FOR_LEVEL_UP) {
            increaseLevel();
        }
    }
    final void increaseXP(final int amount) {
        setXP(getXP() + amount);
    }

    final int getLevel() {
        return level;
    }
    final void setLevel(final int newLevel) {
        this.level = newLevel;
    }
    final void increaseLevel() {
        setLevel(getLevel() + 1);
    }

    final int getHP() {
        return hp;
    }
    final void setHP(final int newHP) {
        this.hp = Math.max(0, newHP);
    }
    final void increaseHP(final int amount) {
        setHP(getHP() + amount);
    }
    final void decreaseHP(final int amount) {
        setHP(getHP() - amount);
    }
}
