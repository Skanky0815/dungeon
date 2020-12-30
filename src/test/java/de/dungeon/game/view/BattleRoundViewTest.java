package de.dungeon.game.view;

import de.dungeon.game.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BattleRoundViewTest extends ViewTestCase {

    @Test
    void renderShouldPrintMessageWithParams() {
        final var text = mock(Text.class);
        when(text.get("my_message")).thenReturn("my %s message %d\n");

        final var view = new View(text);
        view.render("my_message", "nice", 42);

        assertEquals("my nice message 42\n", outContent.toString());
    }

    @Test
    void renderShouldPrintMessageWithoutParams() {
        final var text = mock(Text.class);
        when(text.get("my_message")).thenReturn("my message\n");

        final var view = new View(text);
        view.render("my_message", null);

        assertEquals("my message\n", outContent.toString());
    }

    @Test
    void renderShouldPrintMessage() {
        final var text = mock(Text.class);
        final var view = new View(text);
        view.render("my message");

        assertEquals("my message", outContent.toString());
        verify(text, never()).get(anyString());
    }
}