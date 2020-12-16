package Character.Enemy;

import Character.Property.Dodge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyTest {

    private Enemy enemy;

    @BeforeEach
    void setUp() {
        final List<Behavior> behaviorList = new ArrayList<>(Arrays.asList(
            new Behavior("a", 1, 5),
            new Behavior("b", 6, 10),
            new Behavior("c", 11, 20)
        ));
        enemy = new Enemy("enemy", 15, 0, new Dodge(5), 1, behaviorList);
    }

    @Test
    void getBehaviorShouldReturnTheBBehaviorInRange() {
        assertEquals("b", enemy.getBehavior(7).getText());
    }
}
