package de.dungeon.game.character.enemy.behavior;

import de.dungeon.game.battle.DamageHandler;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.Damage;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DamageBehaviorTest {

    @Test
    void doBehaviorShouldCallTheDamageHandler() throws Exception {
        final var damage = mock(Damage.class);
        when(damage.calculateDamage()).thenReturn(42);
        final var enemy = mock(Enemy.class);

        final var damageHandler = mock(DamageHandler.class);
        final var player = mock(Player.class);

        final var behavior = new DamageBehavior(damageHandler, player).setDamage(damage);
        behavior.setEnemy(enemy);
        behavior.doBehavior();
        verify(damageHandler).makeDamage(eq(42), eq(enemy), eq(player));
    }
}