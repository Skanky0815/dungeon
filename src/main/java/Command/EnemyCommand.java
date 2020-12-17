package Command;

import Character.Enemy.Enemy;

abstract public class EnemyCommand extends Command {

    final protected Enemy enemy;

    public EnemyCommand(final String text, final String doText, final Enemy enemy) {
        super(text, doText);
        this.enemy = enemy;
    }
}
