package main;

import fileio.implementations.FileReader;
import heroes.BasicHero;
import heroes.HeroFactory;
import map.GameMap;
import map.surface.SurfaceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileGameLoader implements IGameLoader {
    private final FileReader fileReader;

    private final GameMap gameMap;
    private final List<BasicHero> heroesList;
    private final List<List<Character>> roundMoves;

    public FileGameLoader(final FileReader fileReader) throws IOException {
        int mapLines = fileReader.nextInt();
        int mapColumns = fileReader.nextInt();

        this.fileReader = fileReader;
        this.gameMap = new GameMap(mapColumns, mapLines);
        this.heroesList = new ArrayList<BasicHero>();
        this.roundMoves = new ArrayList<List<Character>>();

        loadMap();
        loadHeroes();
        loadRounds();
    }

    private void loadMap() throws IOException {
        for (int y = 0; y != gameMap.getMaxY(); ++y) {
            String mapLine = fileReader.nextWord();
            for (int x = 0; x != gameMap.getMaxX(); ++x) {
                char surfaceChar = mapLine.charAt(x);

                gameMap.setSurface(x, y, SurfaceFactory.getSurface(surfaceChar));
            }
        }
    }

    private void loadHeroes() throws IOException {
        int numHeroes = fileReader.nextInt();
        for (int i = 0; i != numHeroes; ++i) {
            char heroChar = fileReader.nextWord().charAt(0);
            int posY = fileReader.nextInt();
            int posX = fileReader.nextInt();

            BasicHero hero = HeroFactory.getHero(heroChar);
            hero.setMap(gameMap);
            hero.setPosition(posX, posY);

            heroesList.add(hero);
        }
    }

    private void loadRounds() throws IOException {
        int numRounds = fileReader.nextInt();
        for (int i = 0; i != numRounds; ++i) {
            ArrayList<Character> movesSequence = new ArrayList<Character>();

            String roundLine = fileReader.nextWord();
            for (int j = 0; j != heroesList.size(); ++j) {
                char move = roundLine.charAt(j);
                movesSequence.add(move);
            }

            roundMoves.add(movesSequence);
        }
    }

    @Override
    public GameMap getGameMap() {
        return gameMap;
    }

    @Override
    public List<BasicHero> getHeroesList() {
        return heroesList;
    }

    @Override
    public List<List<Character>> getRoundMoves() {
        return roundMoves;
    }
}
