package main;

import fileio.implementations.FileReader;
import fileio.implementations.FileWriter;
import heroes.BasicHero;

public final class Main {
    private Main() { }

    public static void main(final String[] args) {
        try {
            FileReader fileReader = new FileReader(args[0]);
            FileWriter fileWriter = new FileWriter(args[1]);

            IGameLoader gameLoader = new FileGameLoader(fileReader);
            GameLogic gameLogic = new GameLogic(gameLoader);
            gameLogic.play();

            for (BasicHero hero : gameLogic.getHeroesList()) {
                fileWriter.writeWord(hero.toString());
                fileWriter.writeNewLine();
            }

            fileWriter.writeNewLine();
            fileWriter.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
