package de.dungeon.game.command;

import de.dungeon.game.character.property.Property;
import de.dungeon.game.rule.TestResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttributeTestCommandTest {

    @Test
    void doingShouldReturnTrueAndCallTheProperty() {
        final var propertyMock = mock(Property.class);
        when(propertyMock.test(42)).thenReturn(new TestResult(2, 6));

        final var command = (new AttributeTestCommand()).init("text", "do text", 42, propertyMock);

        assertTrue(command.doing());
        verify(propertyMock).test(42);
    }
}