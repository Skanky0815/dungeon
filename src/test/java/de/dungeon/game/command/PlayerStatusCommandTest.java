package de.dungeon.game.command;

import de.dungeon.game.view.PlayerStatusView;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PlayerStatusCommandTest {

    @Test
    void doActionShouldCallTheRenderMethodOfTheView() throws Exception {
        final var viewMock = mock(PlayerStatusView.class);

        (new PlayerStatusCommand(viewMock)).doAction();

        verify(viewMock).render();
    }
}
