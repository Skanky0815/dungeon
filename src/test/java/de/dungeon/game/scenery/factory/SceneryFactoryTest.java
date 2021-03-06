package de.dungeon.game.scenery.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.factory.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.factory.CommandFactory;
import de.dungeon.game.scenery.EnemyScenery;
import de.dungeon.game.scenery.Scenery;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SceneryFactoryTest {

    @Test
    void initShouldWork() throws Exception {
        final var player = mock(Player.class);
        final var injector = mock(Injector.class);
        final var enemyFactory = mock(EnemyFactory.class);
        final var commandFactory = mock(CommandFactory.class);

        when(enemyFactory.create(anyString())).thenReturn(mock(Enemy.class));

        when(commandFactory.create(any(), any(Enemy.class))).thenReturn((new Command(mock(View.class)) {
            @Override
            protected boolean doing() {
                return false;
            }
        }).init("a", "do a"));
        when(commandFactory.create(any(), any())).thenReturn((new Command(mock(View.class)) {
            @Override
            protected boolean doing() {
                return false;
            }
        }).init("b", "do b"));

        final var sceneryA = mock(Scenery.class);
        when(sceneryA.init(anyString(), anyString(), any())).thenReturn(sceneryA);
        final var sceneryB = mock(EnemyScenery.class);
        when(sceneryB.init(anyString(), anyString(), any(), any(Enemy.class))).thenReturn(sceneryB);

        when(injector.getInstance(Scenery.class)).thenReturn(sceneryA);
        when(injector.getInstance(EnemyScenery.class)).thenReturn(sceneryB);

        final var sceneries = new SceneryFactory(injector, player, enemyFactory, commandFactory).init();
        assertEquals(2, sceneries.size());
    }
}
