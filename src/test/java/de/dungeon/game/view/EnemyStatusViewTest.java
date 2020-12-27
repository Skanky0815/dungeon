package de.dungeon.game.view;

import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.rule.Dice;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class EnemyStatusViewTest extends ViewTestCase {

    @Test
    void renderShouldPrintTheEnemyStatus() {
        final var behavior = new Behavior();
        behavior.init("do nothing", 1, 20);
        final var behaviorList = new ArrayList<Behavior>() {{
            add(behavior);
        }};

        final var enemy = new Enemy("Enemy A", 15, 2, (Dodge) new Dodge(mock(Dice.class)).init(7), 2, behaviorList);

        final var view = new EnemyStatusView();
        view.setEnemy(enemy);
        view.render();

        final var output = """
                Name: Enemy A
                Leben: 15/15
                ---------------------------------------
                Aktionen: 2\tAusweichen: 7\tRüstung: 2
                ---------------------------------------
                Verhalten:
                \t[1-20] do nothing
                ---------------------------------------
                """;

        assertEquals(output, outContent.toString());
    }
}