package de.dungeon.game.character.property;

import de.dungeon.game.rule.Dice;
import org.jetbrains.annotations.NotNull;

public class Dodge extends Property {
    public Dodge(@NotNull final Dice dice) {
        super(dice);
    }
}
