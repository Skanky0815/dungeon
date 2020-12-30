package de.dungeon.game;

import de.dungeon.game.command.ExitCommand;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FrontControllerTest {

    @Test
    void actionShouldCallTheExitCommand() throws Exception {
        final var exitCommand = mock(ExitCommand.class);
        final var bufferReader = mock(BufferedReader.class);
        when(bufferReader.readLine()).thenReturn("x");
        when(exitCommand.getText()).thenReturn("Quit the game.");
        when(exitCommand.init()).thenReturn(exitCommand);

        final var view = mock(View.class);

        new FrontController(exitCommand, bufferReader, view).action("What will you do?", null);

        verify(exitCommand).doAction();
        verify(view).render(contains("What will you do?"));
        verify(view).render(contains("Quit the game."));
    }

    @Test
    void actionShouldCallTheCallback() throws Exception {
        final var exitCommand = mock(ExitCommand.class);
        final var bufferReader = mock(BufferedReader.class);
        when(bufferReader.readLine()).thenReturn("input");
        when(exitCommand.init()).thenReturn(exitCommand);

        final var view = mock(View.class);

        new FrontController(exitCommand, bufferReader, view).action("What will you do?", (final String input) -> {
            assertEquals("input", input);
        });
    }
}
