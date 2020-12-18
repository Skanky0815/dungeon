package de.dungeon.game.rule;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestResultTest {

    @Test
    void isCriticalSuccessShouldReturnTrueIfDiceValueIs1() {
        final TestResult testResult = new TestResult(1, 10);
        assertTrue(testResult.isCriticalSuccess());
    }

    @Test
    void isCriticalSuccessShouldReturnFalseIfDiceValueIs2() {
        final TestResult testResult = new TestResult(2, 10);
        assertFalse(testResult.isCriticalSuccess());
    }

    @Test
    void isCriticalFailureShouldReturnTrueIfDiceValueIs20() {
        final TestResult testResult = new TestResult(20, 10);
        assertTrue(testResult.isFailureSuccess());
    }

    @Test
    void isCriticalFailureShouldReturnFalseIfDiceValueIs19() {
        final TestResult testResult = new TestResult(19, 10);
        assertFalse(testResult.isFailureSuccess());
    }

    @Test
    void isSuccessShouldReturnTrueIfDiceValueIs10() {
        final TestResult testResult = new TestResult(10, 10);
        assertTrue(testResult.isSuccess());
    }

    @Test
    void isSuccessShouldReturnFalseIfDiceValueIs19() {
        final TestResult testResult = new TestResult(11, 10);
        assertFalse(testResult.isSuccess());
    }
}
