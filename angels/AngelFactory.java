package angels;

import map.GameMap;

public final class AngelFactory {
    private AngelFactory() { }

    public static BasicAngel createAngel(final String angelName, final int x, final int y,
                                         final GameMap gameMap) {
        switch (angelName) {
            default:
                return null;
        }
    }
}
