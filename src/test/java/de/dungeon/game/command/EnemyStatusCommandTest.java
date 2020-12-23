package de.dungeon.game.command;

import de.dungeon.game.character.enemy.Behavior;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.view.EnemyStatusView;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class EnemyStatusCommandTest {

    @Test
    void doingShouldCallTheEnemyStatusView() {
        final var enemy = new Enemy("Enemy A", 15, 0, new Dodge(7, 0), 2, new ArrayList<>());
        final var viewMock = mock(EnemyStatusView.class);

        (new EnemyStatusCommand(viewMock)).init(enemy).doing();

        verify(viewMock).setEnemy(enemy);
        verify(viewMock).render();
    }
}