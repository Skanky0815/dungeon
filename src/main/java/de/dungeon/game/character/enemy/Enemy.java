package de.dungeon.game.character.enemy;

import de.dungeon.game.character.Character;
import de.dungeon.game.character.property.Dodge;

import java.util.List;

final public class Enemy extends Character  {

    final private List<Behavior> behaviorList;
    final private int actions;

    public Enemy(
            final String name,
            final int health,
            final int armor,
            final Dodge dodge,
            final int actions,
            final List<Behavior> behaviorList
    ) {
        super(name, health, armor, dodge);
        this.actions = actions;
        this.behaviorList = behaviorList;
    }

    public int getActions() {
        return actions;
    }

    public Behavior getBehavior(final int value) {
        return (Behavior) behaviorList.stream().filter(behavior -> behavior.isInRange(value)).toArray()[0];
    }
}