package de.dungeon.game;

import de.dungeon.game.command.ExitCommand;
import de.dungeon.game.view.ViewTestCase;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FrontControllerTest extends ViewTestCase {

    @Test
    void actionShouldCallTheExitCommand() throws Exception {
        final var exitCommand = mock(ExitCommand.class);
        final var bufferReader = mock(BufferedReader.class);
        when(bufferReader.readLine()).thenReturn("x");
        when(exitCommand.getText()).thenReturn("Quit the game.");
        when(exitCommand.init()).thenReturn(exitCommand);

        (new FrontController(exitCommand, bufferReader)).action("What will you do?", null);

        verify(exitCommand).doAction();
        assertThat(outContent.toString(), containsString("What will you do?"));
        assertThat(outContent.toString(), containsString("Quit the game."));
    }

    @Test
    void actionShouldCallTheCallback() throws Exception {
        final var exitCommand = mock(ExitCommand.class);
        final var bufferReader = mock(BufferedReader.class);
        when(bufferReader.readLine()).thenReturn("input");
        when(exitCommand.init()).thenReturn(exitCommand);

        (new FrontController(exitCommand, bufferReader)).action("What will you do?", (final String input) -> {
            assertEquals("input", input);
        });
    }
}
