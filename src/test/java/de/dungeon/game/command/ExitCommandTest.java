package de.dungeon.game.command;

import com.ginsberg.junit.exit.ExpectSystemExit;
import org.junit.jupiter.api.Test;

public class ExitCommandTest {

    @Test
    @ExpectSystemExit
    void doActionShouldExitTheGame() {
        final var exist = new ExitCommand("Bye Bye", "und weg");
        exist.doAction();
    }
}
