package observers;

import entities.angels.BasicAngel;
import fileio.implementations.FileWriter;
import entities.heroes.BasicHero;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public final class AngelObserver implements PropertyChangeListener {
    private final FileWriter fileWriter;

    public AngelObserver(final FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pce) {
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
}
