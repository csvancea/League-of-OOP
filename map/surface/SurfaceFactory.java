package map.surface;

public final class SurfaceFactory {
    private SurfaceFactory() { }

    public static ISurface getSurface(final SurfaceType type) {
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

    public static ISurface getSurface(final char type) {
        switch (type) {
            case 'L':
                return new Land();
            case 'V':
                return new Volcanic();
            case 'D':
                return new Desert();
            case 'W':
                return new Woods();
            default:
                return null;
        }
    }

}
