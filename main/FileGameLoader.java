package main;

import entities.angels.AngelFactory;
import entities.angels.BasicAngel;
import fileio.implementations.FileReader;
import entities.heroes.BasicHero;
import entities.heroes.HeroFactory;
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
    private final List<List<BasicAngel>> roundAngels;

    public FileGameLoader(final FileReader fileReader) throws IOException {
        int mapLines = fileReader.nextInt();
        int mapColumns = fileReader.nextInt();

        this.fileReader = fileReader;
        this.gameMap = GameMap.getInstance(); // in prima instanta foloseam dependency injection
        this.gameMap.initMap(mapColumns, mapLines); // dar etapa 2 impune sa folosesc singleton
        this.heroesList = new ArrayList<BasicHero>();
        this.roundMoves = new ArrayList<List<Character>>();
        this.roundAngels = new ArrayList<List<BasicAngel>>();

        loadMap();
        loadHeroes();
        loadRounds();
        loadAngels();
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
            hero.setId(i);
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

    private void loadAngels() throws IOException {
        int numRounds = roundMoves.size();
        for (int i = 0; i != numRounds; ++i) {
            ArrayList<BasicAngel> angels = new ArrayList<BasicAngel>();
            int numAngels = fileReader.nextInt();

            for (int j = 0; j != numAngels; ++j) {
                String angelLine = fileReader.nextWord();
                String[] tokens = angelLine.split(",");

                String angelName = tokens[0];
                int posY = Integer.parseInt(tokens[1]);
                int posX = Integer.parseInt(tokens[2]);

                angels.add(AngelFactory.createAngel(angelName, posX, posY, getGameMap()));
            }

            roundAngels.add(angels);
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

    @Override
    public List<List<BasicAngel>> getRoundAngels() {
        return roundAngels;
    }
}
