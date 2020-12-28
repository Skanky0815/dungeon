package de.dungeon.game.scenery.factory;

public class NoSceneriesException extends Exception {
    public NoSceneriesException() {
        super("No Sceneries found in scenery dir!");
    }
}
