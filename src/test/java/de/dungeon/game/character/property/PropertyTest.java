package de.dungeon.game.character.property;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyTest {

    @Test
    void testShouldSuccess() {
        final Property property = new Property(10, 0);
        assertTrue(property.test(20).isSuccess());
    }
}
