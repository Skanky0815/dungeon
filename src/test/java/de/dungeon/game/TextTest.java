package de.dungeon.game;

import org.junit.jupiter.api.Test;

import java.util.MissingResourceException;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    @Test
    void getShouldReturnAStringFromPropertyFile() {
        final var text = new Text("de");

        assertEquals("Beenden", text.get("game.exit"));
    }

    @Test
    void getShouldReturnAMissingKeyMessage() {
        final var text = new Text("de");

        assertEquals("Missing key: woops", text.get("woops"));
    }

    @Test
    void getShouldReturnTheFoundedLang() {
        final var exception = assertThrows(MissingResourceException.class, () -> {
            final var text = new Text("en");

            assertEquals("Resource missing", text.get("game.exit"));
        });

        assertEquals("Can't find bundle for base name texts.main, locale en", exception.getMessage());
    }
}