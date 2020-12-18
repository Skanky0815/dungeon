package de.dungeon.game.character;

import de.dungeon.game.character.property.Dodge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {

    private Character character;

    @BeforeEach
    void setUp() {
        final Dodge dodge = new Dodge(5, 0);
        character = new Character("Ruhindil", 40, 2, dodge);
    }

    @Test
    void getterShouldReturnValue() {
        assertEquals("Ruhindil", character.getName());
        assertEquals(40, character.getMaxHealth());
        assertEquals(40, character.getHealth());
        assertEquals(5, character.getDodge().getValue());
        assertEquals(2, character.getArmor());
    }

    @Test
    void takeDamageShouldReduceTheHealthWithTheDamageValue() {
        character.takeDamage(20);
        assertEquals(20, character.getHealth());
    }
}
