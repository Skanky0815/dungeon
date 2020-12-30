package de.dungeon.game.character.enemy.behavior;

import de.dungeon.game.character.enemy.behavior.factory.BehaviorMapper;
import de.dungeon.game.view.View;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BehaviorTest {

    private View view;

    @BeforeEach
    void setUp() {
        view = mock(View.class);
    }

    @Test
    void isInRangeShouldReturnTrueIfValueIsGreaterThenTheMin() {
        assertTrue(behavior().isInRange(5));
    }

    @Test
    void isInRangeShouldReturnFalseIfValueIsLessThenTheMin() {
        assertFalse(behavior().isInRange(3));
    }

    @Test
    void isInRangeShouldReturnTrueIfValueIsLessThenTheMax() {
        assertTrue(behavior().isInRange(9));
    }

    @Test
    void isInRangeShouldReturnFalseIfValueIsGreaterThenTheMax() {
        assertFalse(behavior().isInRange(11));
    }

    @Test
    void doBehaviorShouldCallTheViewRender() {
        behavior().doBehavior();
        verify(view).render(eq("behavior text"));
    }

    private Behavior behavior() {
        final var behavior = new Behavior(view);
        behavior.init(BehaviorMapper.build("behavior text", 5, 10));
        return behavior;
    }
}
