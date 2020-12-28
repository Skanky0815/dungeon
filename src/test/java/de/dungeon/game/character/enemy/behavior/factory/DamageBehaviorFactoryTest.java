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
        final var damageMapper = new DamageMapper();
        damageMapper.setDiceCount(1);
        damageMapper.setDiceType(6);
        damageMapper.setModifier(0);

        final var behaviorMapper = new BehaviorMapper();
        behaviorMapper.setType("damage");
        behaviorMapper.setMin(1);
        behaviorMapper.setMax(5);
        behaviorMapper.setText("some text");
        behaviorMapper.setDamageMapper(damageMapper);

        final var behavior = mock(DamageBehavior.class);
        final var damage = mock(Damage.class);
        when(behavior.setDamage(damage)).thenReturn(behavior);
        when(damage.init(1, 6, 0)).thenReturn(damage);

        final var injector = mock(Injector.class);
        when(injector.getInstance(DamageBehavior.class)).thenReturn(behavior);
        when(injector.getInstance(Damage.class)).thenReturn(damage);

        assertEquals(behavior, new DamageBehaviorFactory(injector).create(behaviorMapper));

        verify(behavior).setDamage(eq(damage));
        verify(damage).init(eq(1), eq(6), eq(0));
    }
}