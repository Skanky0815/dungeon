package de.dungeon.game.character.enemy;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.DamageBehavior;
import de.dungeon.game.character.enemy.behavior.factory.BehaviorFactory;
import de.dungeon.game.rule.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EnemyFactoryTest {

    @Test
    void createShouldLoadAEnemyFromTheJSONFile() throws Exception {
        final var behaviorFactory = mock(BehaviorFactory.class);
        final var damageBehavior = mock(DamageBehavior.class);

        when(behaviorFactory.create(anyMap())).thenReturn(damageBehavior, mock(Behavior.class), mock((Behavior.class)));

        final var dice = mock(Dice.class);
        final var factory = new EnemyFactory(new ObjectMapper(), dice, behaviorFactory);

        final var enemy = factory.create("example");
        assertEquals("enemy", enemy.getName());
        assertEquals(15, enemy.getHealth());
        assertEquals(0, enemy.getArmor());
        assertEquals(5, enemy.getDodge().getValue());
        assertEquals(1, enemy.getActions());
        verify(damageBehavior).setEnemy(eq(enemy));
    }
}
