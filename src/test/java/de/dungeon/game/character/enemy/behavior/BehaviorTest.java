package de.dungeon.game.character.enemy.behavior;

import de.dungeon.game.character.enemy.behavior.factory.BehaviorMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BehaviorTest {

    private Behavior behavior;

    @BeforeEach
    void setUp() {
        behavior = new Behavior();
        behavior.init(BehaviorMapper.build("behavior text", 5, 10));
    }

    @Test
    void isInRangeShouldReturnTrueIfValueIsGreaterThenTheMin() {
        assertTrue(behavior.isInRange(5));
    }

    @Test
    void isInRangeShouldReturnFalseIfValueIsLessThenTheMin() {
        assertFalse(behavior.isInRange(3));
    }

    @Test
    void isInRangeShouldReturnTrueIfValueIsLessThenTheMax() {
        assertTrue(behavior.isInRange(9));
    }

    @Test
    void isInRangeShouldReturnFalseIfValueIsGreaterThenTheMax() {
        assertFalse(behavior.isInRange(11));
    }
}
