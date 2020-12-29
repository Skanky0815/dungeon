package de.dungeon.game.rule;

import de.dungeon.game.character.enemy.behavior.factory.DamageMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DamageTest {

    @Test
    void calculateDamageShouldReturnADamageBetween1And6() throws UnknownDiceException {
        assertEquals(4, damage(4).init(DamageMapper.build(1, 6, 0)).calculateDamage());
    }

    @Test
    void calculateDamageShouldReturnADamageBetween1And20() throws UnknownDiceException {
        assertEquals(10, damage(10).init(DamageMapper.build(1, 20, 0)).calculateDamage());
    }

    @Test
    void calculateDamageShouldReturnADamageBetween4And10BecauseOfModifier4() throws UnknownDiceException {
        assertEquals(8, damage(4).init(DamageMapper.build(1, 6, 4)).calculateDamage());
    }

    @Test
    void calculateDamageShouldReturnADamageBetween2And12BecauseOfTwoD6() throws UnknownDiceException {
        assertEquals(2, damage(1).init(DamageMapper.build(2, 6, 0)).calculateDamage());
    }

    @Test
    void calculateDamageShouldThrownAUnknownDiceException() {
        final var exception = assertThrows(UnknownDiceException.class, () -> {
            damage(0).init(DamageMapper.build(1, 3, 0)).calculateDamage();
        });

        assertEquals("Dice 3 does not exists!", exception.getMessage());
    }

    private Damage damage(final int diceValue) {
        final var dice = mock(Dice.class);
        when(dice.rollD6()).thenReturn(diceValue);
        when(dice.rollD20()).thenReturn(diceValue);

        return new Damage(dice);
    }
}