package de.dungeon.game.character.player;

import de.dungeon.game.character.property.Melee;
import de.dungeon.game.rule.Damage;
import de.dungeon.game.rule.UnknownDiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeaponTest {

    @Test
    void getDamageShouldReturnTheCalculatedDamage() throws UnknownDiceException {
        final var damage = mock(Damage.class);
        when(damage.calculateDamage()).thenReturn(42);

        final var weapon = new Weapon("axe", damage, Melee.class);

        assertEquals(Melee.class, weapon.getTestProperty());
        assertEquals("axe", weapon.getName());
        assertEquals(42, weapon.getDamage());
        verify(damage).calculateDamage();
    }
}