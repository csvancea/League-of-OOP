package map;

import entities.IEntity;
import map.surface.ISurface;
import map.surface.SurfaceFactory;
import map.surface.SurfaceType;

import java.util.ArrayList;

/**
 * Map - Reprezinta harta jocului.
 *
 * Coordonate:
 *   * consider punctul (x=0, y=0) ca fiind punctul din coltul stanga-sus
 *   * axa X: coloanele
 *   * axa Y: liniile
 */
public final class GameMap {
    private static GameMap instance = null;

    private Cell[][] map;
    private int maxX;
    private int maxY;

    private GameMap() { }

    public static GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    public void initMap(final int maxXVal, final int maxYVal) {
        this.maxX = maxXVal;
        this.maxY = maxYVal;
        this.map = new Cell[maxX][maxY];

        for (int x = 0; x != maxX; ++x) {
            for (int y = 0; y != maxY; ++y) {
                this.map[x][y] = new Cell();
            }
        }
    }

    public void setSurface(final int x, final int y, final SurfaceType surfaceType) {
        this.map[x][y].setSurface(SurfaceFactory.getSurface(surfaceType));
    }

    public void setSurface(final int x, final int y, final ISurface surface) {
        this.map[x][y].setSurface(surface);
    }

    public ISurface getSurface(final int x, final int y) {
        return this.map[x][y].getSurface();
    }

    public ArrayList<IEntity> getEntities(final int x, final int y) {
        return this.map[x][y].getEntities();
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    private static final class Cell {
        private ISurface surface;
        private final ArrayList<IEntity> entities;

        private Cell() {
            entities = new ArrayList<IEntity>();
        }

        private ISurface getSurface() {
            return surface;
        }

        private void setSurface(final ISurface surface) {
            this.surface = surface;
        }

        private ArrayList<IEntity> getEntities() {
            return entities;
        }
    }
}
