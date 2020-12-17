package Command;

import com.ginsberg.junit.exit.ExpectSystemExit;
import org.junit.jupiter.api.Test;

public class ExitTest {

    @Test
    @ExpectSystemExit
    void doActionShouldExitTheGame() {
        final var exist = new Exit("Bye Bye", "und weg");
        exist.doAction();
    }
}
