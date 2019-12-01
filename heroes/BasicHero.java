package heroes;

import abilities.IAbility;
import abilities.IPassive;
import map.GameMap;
import map.entity.IMapEntity;
import map.entity.MapEntityType;
import map.surface.ISurface;

import java.util.ArrayList;

public abstract class BasicHero implements IMapEntity {
    private static final int BASE_XP_FOR_LEVEL_UP = 250;
    private static final int MULTIPLIER_FOR_LEVEL_UP = 50;

    private int x, y;
    private int xp;
    private int level;
    private int hp;
    private int damageTaken;
    private GameMap map;
    private final ArrayList<IAbility> abilities;

    private BasicHero lastAttacker;
    private BasicHero passiveAttacker;
    private IPassive passivePenalty;

    public BasicHero() {
        abilities = new ArrayList<IAbility>();

        setMap(null);
        setPosition(-1, -1);
        setXP(0);
        setLevel(0);
        setHP(getMaxHP());
        setDamageTaken(0);
        setPassivePenalty(null, null);
    }

    @Override
    public final String toString() {
        String ret;
        if (isDead()) {
            ret = String.format("%c dead", getHeroType().toString().charAt(0));
        } else {
            ret = String.format("%c %d %d %d %d %d", getHeroType().toString().charAt(0), getLevel(), getXP(), getHP(), y, x);
        }
        return ret;
    }
    public abstract HeroType getHeroType();
    public abstract int getInitialHP();
    public abstract int getHPBonusPerLevel();
    public abstract float getLandModifier();
    public abstract void acceptAbility(IAbility ability);

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
        if (isDead()) {
            return;
        }

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

        if (!isDead()) {
            setHP(getMaxHP());
        }
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
    public final void onKill(final BasicHero attacked) {
        int bonusXP = Math.max(0, 200 - (getLevel() - attacked.getLevel()) * 40);
        increaseXP(bonusXP);
    }

    public final int getDamageTaken() {
        return damageTaken;
    }
    public final void setDamageTaken(final int damage) {
        this.damageTaken = damage;
    }
    public final void increaseDamageTaken(final int amount) {
        setDamageTaken(getDamageTaken() + amount);
    }
    public final void applyDamageTaken() {
        decreaseHP(getDamageTaken());
        setDamageTaken(0);
    }

    public final GameMap getMap() {
        return map;
    }
    public final void setMap(final GameMap map) {
        this.map = map;
    }

    public final ISurface getSurface() {
        return getMap().getSurface(x, y);
    }

    public final ArrayList<IAbility> getAbilities() {
        return abilities;
    }

    public final void setPassivePenalty(final BasicHero attacker, final IPassive action) {
        passiveAttacker = attacker;
        passivePenalty = action;
    }
    public final void applyPassivePenalty() {
        if (passivePenalty != null) {
            passivePenalty.apply(this);
        }
    }
    public final BasicHero getPassiveAttacker() {
        return passiveAttacker;
    }

    public final BasicHero getLastAttacker() {
        return lastAttacker;
    }

    public final void setLastAttacker(final BasicHero lastAttacker) {
        this.lastAttacker = lastAttacker;
    }
}
