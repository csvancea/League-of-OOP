package admin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class AngelObserver implements PropertyChangeListener {
    private static AngelObserver instance = null;

    private AngelObserver() { }

    public static AngelObserver getInstance() {
        if (instance == null) {
            instance = new AngelObserver();
        }
        return instance;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pce) {
        GrandMagician.getInstance().onNotify("angel", pce);
    }
}
