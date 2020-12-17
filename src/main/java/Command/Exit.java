package Command;

public class Exit extends Command {

    public Exit(String text, String doText) {
        super(text, doText);
    }

    @Override
    protected boolean doing() {
        System.exit(0);
        return true;
    }
}
