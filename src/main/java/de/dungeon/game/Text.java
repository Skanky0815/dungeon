package de.dungeon.game;

import com.google.inject.Singleton;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

@Singleton
public class Text {

    private static final String INDICATOR_MISSING_RESOURCE = "Resource missing";
    private static final String INDICATOR_MISSING_KEY = "Key missing";

    private final ResourceBundle resourceBundle;

    public Text() {
        final var local = new Locale("de");
        resourceBundle = PropertyResourceBundle.getBundle("texts.main", local);
    }

    public String get(@NotNull final String key) {
        if (null != resourceBundle) {
            try {
                return resourceBundle.getString(key);
            } catch (final MissingResourceException e) {
                return INDICATOR_MISSING_KEY + key;
            }
        }
        return INDICATOR_MISSING_RESOURCE;
    }
}
