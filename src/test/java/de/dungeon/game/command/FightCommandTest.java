package de.dungeon.game.command;

import de.dungeon.game.battle.EnemyAttack;
import de.dungeon.game.battle.PlayerAttack;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class FightCommandTest {

    @Test
    void doingShouldCallPlayerAttack() {
        final var playerAttack = mock(PlayerAttack.class);
        final var enemyAttack = mock(EnemyAttack.class);
        final var enemy = mock(Enemy.class);
        when(enemy.isAlive()).thenReturn(true);
        
        final var player = mock(Player.class);

        new FightCommand(mock(View.class), player, playerAttack, enemyAttack).setEnemy(enemy).doing();

        verify(playerAttack).attack(eq(enemy), eq(0));
        verify(enemyAttack).attack();;
    }

    @Test
    void doingShouldReturnTrueIfEnemyIsDead() {
        final var playerAttack = mock(PlayerAttack.class);
        final var enemyAttack = mock(EnemyAttack.class);
        final var enemy = mock(Enemy.class);
        when(enemy.isAlive()).thenReturn(false);

        final var player = mock(Player.class);

        assertTrue(new FightCommand(mock(View.class), player, playerAttack, enemyAttack).setEnemy(enemy).doing());
        verify(enemyAttack, never()).attack();
    }
}