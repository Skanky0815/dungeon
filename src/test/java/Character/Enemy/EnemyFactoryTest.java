package Character.Enemy;

import org.junit.jupiter.api.Test;

public class EnemyFactoryTest {

    @Test
    void createShouldLoadAEnemyFromTheJmlFile() throws Exception {
        final EnemyFactory factory = new EnemyFactory();

        System.out.println(factory.create("Eber").getName());
    }
}
