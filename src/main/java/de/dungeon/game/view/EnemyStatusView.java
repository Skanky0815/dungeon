package de.dungeon.game.view;

import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.Enemy;
import org.jetbrains.annotations.NotNull;

public class EnemyStatusView implements View {

    private Enemy enemy;

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
                """;

        System.out.printf(
                text,
                enemy.getName(),
                enemy.getHealth(),
                enemy.getMaxHealth(),
                enemy.getActions(),
                enemy.getDodge().getValue(),
                enemy.getArmor(),
                setupBehaviors()
        );
    }

    private StringBuilder setupBehaviors() {
        final var behaviors = new StringBuilder();
        for (Behavior behavior : enemy.getBehaviorList()) {
            behaviors.append("\n\t[%d-%d] %s".formatted(behavior.getMin(), behavior.getMax(), behavior.getText()));
        }
        return behaviors;
    }
}
