package de.dungeon.game.character.enemy;

public class UnknownBehaviorTypeException extends Exception{
    public UnknownBehaviorTypeException(final String type) {
        super("Behavior type %s not implemented!".formatted(type));
    }
}
