package de.dungeon.game.character.enemy.behavior.factory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypeMapperTest {

    @Test
    void createBehaviorByTypeShouldThrownAUnknownBehaviorTypeException() {
        final var exception = assertThrows(UnknownBehaviorTypeException.class, () -> {
            final var mapper = new BehaviorMapper();
            mapper.setType("woops");

            new TypeMapper(mock(DamageBehaviorFactory.class), mock(EscapeBehaviorFactory.class)).createBehaviorByType(mapper);
        });

        assertEquals("Behavior type woops not implemented!", exception.getMessage());
    }

    @Test
    void createBehaviorByTypeShouldReturnADamageBehavior() throws UnknownBehaviorTypeException {
        final var damageBehaviorFactory = mock(DamageBehaviorFactory.class);
        final var mapper = new BehaviorMapper();
        mapper.setType("damage");

        new TypeMapper(damageBehaviorFactory, mock(EscapeBehaviorFactory.class)).createBehaviorByType(mapper);

        verify(damageBehaviorFactory).create(eq(mapper));
    }

    @Test
    void createBehaviorByTypeShouldReturnAEscapeBehavior() throws UnknownBehaviorTypeException {
        final var escapeBehaviorFactory = mock(EscapeBehaviorFactory.class);
        final var mapper = new BehaviorMapper();
        mapper.setType("escape");

        new TypeMapper(mock(DamageBehaviorFactory.class), escapeBehaviorFactory).createBehaviorByType(mapper);

        verify(escapeBehaviorFactory).create(eq(mapper));
    }
}