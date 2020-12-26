package de.dungeon.game.character.enemy;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dungeon.game.rule.Dice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class EnemyFactoryTest {

    private EnemyFactory factory;

    @BeforeEach
    void setUp() {
        final var dice = mock(Dice.class);
        factory = new EnemyFactory(new ObjectMapper(), dice);
    }

    @Test
    void createShouldLoadAEnemyFromTheJSONFile() throws Exception {
        final var enemy = factory.create("Eber");
        assertEquals("Eber", enemy.getName());
        assertEquals(15, enemy.getHealth());
        assertEquals(0, enemy.getArmor());
        assertEquals(5, enemy.getDodge().getValue());
        assertEquals(1, enemy.getActions());
    }

    @Test
    void createShouldLoadAndInitTheBehaviorWithDamage() throws Exception {
        final var enemy = factory.create("Eber");
        final var behavior = enemy.getBehavior(1);
        final var damage = behavior.getDamage();

        assertEquals("Der Eber greift dich mit seinen Hauern direkt an", behavior.getText());
        assertEquals(1, damage.getDiceCount());
        assertEquals(6, damage.getDiceType());
        assertEquals(4, damage.getModifier());
    }
}
