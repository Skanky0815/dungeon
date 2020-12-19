package de.dungeon.game.scenery;

import de.dungeon.game.FrontController;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.Command;
import de.dungeon.game.view.ViewTestCase;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SceneryTest extends ViewTestCase {

    @Test
    void runShouldCallTheControllerWithoutCommands() {
        final var controllerMock = mock(FrontController.class);
        when(controllerMock.action(eq("text\n"), any()))
                .thenAnswer((Answer<Boolean>) invocation -> {
                    SceneryCallback callback = invocation.getArgument(1);
                    callback.call("0");
                    return true;
                })
                .thenReturn(true);

        (new Scenery("key", controllerMock, "text")).run();

        assertThat(outContent.toString(), containsString("No option found for number 0\n"));
    }

    @Test
    void runShouldCallTheControllerWithCommandsAndEnemy() {
        final var controllerMock = mock(FrontController.class);
        when(controllerMock.action(eq("text enemy text\n[0] my command\n"), any()))
                .thenAnswer((Answer<Boolean>) invocation -> {
                    SceneryCallback callback = invocation.getArgument(1);
                    callback.call("0");
                    return true;
                })
                .thenReturn(false)
                .thenReturn(true);

        final Command commandStub = new Command("my command") {
            @Override
            protected boolean doing() {
                assertTrue(true);
                return false;
            }
        };

        final var commands = new ArrayList<Command>() {{
            add(commandStub);
        }};
        final var enemy = new Enemy("enemy", 15, 0, null, 0, null);

        (new Scenery("key", controllerMock, "text %s text", commands, enemy)).run();

        assertThat(outContent.toString(), containsString("Nächste Action"));
    }
}
