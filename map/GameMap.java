package map;

import map.entity.IMapEntity;
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
    private final Cell[][] map;
    private final int maxX;
    private final int maxY;

    public GameMap(final int maxX, final int maxY) {
        this.map = new Cell[maxX][maxY];
        this.maxX = maxX;
        this.maxY = maxY;

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

    public ArrayList<IMapEntity> getEntities(final int x, final int y) {
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
        private final ArrayList<IMapEntity> entities;

        private Cell() {
            entities = new ArrayList<IMapEntity>();
        }

        private ISurface getSurface() {
            return surface;
        }

        private void setSurface(final ISurface surface) {
            this.surface = surface;
        }

        private ArrayList<IMapEntity> getEntities() {
            return entities;
        }
    }
}
