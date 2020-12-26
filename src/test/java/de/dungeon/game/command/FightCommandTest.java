package de.dungeon.game.command;

import de.dungeon.game.battle.EnemyAttack;
import de.dungeon.game.battle.PlayerAttack;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.player.PlayerBuilder;
import de.dungeon.game.character.property.Dodge;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

class FightCommandTest {

    @Test
    void doingShouldCallPlayerAttack() {
        final var playerAttackMock = mock(PlayerAttack.class);
        final var enemyAttackMock = mock(EnemyAttack.class);
        final var enemy = new Enemy("Enemy", 15, 0, new Dodge(7, 0), 1, new ArrayList<>());
        final var player = PlayerBuilder.build("Player", 1, 1, 1, 1).get();

        (new FightCommand(player, playerAttackMock, enemyAttackMock)).init(enemy).doing();

        verify(playerAttackMock).attack(eq(enemy), eq(0));
    }
}