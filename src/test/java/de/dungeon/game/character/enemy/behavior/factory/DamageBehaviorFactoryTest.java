package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import de.dungeon.game.rule.Damage;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DamageBehaviorFactoryTest {

    @Test
    void createShouldReturnADamageBehaviorWithDamage() {
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

        final var behavior = mock(DamageBehavior.class);
        final var damage = mock(Damage.class);
        when(behavior.setDamage(damage)).thenReturn(behavior);
        when(damage.init(1, 6, 0)).thenReturn(damage);

        final var injector = mock(Injector.class);
        when(injector.getInstance(DamageBehavior.class)).thenReturn(behavior);
        when(injector.getInstance(Damage.class)).thenReturn(damage);

        assertEquals(behavior, new DamageBehaviorFactory(injector).create(map));

        verify(behavior).setDamage(eq(damage));
        verify(damage).init(eq(1), eq(6), eq(0));
    }
}