package Character.Enemy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnemyFactoryTest {

    @Test
    void createShouldLoadAEnemyFromTheJSONFile() throws Exception {
        final EnemyFactory factory = new EnemyFactory();

        final var enemy = factory.create("Eber");

        assertEquals("Eber", enemy.getName());
        assertEquals(15, enemy.getHealth());
        assertEquals(0, enemy.getArmor());
        assertEquals(5, enemy.getDodge().getValue());
        assertEquals(1, enemy.getActions());
    }
}
