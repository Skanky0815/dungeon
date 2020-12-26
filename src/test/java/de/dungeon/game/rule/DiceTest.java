package de.dungeon.game.rule;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DiceTest {

    @Test
    void rollD6ShouldReturnAValueGreaterThen1() {
        assertThat("dice rolled value", dice().rollD6(), greaterThanOrEqualTo(1));
    }

    @Test
    void rollD6ShouldReturnAValueLesserThen6() {
        assertThat("dice rolled value", dice().rollD6(), lessThanOrEqualTo(6));
    }

    @Test
    void rollD20ShouldReturnAValueGreaterThen1() {
        assertThat("dice rolled value", dice().rollD20(), greaterThanOrEqualTo(1));
    }

    @Test
    void rollD20ShouldReturnAValueLesserThen20() {
        assertThat("dice rolled value", dice().rollD20(), lessThanOrEqualTo(20));
    }

    private Dice dice() {
        return new Dice(new Random());
    }
}
