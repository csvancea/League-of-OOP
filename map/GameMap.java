package map;

import map.surface.ISurface;
import map.surface.SurfaceFactory;
import map.surface.SurfaceType;

/**
 * Map - Reprezinta harta jocului.
 *
 * Coordonate:
 *   * consider punctul (x=0, y=0) ca fiind punctul din coltul stanga-sus
 *   * axa X: coloanele
 *   * axa Y: liniile
 */
public final class GameMap {
    private final ISurface[][] map;
    private final int maxX;
    private final int maxY;

    public GameMap(final int maxX, final int maxY) {
        this.map = new ISurface[maxX][maxY];
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void setSurface(final int x, final int y, final SurfaceType surfaceType) {
        this.map[x][y] = SurfaceFactory.getSurface(surfaceType);
    }

    public void setSurface(final int x, final int y, final ISurface surface) {
        this.map[x][y] = surface;
    }

    public ISurface getSurface(final int x, final int y) {
        return this.map[x][y];
    }
}
