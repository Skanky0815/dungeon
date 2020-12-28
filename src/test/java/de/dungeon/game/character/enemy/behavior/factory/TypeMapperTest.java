package de.dungeon.game.character.enemy.behavior.factory;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TypeMapperTest {

    @Test
    void createBehaviorByTypeShouldThrownAUnknownBehaviorTypeException() {
        final var exception = assertThrows(UnknownBehaviorTypeException.class, () -> {
            final var map = new HashMap<>() {{
               put("type", "woops");
            }};

            new TypeMapper(mock(DamageBehaviorFactory.class)).createBehaviorByType(map);
        });

        assertEquals("Behavior type woops not implemented!", exception.getMessage());
    }

    @Test
    void createBehaviorByTypeShouldReturnADamageBehavior() throws UnknownBehaviorTypeException {
        final var damageBehaviorFactory = mock(DamageBehaviorFactory.class);
        final var map = new HashMap<>() {{
            put("type", "damage");
        }};

        new TypeMapper(damageBehaviorFactory).createBehaviorByType(map);

        verify(damageBehaviorFactory).create(eq(map));
    }
}