package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.EnemyStatusView;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EnemyStatusCommandTest {

    @Test
    void doingShouldCallTheEnemyStatusView() {
        final var enemy = mock(Enemy.class);
        final var viewMock = mock(EnemyStatusView.class);

        (new EnemyStatusCommand(viewMock)).setEnemy(enemy).doing();

        verify(viewMock).setEnemy(enemy);
        verify(viewMock).render();
    }
}