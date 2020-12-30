package de.dungeon.game.command;

import de.dungeon.game.character.property.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AttributeTestCommandTest {

    @Test
    void doingShouldReturnTrueAndCallTheProperty() {
        final var propertyMock = mock(Property.class);
        when(propertyMock.isTestSuccessfully(42)).thenReturn(true);

        final var command = new AttributeTestCommand().init("text", "do text", 42, propertyMock);

        assertTrue(command.doing());
        verify(propertyMock).isTestSuccessfully(42);
    }
}