package admin;

import entities.IEntity;
import entities.heroes.BasicHero;
import fileio.implementations.FileWriter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public final class HeroObserver implements PropertyChangeListener {
    private final FileWriter fileWriter;

    public HeroObserver(final FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pce) {
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
