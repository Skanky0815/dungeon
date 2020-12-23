package de.dungeon.game.rule;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class DamageTest {

    @Test
    void calculateDamageShouldReturnADamageBetween1And6() throws UnknownDiceException {
        final var damage = (new Damage(1, 6, 0)).calculateDamage();
        assertThat(damage, greaterThanOrEqualTo(1));
        assertThat(damage, lessThanOrEqualTo(6));
    }

    @Test
    void calculateDamageShouldReturnADamageBetween1And20() throws UnknownDiceException {
        final var damage = (new Damage(1, 20, 0)).calculateDamage();
        assertThat(damage, greaterThanOrEqualTo(1));
        assertThat(damage, lessThanOrEqualTo(20));
    }

    @Test
    void calculateDamageShouldReturnADamageBetween4And10BecauseOfModifier4() throws UnknownDiceException {
        final var damage = (new Damage(1, 6, 4)).calculateDamage();
        assertThat(damage, greaterThanOrEqualTo(4));
        assertThat(damage, lessThanOrEqualTo(10));
    }

    @Test
    void calculateDamageShouldReturnADamageBetween2And12BecauseOfTwoD6() throws UnknownDiceException {
        final var damage = (new Damage(2, 6, 0)).calculateDamage();
        assertThat(damage, greaterThanOrEqualTo(2));
        assertThat(damage, lessThanOrEqualTo(12));
    }

    @Test
    void calculateDamageShouldThrownAUnknownDiceException() {
        final var exception = assertThrows(UnknownDiceException.class, () -> {
            (new Damage(1, 3, 0)).calculateDamage();
        });

        assertEquals("Dice 3 does not exists!", exception.getMessage());
    }
}