package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BehaviorFactoryTest {

    @Test
    void createShouldThrownAUnknownBehaviorTypeException() {
        assertThrows(UnknownBehaviorTypeException.class, () -> {
            final var map = new HashMap<>() {{
                put("type", "woops");
            }};
            final var typeMapper = mock(TypeMapper.class);
            when(typeMapper.createBehaviorByType(map)).thenThrow(UnknownBehaviorTypeException.class);

            new BehaviorFactory(mock(Injector.class), typeMapper).create(map);
        });
    }

    @Test
    void createShouldCreateABehavior() throws Exception {
        final var behavior = mock(DamageBehavior.class);
        final var injector = mock(Injector.class);
        when(injector.getInstance(Behavior.class)).thenReturn(behavior);

        final var map = new HashMap<>() {{
            put("min", 1);
            put("max", 5);
            put("text", "some text");
        }};

        assertEquals(behavior, new BehaviorFactory(injector, mock(TypeMapper.class)).create(map));
        verify(behavior).init(eq("some text"), eq(1), eq(5));
    }

    @Test
    void createShouldCreateADamageBehavior() throws Exception {
        final var behavior = mock(DamageBehavior.class);
        final var injector = mock(Injector.class);

        final var map = new HashMap<>() {{
            put("type", "damage");
            put("min", 1);
            put("max", 5);
            put("text", "some text");
        }};

        final var typeMapper = mock(TypeMapper.class);
        when(typeMapper.createBehaviorByType(map)).thenReturn(behavior);

        assertEquals(behavior, new BehaviorFactory(injector, typeMapper).create(map));
        verify(typeMapper).createBehaviorByType(eq(map));
    }
}