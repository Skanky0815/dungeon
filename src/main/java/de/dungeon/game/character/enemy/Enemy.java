package de.dungeon.game.character.enemy;

import de.dungeon.game.character.Character;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.property.Dodge;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Enemy extends Character  {

    private final List<Behavior> behaviorList;
    private final int actions;

    public Enemy(
            @NotNull final EnemyProperty property,
            @NotNull final Dodge dodge,
            @NotNull final List<Behavior> behaviorList
    ) {
        super(property.getName(), property.getHealth(), property.getArmor(), dodge);
        this.actions = property.getActions();
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

    public void die() {
        // TODO enemy must die!
    }
}
