package de.dungeon.game.command;

import de.dungeon.game.FrontController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrontControllerTest {

    private FrontController controller;

    @BeforeEach
    void setUp() {
        controller = new FrontController(new ExitCommand());
    }

    @Test
    void Should() {
        assertTrue(true);
    }
}