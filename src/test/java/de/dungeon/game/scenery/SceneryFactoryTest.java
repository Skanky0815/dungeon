package de.dungeon.game.scenery;

import de.dungeon.game.FrontController;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.EnemyFactory;
import de.dungeon.game.command.Command;
import de.dungeon.game.command.CommandFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SceneryFactoryTest {

    @Test
    void initShouldWork() throws Exception {
        final var player = mock(Player.class);
        final var controller = mock(FrontController.class);
        final var enemyFactory = mock(EnemyFactory.class);
        final var commandFactory = mock(CommandFactory.class);

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

        final var sceneries = (new SceneryFactory(player, controller, enemyFactory, commandFactory)).init();
        assertEquals(2, sceneries.size());
    }
}
