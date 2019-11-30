package map.surface;

public final class Land implements ISurface {
    @Override
    public SurfaceType getSurfaceType() {
        return SurfaceType.LAND;
    }
}
