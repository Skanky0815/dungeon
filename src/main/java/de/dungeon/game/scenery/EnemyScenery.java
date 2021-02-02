package de.dungeon.game.scenery;

import com.google.inject.Inject;
import de.dungeon.game.FrontController;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.command.Command;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EnemyScenery extends Scenery {

    private Enemy enemy;

    @Inject
    public EnemyScenery(@NotNull final FrontController controller, @NotNull final View view) {
        super(controller, view);
    }

    public Scenery init(
            @NotNull final String key,
            @NotNull String text,
            @NotNull final List<Command> commands,
            @NotNull final Enemy enemy
    ) {
        text = text.formatted(enemy.getName());
        this.enemy = enemy;
        return init(key, text, commands);
    }
}
