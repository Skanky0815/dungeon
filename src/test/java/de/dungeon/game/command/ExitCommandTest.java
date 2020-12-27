package de.dungeon.game.command;

import com.ginsberg.junit.exit.ExpectSystemExit;
import de.dungeon.game.Text;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ExitCommandTest {

    @Test
    @ExpectSystemExit
    void doActionShouldExitTheGame() {
        final var text = mock(Text.class);
        when(text.get("game.exit")).thenReturn("bye");

        final var exist = new ExitCommand(text);
        exist.init();

        verify(text).get("game.exit");

        exist.doing();
    }
}
