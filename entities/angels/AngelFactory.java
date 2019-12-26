package entities.angels;

import entities.angels.evil.DarkAngel;
import entities.angels.evil.Dracula;
import entities.angels.evil.TheDoomer;
import entities.angels.good.DamageAngel;
import entities.angels.good.GoodBoy;
import entities.angels.good.LevelUpAngel;
import entities.angels.good.LifeGiver;
import entities.angels.good.SmallAngel;
import entities.angels.good.Spawner;
import entities.angels.good.XPAngel;
import map.GameMap;

public final class AngelFactory {
    private AngelFactory() { }

    public static BasicAngel createAngel(final String angelName, final int x, final int y,
                                         final GameMap gameMap) {
        switch (angelName) {
        // Good:
            case "DamageAngel":
                return new DamageAngel(x, y, gameMap);
            case "GoodBoy":
                return new GoodBoy(x, y, gameMap);
            case "LevelUpAngel":
                return new LevelUpAngel(x, y, gameMap);
            case "LifeGiver":
                return new LifeGiver(x, y, gameMap);
            case "SmallAngel":
                return new SmallAngel(x, y, gameMap);
            case "Spawner":
                return new Spawner(x, y, gameMap);
            case "XPAngel":
                return new XPAngel(x, y, gameMap);
        // Evil:
            case "DarkAngel":
                return new DarkAngel(x, y, gameMap);
            case "Dracula":
                return new Dracula(x, y, gameMap);
            case "TheDoomer":
                return new TheDoomer(x, y, gameMap);
            default:
                return null;
        }
    }
}
