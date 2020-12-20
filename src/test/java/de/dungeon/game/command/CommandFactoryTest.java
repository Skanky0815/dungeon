package de.dungeon.game.command;

import de.dungeon.game.character.PlayerBuilder;
import de.dungeon.game.character.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {

    private CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        final var playerSub = PlayerBuilder.build("Player a", 1, 1, 1, 1).get();
        commandFactory = new CommandFactory(playerSub);
    }

    @Test
    void createShouldReturnAGoCommand() throws Exception {
        final var commandData = new HashMap<String, Object>() {{
            put("command", "go");
            put("text", "Command Text");
            put("do_text", "Command Do Text");
        }};

        final var command = commandFactory.create(commandData, null);
        assertEquals("Command Text", command.getText());
        assertTrue(command.doing());
    }

    @Test
    void createShouldReturnEnemyStatusCommand() throws Exception {
        final var enemyStub = new Enemy("Enemy A", 15, 0, null, 0, null);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "npc_status");
        }};

        final var command = commandFactory.create(commandData, enemyStub);
        assertEquals("Command Text", command.getText());
    }

    @Test
    void createShouldReturnAttributeTestCommand() throws Exception {
        final var commandData = new HashMap<String, Object>() {{
            put("command", "attribute_test");
        }};

        final var command = commandFactory.create(commandData, null);
        assertEquals("Command Text", command.getText());
    }

    @Test
    void createShouldReturnFightCommand() throws Exception {
        final var enemyStub = new Enemy("Enemy A", 15, 0, null, 0, null);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "fight");
        }};

        final var command = commandFactory.create(commandData, enemyStub);
        assertEquals("Command Text", command.getText());
    }
}
