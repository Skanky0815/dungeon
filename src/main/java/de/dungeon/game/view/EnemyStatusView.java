package de.dungeon.game.view;

import com.google.inject.Inject;
import de.dungeon.game.Text;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.Enemy;
import org.jetbrains.annotations.NotNull;

public class EnemyStatusView extends View {

    private Enemy enemy;

    @Inject
    public EnemyStatusView(@NotNull final Text text) {
        super(text);
    }

    public void setEnemy(@NotNull final Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void render() {
        final var text = """
                Name: %s
                Leben: %d/%d
                ---------------------------------------
                Aktionen: %d\tAusweichen: %d\tRüstung: %d
                ---------------------------------------
                Verhalten:%s
                ---------------------------------------
                """.formatted(
                        enemy.getName(),
                        enemy.getHealth(),
                        enemy.getMaxHealth(),
                        enemy.getActions(),
                        enemy.getDodge().getValue(),
                        enemy.getArmor(),
                        setupBehaviors()
                );

        render(text);
    }

    private StringBuilder setupBehaviors() {
        final var behaviors = new StringBuilder();
        for (Behavior behavior : enemy.getBehaviorList()) {
            behaviors.append("\n\t[%d-%d] %s".formatted(behavior.getMin(), behavior.getMax(), behavior.getText()));
        }
        return behaviors;
    }
}
