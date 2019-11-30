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
public final class Map {
    private final ISurface[][] map;
    private final int maxX;
    private final int maxY;

    public Map(final int maxX, final int maxY) {
        this.map = new ISurface[maxX][maxY];
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void setType(final int x, final int y, final SurfaceType type) {
        this.map[x][y] = SurfaceFactory.getInstance().getSurface(type);
    }

    public void setSurface(final int x, final int y, final ISurface surface) {
        this.map[x][y] = surface;
    }
}
