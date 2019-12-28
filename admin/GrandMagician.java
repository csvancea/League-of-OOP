package admin;

import entities.IEntity;
import entities.angels.BasicAngel;
import entities.heroes.BasicHero;
import fileio.implementations.FileWriter;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public final class GrandMagician {
    private static GrandMagician instance = null;
    private FileWriter fileWriter = null;

    private GrandMagician() { }

    public static GrandMagician getInstance() {
        if (instance == null) {
            instance = new GrandMagician();
        }
        return instance;
    }

    public FileWriter getFileWriter() {
        return fileWriter;
    }

    public void setFileWriter(final FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void onNotify(final String observer, final PropertyChangeEvent pce) {
        switch (observer) {
            case "angel":
                onAngelEvent(pce);
                break;
            case "hero":
                onHeroEvent(pce);
                break;
            default:
                break;
        }
    }

    private void onAngelEvent(final PropertyChangeEvent pce) {
        BasicAngel angel = (BasicAngel) pce.getSource();
        String message = null;

        switch (pce.getPropertyName()) {
            case "active":
                if ((Boolean) pce.getNewValue()) {
                    message = String.format("Angel %s was spawned at %d %d",
                            angel, angel.getY(), angel.getX());
                }
                break;
            case "interact":
                BasicHero hero = (BasicHero) pce.getNewValue();
                message = String.format("%s %s %s", angel, angel.getAction(), hero);
                break;
            default:
                message = "unknown property";
                break;
        }
        if (message != null) {
            try {
                fileWriter.writeWord(message);
                fileWriter.writeNewLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void onHeroEvent(final PropertyChangeEvent pce) {
        BasicHero hero = (BasicHero) pce.getSource();
        String message = null;

        switch (pce.getPropertyName()) {
            case "death":
                IEntity attackerEntity = (IEntity) pce.getNewValue();
                if (attackerEntity != null) {
                    switch (attackerEntity.getEntityType()) {
                        case HERO:
                            BasicHero attackerHero = (BasicHero) attackerEntity;
                            message = String.format("Player %s was killed by %s",
                                    hero, attackerHero);
                            break;
                        case ANGEL:
                            message = String.format("Player %s was killed by an angel", hero);
                            break;
                        default:
                            break;
                    }
                }
                break;
            case "revive":
                message = String.format("Player %s was brought to life by an angel", hero);
                break;
            case "setLevel":
                int newLevel = (Integer) pce.getNewValue();
                int oldLevel = (Integer) pce.getOldValue();

                if (newLevel > oldLevel) {
                    message = String.format("%s reached level %d", hero, newLevel);
                }
                break;
            default:
                message = "unknown property";
                break;
        }

        if (message != null) {
            try {
                fileWriter.writeWord(message);
                fileWriter.writeNewLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
