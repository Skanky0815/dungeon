package de.dungeon.game.character.property;

public class Dodge extends Property {

    public Dodge(final int value) {
        this(value, 0);
    }

    public Dodge(final int value, final int modifier) {
        super(value, modifier);
    }
}
