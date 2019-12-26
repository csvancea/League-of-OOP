package entities.heroes;

public enum HeroType {
    KNIGHT("Knight"),
    PYROMANCER("Pyromancer"),
    ROGUE("Rogue"),
    WIZARD("Wizard");

    private final String name;

    HeroType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
