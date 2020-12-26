package de.dungeon.game.character.property;

import de.dungeon.game.rule.Dice;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class PropertyTest {

    @Test
    void testShouldSuccess() {
        final var dice = mock(Dice.class);
        when(dice.rollD20()).thenReturn(15);

        final Property property = new Property(dice);
        property.init(10, 0);
        assertTrue(property.test(5).isSuccess());
    }
}
