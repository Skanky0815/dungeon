package Command;

abstract class Command {
    final private String text;
    final private String doText;
    private Action successAction;
    private Action failureAction;

    public Command(final String text, final String doText) {
        this.text = text;
        this.doText = doText;
    }

    public void setSuccessAction(final Action successAction) {
        this.successAction = successAction;
    }

    public void setFailureAction(final Action failureAction) {
        this.failureAction = failureAction;
    }

    public boolean doAction() {
        if (doing()) {
            if (null == successAction) {
                return true;
            }
            return successAction.run();
        }

        if (null == failureAction) {
            return false;
        }
        return failureAction.run();
    }

    abstract protected boolean doing();
}
