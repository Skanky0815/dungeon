package de.dungeon.game.character.enemy;

import com.google.inject.Injector;
import de.dungeon.game.rule.Damage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BehaviorFactoryTest {

    @Test
    void createShouldThrownAUnknownBehaviorTypeException() {
        final var exception = assertThrows(UnknownBehaviorTypeException.class, () -> {
            final var map = new HashMap<>() {{
                put("type", "woops");
            }};
            new BehaviorFactory(mock(Injector.class)).create(map);
        });

        assertEquals("Behavior type woops not implemented!", exception.getMessage());
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

        assertEquals(behavior, new BehaviorFactory(injector).create(map));
        verify(behavior).init(eq("some text"), eq(1), eq(5));
    }

    @Test
    void createShouldCreateADamageBehavior() throws Exception {
        final var behavior = mock(DamageBehavior.class);
        final var damage = mock(Damage.class);
        when(damage.init(1, 6, 0)).thenReturn(damage);
        when(behavior.setDamage(damage)).thenReturn(behavior);

        final var injector = mock(Injector.class);
        when(injector.getInstance(DamageBehavior.class)).thenReturn(behavior);
        when(injector.getInstance(Damage.class)).thenReturn(damage);

        final var map = new HashMap<>() {{
            put("type", "damage");
            put("min", 1);
            put("max", 5);
            put("text", "some text");
            put("damage", new HashMap<>() {{
                put("diceCount", 1);
                put("diceType", 6);
                put("modifier", 0);
            }});
        }};

        assertEquals(behavior, new BehaviorFactory(injector).create(map));
        verify(damage).init(eq(1), eq(6), eq(0));
        verify(behavior).init(eq("some text"), eq(1), eq(5));
        verify(behavior).setDamage(eq(damage));
    }
}