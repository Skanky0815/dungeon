package de.dungeon.game.command;

import de.dungeon.game.character.property.Property;

public class AttributeTestCommand extends Command {

    private final int modifier;
    private final Property property;

    public AttributeTestCommand(final String text, final String doText, final int modifier, final Property property) {
        super(text, doText);
        this.modifier = modifier;
        this.property = property;
    }

    @Override
    protected boolean doing() {
        return property.test(modifier).isSuccess();
    }
}
