package de.dungeon.game.character.enemy.behavior.factory;

import com.google.inject.Injector;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import de.dungeon.game.rule.Damage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DamageBehaviorFactoryTest {

    @Test
    void createShouldReturnADamageBehaviorWithDamage() {
        final var damageMapper = DamageMapper.build(1, 6, 0);
        final var behaviorMapper = BehaviorMapper.build("some text", 1,  5, "damage", damageMapper);

        final var behavior = mock(DamageBehavior.class);
        final var damage = mock(Damage.class);
        when(behavior.setDamage(damage)).thenReturn(behavior);
        when(damage.init(damageMapper)).thenReturn(damage);

        final var injector = mock(Injector.class);
        when(injector.getInstance(DamageBehavior.class)).thenReturn(behavior);
        when(injector.getInstance(Damage.class)).thenReturn(damage);

        assertEquals(behavior, new DamageBehaviorFactory(injector).create(behaviorMapper));

        verify(behavior).setDamage(eq(damage));
        verify(damage).init(eq(damageMapper));
    }
}