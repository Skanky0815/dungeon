package de.dungeon.game.command;

import com.ginsberg.junit.exit.ExpectSystemExit;
import org.junit.jupiter.api.Test;

public class ExitCommandTest {

    @Test
    @ExpectSystemExit
    void doActionShouldExitTheGame() throws Exception {
        final var exist = new ExitCommand();
        exist.doAction();
    }
}
