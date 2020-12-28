package de.dungeon.game.scenery.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.factory.Factory;
import de.dungeon.game.scenery.Scenery;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FactoryTest {

    @Test
    void initShouldWork() throws Exception {
        final var player = mock(Player.class);
        final var injector = mock(Injector.class);
        final var enemyFactory = mock(EnemyFactory.class);
        final var commandFactory = mock(Factory.class);

        when(enemyFactory.create(anyString())).thenReturn(mock(Enemy.class));

        when(commandFactory.create(any(), any(Enemy.class))).thenReturn((new Command() {
            @Override
            protected boolean doing() {
                return false;
            }
        }).init("a", "do a"));
        when(commandFactory.create(any(), any())).thenReturn((new Command() {
            @Override
            protected boolean doing() {
                return false;
            }
        }).init("b", "do b"));

        final var sceneryA = mock(Scenery.class);
        when(sceneryA.init(anyString(), anyString(), any())).thenReturn(sceneryA);
        final var sceneryB = mock(Scenery.class);
        when(sceneryB.init(anyString(), anyString(), any())).thenReturn(sceneryB);

        when(injector.getInstance(Scenery.class)).thenReturn(sceneryA, sceneryB);

        final var sceneries = (new de.dungeon.game.scenery.factory.Factory(injector, player, enemyFactory, commandFactory)).init();
        assertEquals(2, sceneries.size());
    }
}
