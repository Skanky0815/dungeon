package de.dungeon.game.character.enemy.behavior.factory;

import org.jetbrains.annotations.NotNull;

public class UnknownBehaviorTypeException extends Exception{
    public UnknownBehaviorTypeException(@NotNull final String type) {
        super("Behavior type %s not implemented!".formatted(type));
    }
}
