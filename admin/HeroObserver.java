package admin;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public final class HeroObserver implements PropertyChangeListener {
    private static HeroObserver instance = null;

    private HeroObserver() { }

    public static HeroObserver getInstance() {
        if (instance == null) {
            instance = new HeroObserver();
        }
        return instance;
    }

    @Override
    public void propertyChange(final PropertyChangeEvent pce) {
        GrandMagician.getInstance().onNotify("hero", pce);
    }
}
