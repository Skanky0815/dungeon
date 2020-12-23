package de.dungeon.game.command;

import de.dungeon.game.character.property.Property;

public class AttributeTestCommand extends Command {

    private int modifier;
    private Property property;

    public AttributeTestCommand init(
            final String text,
            final String doText,
            final int modifier,
            final Property property
    ) {
        super.init(text, doText);
        this.modifier = modifier;
        this.property = property;
        return this;
    }

    @Override
    protected boolean doing() {
        return property.test(modifier).isSuccess();
    }
}
