package de.dungeon.game.command;

import de.dungeon.game.character.player.PlayerBuilder;
import de.dungeon.game.character.enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertThat(command.getText(), containsString("Enemy A"));
        assertThat(command, instanceOf(EnemyCommand.class));
    }

    @ParameterizedTest
    @ValueSource(strings = {"melee", "range", "magic", "dodge"})
    void createShouldReturnAttributeTestCommand(final String attribute) throws Exception {
        final var commandData = new HashMap<String, Object>() {{
            put("command", "attribute_test");
            put("attribute", attribute);
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
    void createShouldThrownAUnknownPropertyException() {
        final var exception = assertThrows(UnknownPropertyException.class, () -> {
            final var commandData = new HashMap<String, Object>() {{
                put("command", "attribute_test");
                put("attribute", "woop");
                put("modifier", 5);
                put("text", "Command %s Text");
                put("do_text", "%s do Text");
                put("success", new HashMap<String, String>());
                put("failure", new HashMap<String, String>());
            }};

            commandFactory.create(commandData, null);
        });

        assertEquals("Property woop does not exists!", exception.getMessage());
    }

    @Test
    void createShouldReturnFightCommand() throws Exception {
        final var enemyStub = new Enemy("Enemy A", 15, 0, null, 0, null);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "fight");
        }};

        final var command = commandFactory.create(commandData, enemyStub);
        assertThat(command, instanceOf(FightCommand.class));
    }

    @Test
    void createShouldThrownAExceptionForUnknownCommand() {
        final var exception = assertThrows(UnknownCommandException.class, () -> {
            final var commandData = new HashMap<String, Object>() {{
                put("command", "woop");
            }};

            commandFactory.create(commandData, null);
        });

        assertEquals("Command woop does not exists!", exception.getMessage());
    }
}
