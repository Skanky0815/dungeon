package de.dungeon.game.command;

import de.dungeon.game.character.PlayerBuilder;
import de.dungeon.game.view.PlayerStatusView;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PlayerStatusCommandTest {

    @Test
    void doActionShouldCallTheRenderMethodOfTheView() {
        final var player = (new PlayerBuilder()).build("player", 1, 1, 1, 1).get();
        final var viewMock = mock(PlayerStatusView.class);

        final var command = new PlayerStatusCommand(viewMock);
        command.setPlayer(player);
        command.doAction();

        verify(viewMock).setPlayer(player);
        verify(viewMock).render();
    }
}
