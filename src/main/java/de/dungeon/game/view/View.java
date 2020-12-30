package de.dungeon.game.view;

import com.google.inject.Inject;
import de.dungeon.game.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class View {

    protected String message;
    protected final Text text;

    @Inject
    public View(@NotNull final Text text) {
        this.text = text;
    }

    public void render(@NotNull final String key, @Nullable final Object ...params) {
        this.message = text.get(key).formatted(params);
        print();
    }

    public void render(@NotNull final String message) {
        this.message = message;
        print();
    }

    public void render() {
        print();
    }

    private void print() {
        System.out.print(message);
    }
}
