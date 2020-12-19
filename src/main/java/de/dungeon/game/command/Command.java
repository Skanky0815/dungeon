package de.dungeon.game.command;

import de.dungeon.game.scenery.Scenery;

public abstract class Command {
    private final String text;
    private final String doText;
    private String successText;
    private String failureText;
    private Scenery successAction;
    private Scenery failureAction;

    public Command(final String text, final String doText) {
        this.text = text;
        this.doText = doText;
    }

    public Command(final String text) {
        this(text, "");
    }

    public void setSuccessAction(final Scenery action, final String text) {
        this.successAction = action;
        this.successText = text;
    }

    public void setFailureAction(final Scenery action, final String text) {
        this.failureAction = action;
        this.failureText = text;
    }

    public String getText() {
        return text;
    }

    public boolean doAction() {
        System.out.println(doText);
        if (doing()) {
            return handleAction(successText, successAction);
        }

        return handleAction(failureText, failureAction);
    }

    protected abstract boolean doing();

    private boolean handleAction(final String text, final Scenery action) {
        System.out.println(text);
        if (null != action) {
            action.run();
        }
        return true;
    }
}
