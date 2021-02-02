package de.dungeon.game.scenery;

import de.dungeon.game.FrontController;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.Command;
import de.dungeon.game.scenery.factory.SceneryCallback;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnemySceneryTest {

    @Test
    void runShouldCallTheControllerWithCommandsAndEnemy() throws Exception {
        final var controllerMock = mock(FrontController.class);
        when(controllerMock.action(eq("text enemy text\n[0] my command\n"), any()))
                .thenAnswer((Answer<Boolean>) invocation -> {
                    SceneryCallback callback = invocation.getArgument(1);
                    callback.call("0");
                    return true;
                })
                .thenReturn(false)
                .thenReturn(true);

        final Command commandStub = (new Command(mock(View.class)) {
            @Override
            protected boolean doing() {
                assertTrue(true);
                return false;
            }
        }).init("my command", null);

        final var commands = new ArrayList<Command>() {{
            add(commandStub);
        }};
        final var enemy = mock(Enemy.class);
        when(enemy.getName()).thenReturn("enemy");

        new EnemyScenery(controllerMock, mock(View.class)).init("key", "text %s text", commands, enemy).run();
    }
}
