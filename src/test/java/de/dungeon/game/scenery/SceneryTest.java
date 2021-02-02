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
import static org.mockito.Mockito.*;

public class SceneryTest {

    @Test
    void runShouldCallTheControllerWithoutCommands() throws Exception {
        final var controllerMock = mock(FrontController.class);
        when(controllerMock.action(eq("text\n"), any()))
                .thenAnswer((Answer<Boolean>) invocation -> {
                    SceneryCallback callback = invocation.getArgument(1);
                    callback.call("0");
                    return true;
                })
                .thenReturn(true);

        final var view = mock(View.class);

        new Scenery(controllerMock, view).init("key", "text", new ArrayList<>()).run();

        verify(view).render(eq("game.scenery.option_not_found"), eq(0));
    }
}
