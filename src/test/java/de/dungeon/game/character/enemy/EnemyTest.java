package de.dungeon.game.character.enemy;

import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.factory.BehaviorMapper;
import de.dungeon.game.character.enemy.factory.EnemyMapper;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class EnemyTest {

    private Enemy enemy;

    @BeforeEach
    void setUp() {
        final List<Behavior> behaviorList = new ArrayList<>(Arrays.asList(
            createBehavior("a", 1, 5),
            createBehavior("b", 6, 10),
            createBehavior("c", 11, 20)
        ));
        final var enemyMapper = EnemyMapper.build("enemy", 15, 0, 1);
        enemy = new Enemy(enemyMapper, mock(Dodge.class), behaviorList);
    }

    @Test
    void getBehaviorShouldReturnTheBehaviorInRange() {
        assertEquals("b", enemy.getBehavior(7).getText());
    }

    @Test
    void isAliveShouldTrueIfEnemyHastMoreThen0Health() {
        assertTrue(enemy.isAlive());
    }

    @Test
    void isAliveShouldFalseIfEnemyIsDead() {
        enemy.takeDamage(16);
        assertFalse(enemy.isAlive());
    }

    private Behavior createBehavior(final String text, final int min, final int max) {
        final var behavior = new Behavior(mock(View.class));
        behavior.init(BehaviorMapper.build(text, min, max));
        return behavior;
    }
}
