package de.dungeon.game.command;

import com.google.inject.Inject;
import de.dungeon.game.battle.EnemyAttack;
import de.dungeon.game.battle.PlayerAttack;
import de.dungeon.game.character.Player;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class FightCommand extends EnemyCommand {

    private final Player player;
    private final PlayerAttack playerAttack;
    private final EnemyAttack enemyAttack;

    @Inject
    public FightCommand(
            @NotNull final View view,
            @NotNull final Player player,
            @NotNull final PlayerAttack playerAttack,
            @NotNull final EnemyAttack enemyAttack
    ) {
        super(view);
        this.player = player;
        this.playerAttack = playerAttack;
        this.enemyAttack = enemyAttack;
    }

    @Override
    public FightCommand setEnemy(@NotNull final Enemy enemy) {
        super.init("%s kämpft!".formatted(player.getName()), "%s angreifen.".formatted(enemy.getName()));
        enemyAttack.setEnemy(enemy);
        return (FightCommand) super.setEnemy(enemy);
    }

    @Override
    protected boolean doing() {
        view.render("game.battle.try_to_attack", player.getName(), enemy.getName());
        playerAttack.attack(enemy, 0);
        if (enemy.isAlive()) {
            enemyAttack.attack();
            return false;
        }

        return true;
    }
}
