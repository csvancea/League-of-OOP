package map.surface;

public final class SurfaceFactory {
    private static SurfaceFactory instance = null;
    private SurfaceFactory() { }

    public static SurfaceFactory getInstance() {
        if (instance == null) {
            instance = new SurfaceFactory();
        }
        return instance;
    }

    public ISurface getSurface(final SurfaceType type) {
        switch (type) {
            case LAND:
                return new Land();
            case VOLCANIC:
                return new Volcanic();
            case DESERT:
                return new Desert();
            case WOODS:
                return new Woods();
            default:
                return null;
        }
    }

}
