package de.dungeon.game.battle;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.player.Weapon;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.rule.UnknownDiceException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class PlayerAttackTest {

    @Test
    void attackShouldAttackWithAWeapon() throws Exception {
        final var weapon = mock(Weapon.class);
        when(weapon.getDamage()).thenReturn(4);

        final var player = mock(Player.class);
        when(player.getWeapon(1)).thenReturn(weapon);
        when(player.tryToAttackWithWeapon(weapon)).thenReturn(true);

        final var enemy = new Enemy("Enemy", 15, 2, new Dodge(0, 0), 1, new ArrayList<>());
        final var damageHandler = mock(DamageHandler.class);

        (new PlayerAttack(player, damageHandler)).attack(enemy, 1);

        verify(damageHandler).makeDamage(eq(4), eq(player), eq(enemy));
    }

    @Test
    void attackShouldDoNotingBecauseException() throws UnknownDiceException {
        final var weapon = mock(Weapon.class);
        when(weapon.getDamage()).thenThrow(new UnknownDiceException(3));

        final var player = mock(Player.class);
        when(player.getWeapon(1)).thenReturn(weapon);
        when(player.tryToAttackWithWeapon(weapon)).thenReturn(true);

        final var enemy = new Enemy("Enemy", 15, 2, new Dodge(0, 0), 1, new ArrayList<>());
        final var damageHandler = mock(DamageHandler.class);

        (new PlayerAttack(player, damageHandler)).attack(enemy, 1);
        verify(damageHandler, never()).makeDamage(anyInt(), eq(player), eq(enemy));
    }
}