package de.dungeon.game;

import org.junit.jupiter.api.Test;

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
        final var text = new Text("en");

        assertEquals("Beenden", text.get("game.exit"));
    }

}