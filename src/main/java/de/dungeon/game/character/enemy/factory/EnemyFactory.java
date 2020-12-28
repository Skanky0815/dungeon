package de.dungeon.game.character.enemy.factory;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.character.enemy.Enemy;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.factory.BehaviorFactory;
import de.dungeon.game.character.enemy.behavior.factory.BehaviorMapper;
import de.dungeon.game.character.enemy.behavior.factory.UnknownBehaviorTypeException;
import de.dungeon.game.character.property.Dodge;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.dungeon.game.rule.Dice;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class EnemyFactory {

    private final ObjectMapper mapper;
    private final Dice dice;
    private final BehaviorFactory behaviorFactory;

    @Inject
    public EnemyFactory(
            @NotNull final ObjectMapper mapper,
            @NotNull final Dice dice,
            @NotNull final BehaviorFactory behaviorFactory
    ) {
        this.mapper = mapper;
        this.dice = dice;
        this.behaviorFactory = behaviorFactory;
    }

    public Enemy create(@NotNull final String name) throws Exception {
        final var mapper = loadData(name);

        return new Enemy(
                mapper.getName(),
                mapper.getHealth(),
                mapper.getArmor(),
                (Dodge) new Dodge(dice).init(mapper.getDodge()),
                mapper.getActions(),
                createBehaviorList(mapper.getBehaviors())
        );
    }

    private List<Behavior> createBehaviorList(
            @NotNull List<BehaviorMapper> behaviorListData
    ) throws UnknownBehaviorTypeException {
        final var behaviorList = new ArrayList<Behavior>();
        for (var behaviorData : behaviorListData) {
            behaviorList.add(behaviorFactory.create(behaviorData));
        }
        return behaviorList;
    }

    private EnemyMapper loadData(@NotNull final String name) throws Exception {
        final var file = new File(getClass().getResource(String.format("/npcs/%s.json", name)).getFile());
        return mapper.readValue(file, EnemyMapper.class);
    }
}
