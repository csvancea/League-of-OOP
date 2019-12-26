package entities.heroes;

public final class HeroFactory {
    private HeroFactory() { }

    public static BasicHero getHero(final HeroType heroType) {
        switch (heroType) {
            case KNIGHT:
                return new Knight();
            case PYROMANCER:
                return new Pyromancer();
            case ROGUE:
                return new Rogue();
            case WIZARD:
                return new Wizard();
            default:
                return null;
        }
    }

    public static BasicHero getHero(final char ch) {
        switch (ch) {
            case 'K':
                return new Knight();
            case 'P':
                return new Pyromancer();
            case 'R':
                return new Rogue();
            case 'W':
                return new Wizard();
            default:
                return null;
        }
    }
}
