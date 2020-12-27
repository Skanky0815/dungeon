package de.dungeon.game.character.enemy;

import de.dungeon.game.character.Character;
import de.dungeon.game.character.property.Dodge;

import java.util.List;

public class Enemy extends Character  {

    private final List<Behavior> behaviorList;
    private final int actions;

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
        behaviorList.forEach(behavior -> behavior.setEnemy(this));
        this.behaviorList = behaviorList;
    }

    public int getActions() {
        return actions;
    }

    public List<Behavior> getBehaviorList() {
        return behaviorList;
    }

    public Behavior getBehavior(final int value) {
        return (Behavior) behaviorList.stream().filter(behavior -> behavior.isInRange(value)).toArray()[0];
    }
}
