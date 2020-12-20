package de.dungeon.game.command;

import de.dungeon.game.character.PlayerBuilder;
import de.dungeon.game.character.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CommandFactoryTest {

    private CommandFactory commandFactory;

    @BeforeEach
    void setUp() {
        final var playerSub = PlayerBuilder.build("Player A", 1, 1, 1, 1).get();
        commandFactory = new CommandFactory(playerSub);
    }

    @Test
    void createShouldReturnAGoCommand() {
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
    void createShouldReturnEnemyStatusCommand() {
        final var enemyStub = new Enemy("Enemy A", 15, 0, null, 0, null);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "npc_status");
        }};

        final var command = commandFactory.create(commandData, enemyStub);
        assertThat(command.getText(), containsString("Enemy A"));
        assertThat(command, instanceOf(EnemyCommand.class));
    }

    @Test
    void createShouldReturnAttributeTestCommand() {
        final var commandData = new HashMap<String, Object>() {{
            put("command", "attribute_test");
            put("attribute", "agility");
            put("modifier", 5);
            put("text", "Command %s Text");
            put("do_text", "%s do Text");
            put("success", new HashMap<String, String>());
            put("failure", new HashMap<String, String>());
        }};

        final var command = commandFactory.create(commandData, null);
        assertEquals("Command Player A Text", command.getText());
        assertThat(command, instanceOf(AttributeTestCommand.class));
    }

    @Test
    void createShouldReturnFightCommand() {
        final var enemyStub = new Enemy("Enemy A", 15, 0, null, 0, null);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "fight");
        }};

        final var command = commandFactory.create(commandData, enemyStub);
        assertThat(command, instanceOf(FightCommand.class));
    }
}
