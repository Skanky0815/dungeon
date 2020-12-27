package de.dungeon.game.character.property;

import de.dungeon.game.rule.Dice;
import org.jetbrains.annotations.NotNull;

public class Melee extends Property {
    public Melee(@NotNull final Dice dice) {
        super(dice);
    }
}
