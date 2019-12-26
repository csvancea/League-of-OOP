package abilities;

public final class Utils {
    private Utils() { }

    public static float adjustHeroModifier(final float originalHeroModifier, final float adjust) {
        float adjustedHeroModifier = originalHeroModifier;
        if (originalHeroModifier != 1.00f) {
            adjustedHeroModifier += adjust;
        }
        return adjustedHeroModifier;
    }
}
