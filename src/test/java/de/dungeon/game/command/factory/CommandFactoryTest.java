package de.dungeon.game.command.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.player.PlayerBuilder;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.AttributeTestCommand;
import de.dungeon.game.command.EnemyStatusCommand;
import de.dungeon.game.command.FightCommand;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        final var mapper = new CommandMapper();
        mapper.setCommand("go");
        mapper.setText("Command Text");
        mapper.setDoText("Command Do Text");

        when(injectorMock.getInstance(View.class)).thenReturn(mock(View.class));

        final var command = factory().create(mapper);
        assertEquals("Command Text", command.getText());
        assertTrue(command.doAction());
    }

    @Test
    void createShouldReturnEnemyStatusCommand() throws Exception {
        final var enemy = mock(Enemy.class);
        final var mapper = new CommandMapper();
        mapper.setCommand("npc_status");

        final var enemyStatusMock = mock(EnemyStatusCommand.class);
        when(injectorMock.getInstance(EnemyStatusCommand.class)).thenReturn(enemyStatusMock);
        when(enemyStatusMock.setEnemy(enemy)).thenReturn(enemyStatusMock);

        final var command = factory().create(mapper, enemy);
        assertThat(command, instanceOf(EnemyStatusCommand.class));
        verify(enemyStatusMock).setEnemy(enemy);
    }

    @ParameterizedTest
    @ValueSource(strings = {"melee", "range", "magic", "dodge"})
    void createShouldReturnAttributeTestCommand(final String attribute) throws Exception {
        final var mapper = new CommandMapper();
        mapper.setCommand("attribute_test");
        mapper.setAttribute(attribute);
        mapper.setModifier(5);
        mapper.setText("Command %s Text");
        mapper.setDoText("%s do Text");

        final var attributeTestCommand = mock(AttributeTestCommand.class);
        when(injectorMock.getInstance(AttributeTestCommand.class)).thenReturn(attributeTestCommand);
        when(attributeTestCommand.init(eq("Command Player A Text"), eq("Player A do Text"), eq(5), any()))
                .thenReturn(attributeTestCommand);

        final var command = factory().create(mapper);
        assertThat(command, instanceOf(AttributeTestCommand.class));
        verify(attributeTestCommand).init(eq("Command Player A Text"), eq("Player A do Text"), eq(5), any());
    }

    @Test
    void createShouldThrownAUnknownPropertyException() {
        final var exception = assertThrows(UnknownPropertyException.class, () -> {
            final var mapper = new CommandMapper();
            mapper.setCommand("attribute_test");
            mapper.setAttribute("woop");
            mapper.setModifier(5);
            mapper.setText("Command %s Text");
            mapper.setDoText("%s do Text");

            factory().create(mapper);
        });

        assertEquals("Property woop does not exists!", exception.getMessage());
    }

    @Test
    void createShouldReturnFightCommand() throws Exception {
        final var enemy = mock(Enemy.class);
        final var mapper = new CommandMapper();
        mapper.setCommand("fight");

        final var fightCommand = mock(FightCommand.class);
        when(injectorMock.getInstance(FightCommand.class)).thenReturn(fightCommand);
        when(fightCommand.setEnemy(enemy)).thenReturn(fightCommand);

        final var command = factory().create(mapper, enemy);
        assertThat(command, instanceOf(FightCommand.class));
        verify(fightCommand).setEnemy(enemy);
    }

    @Test
    void createShouldThrownAExceptionForUnknownCommand() {
        final var exception = assertThrows(UnknownCommandException.class, () -> {
            final var mapper = new CommandMapper();
            mapper.setCommand("woop");

            factory().create(mapper, mock(Enemy.class));
        });

        assertEquals("Command woop does not exists!", exception.getMessage());
    }

    private CommandFactory factory() {
        final var playerSub = PlayerBuilder.build("Player A", 1, 1, 1, 1).get();
        return new CommandFactory(playerSub, injectorMock);
    }
}
