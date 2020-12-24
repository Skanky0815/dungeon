package de.dungeon.game.command;

import com.ginsberg.junit.exit.ExpectSystemExit;
import de.dungeon.game.Text;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ExitCommandTest {

    @Test
    @ExpectSystemExit
    void doActionShouldExitTheGame() throws Exception {
        final var text = mock(Text.class);

        final var exist = new ExitCommand(text);
        exist.doAction();
    }
}
