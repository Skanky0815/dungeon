package de.dungeon.game.command.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.player.PlayerBuilder;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.AttributeTestCommand;
import de.dungeon.game.command.EnemyStatusCommand;
import de.dungeon.game.command.FightCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandFactoryTest {

    private Injector injectorMock;

    @BeforeEach
    void setUp() {
        injectorMock = mock(Injector.class);
    }

    @Test
    void createShouldReturnAGoCommand() throws Exception {
        final var commandData = new HashMap<String, Object>() {{
            put("command", "go");
            put("text", "Command Text");
            put("do_text", "Command Do Text");
        }};

        final var command = factory().create(commandData);
        assertEquals("Command Text", command.getText());
        assertTrue(command.doAction());
    }

    @Test
    void createShouldReturnEnemyStatusCommand() throws Exception {
        final var enemy = mock(Enemy.class);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "npc_status");
        }};

        final var enemyStatusMock = mock(EnemyStatusCommand.class);
        when(injectorMock.getInstance(EnemyStatusCommand.class)).thenReturn(enemyStatusMock);
        when(enemyStatusMock.setEnemy(enemy)).thenReturn(enemyStatusMock);

        final var command = factory().create(commandData, enemy);
        assertThat(command, instanceOf(EnemyStatusCommand.class));
        verify(enemyStatusMock).setEnemy(enemy);
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

        final var attributeTestCommand = mock(AttributeTestCommand.class);
        when(injectorMock.getInstance(AttributeTestCommand.class)).thenReturn(attributeTestCommand);
        when(attributeTestCommand.init(eq("Command Player A Text"), eq("Player A do Text"), eq(5), any()))
                .thenReturn(attributeTestCommand);

        final var command = factory().create(commandData);
        assertThat(command, instanceOf(AttributeTestCommand.class));
        verify(attributeTestCommand).init(eq("Command Player A Text"), eq("Player A do Text"), eq(5), any());
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

            factory().create(commandData);
        });

        assertEquals("Property woop does not exists!", exception.getMessage());
    }

    @Test
    void createShouldReturnFightCommand() throws Exception {
        final var enemy = mock(Enemy.class);
        final var commandData = new HashMap<String, Object>() {{
            put("command", "fight");
        }};

        final var fightCommand = mock(FightCommand.class);
        when(injectorMock.getInstance(FightCommand.class)).thenReturn(fightCommand);
        when(fightCommand.setEnemy(enemy)).thenReturn(fightCommand);

        final var command = factory().create(commandData, enemy);
        assertThat(command, instanceOf(FightCommand.class));
        verify(fightCommand).setEnemy(enemy);
    }

    @Test
    void createShouldThrownAExceptionForUnknownCommand() {
        final var exception = assertThrows(UnknownCommandException.class, () -> {
            final var commandData = new HashMap<String, Object>() {{
                put("command", "woop");
            }};

            factory().create(commandData, mock(Enemy.class));
        });

        assertEquals("Command woop does not exists!", exception.getMessage());
    }

    private CommandFactory factory() {
        final var playerSub = PlayerBuilder.build("Player A", 1, 1, 1, 1).get();
        return new CommandFactory(playerSub, injectorMock);
    }
}
