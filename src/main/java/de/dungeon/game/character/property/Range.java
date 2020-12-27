package de.dungeon.game.character.property;

import de.dungeon.game.rule.Dice;
import org.jetbrains.annotations.NotNull;

public class Range extends Property {
    public Range(@NotNull final Dice dice) {
        super(dice);
    }
}
