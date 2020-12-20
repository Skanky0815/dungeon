package de.dungeon.game.scenery;

import de.dungeon.game.FrontController;
import de.dungeon.game.character.PlayerBuilder;
import de.dungeon.game.character.enemy.EnemyFactory;
import de.dungeon.game.command.CommandFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SceneryFactoryTest {

    @Test
    void initShouldWork() throws Exception {
        final var player = PlayerBuilder.build("Ruhindil", 1, 1, 1, 1).get();
        final var controller = mock(FrontController.class);
        final var enemyFactory = mock(EnemyFactory.class);
        final var commandFactory = mock(CommandFactory.class);

        final var sceneries = (new SceneryFactory(player, controller, enemyFactory, commandFactory)).init();
        assertEquals(3, sceneries.size());
    }
}