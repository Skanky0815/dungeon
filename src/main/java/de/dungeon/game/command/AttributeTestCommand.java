package de.dungeon.game.command;

import de.dungeon.game.character.property.Property;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;

public class AttributeTestCommand extends Command {

    private int modifier;
    private Property property;

    public AttributeTestCommand(@NotNull final View view) {
        super(view);
    }

    public AttributeTestCommand init(
            @NotNull final String text,
            @NotNull final String doText,
            final int modifier,
            @NotNull final Property property
    ) {
        super.init(text, doText);
        this.modifier = modifier;
        this.property = property;
        return this;
    }

    @Override
    protected boolean doing() {
        return property.isTestSuccessfully(modifier);
    }
}
