package de.dungeon.game.battle;

import de.dungeon.game.character.enemy.Behavior;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.rule.Dice;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class EnemyAttackTest {

    @Test
    void attackShouldCallTheDamageHandler() {
        final var dice = mock(Dice.class);
        when(dice.rollD20()).thenReturn(5);

        final var behavior = mock(Behavior.class);
        final var enemy = mock(Enemy.class);
        when(enemy.getActions()).thenReturn(1);
        when(enemy.getBehavior(eq(5))).thenReturn(behavior);

        final var enemyAttack = new EnemyAttack(dice);
        enemyAttack.setEnemy(enemy);
        enemyAttack.attack();

        verify(behavior).doBehavior();
    }

    @Test
    void attackShouldCallTheDamageHandlerTwice() {
        final var dice = mock(Dice.class);
        when(dice.rollD20()).thenReturn(1, 10);

        final var behavior = mock(Behavior.class);
        final var enemy = mock(Enemy.class);
        when(enemy.getActions()).thenReturn(1);
        when(enemy.getBehavior(anyInt())).thenReturn(behavior);

        final var enemyAttack = new EnemyAttack(dice);
        enemyAttack.setEnemy(enemy);
        enemyAttack.attack();

        verify(behavior, times(2)).doBehavior();
    }

    @Test
    void attackShouldDoNothingIfA20IsRolled() {
        final var dice = mock(Dice.class);
        when(dice.rollD20()).thenReturn(20);

        final var enemy = mock(Enemy.class);
        when(enemy.getActions()).thenReturn(1);

        final var enemyAttack = new EnemyAttack(dice);
        enemyAttack.setEnemy(enemy);
        enemyAttack.attack();

        verify(enemy, never()).getBehavior(anyInt());
    }
}