package map.surface;

public final class Land implements ISurface {
    @Override
    public SurfaceType getType() {
        return SurfaceType.LAND;
    }
}
