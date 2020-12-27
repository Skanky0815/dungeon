package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.battle.EnemyAttack;
import de.dungeon.game.battle.PlayerAttack;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import org.jetbrains.annotations.NotNull;

public class FightCommand extends EnemyCommand {

    private final Player player;
    private final PlayerAttack playerAttack;
    private final EnemyAttack enemyAttack;

    @Inject
    public FightCommand(
            @NotNull final Player player,
            @NotNull final PlayerAttack playerAttack,
            @NotNull final EnemyAttack enemyAttack
    ) {
        this.player = player;
        this.playerAttack = playerAttack;
        this.enemyAttack = enemyAttack;
    }

    public FightCommand init(@NotNull final Enemy enemy) {
        super.setEnemy(enemy);
        super.init("%s kämpft!".formatted(player.getName()), "%s angreifen.".formatted(enemy.getName()));
        enemyAttack.setEnemy(enemy);
        return this;
    }

    @Override
    protected boolean doing() {
        System.out.printf("%s versucht %s anzugreifen\n", player.getName(), enemy.getName());
        playerAttack.attack(enemy, 0);
        if (enemy.isAlive()) {
            enemyAttack.attack();
            return false;
        }

        return true;
    }
}
