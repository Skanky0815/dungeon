package de.dungeon.game.command;

import de.dungeon.game.scenery.Scenery;
import de.dungeon.game.view.View;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Command {
    private String text;
    private String doText;
    private String successText;
    private String failureText;
    private Scenery successAction;
    private Scenery failureAction;
    protected final View view;

    public Command(@NotNull final View view) {
        this.view = view;
    }

    public Command init(
            @NotNull final String text,
            @Nullable final String doText
    ) {
        this.text = text;
        this.doText = doText;
        return this;
    }

    public void setSuccessAction(@NotNull final Scenery action, @NotNull final String text) {
        this.successAction = action;
        this.successText = text;
    }

    public void setFailureAction(@NotNull final Scenery action, @NotNull final String text) {
        this.failureAction = action;
        this.failureText = text;
    }

    public String getText() {
        return text;
    }

    public boolean doAction() throws Exception {
        if (null != doText) {
            view.render(doText);
        }

        if (doing()) {
            return handleAction(successText, successAction, true);
        }

        return handleAction(failureText, failureAction, false);
    }

    protected abstract boolean doing();

    private boolean handleAction(
            @Nullable final String text,
            @Nullable final Scenery action,
            final boolean isSuccessful
    ) throws Exception {
        if (null != action) {
            view.render(text);
            action.run();
        }
        return isSuccessful;
    }
}
