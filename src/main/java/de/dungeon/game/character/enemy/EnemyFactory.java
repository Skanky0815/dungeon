package de.dungeon.game.character.enemy;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import de.dungeon.game.character.enemy.behavior.Behavior;
import de.dungeon.game.character.enemy.behavior.factory.Factory;
import de.dungeon.game.character.property.Dodge;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.dungeon.game.rule.Dice;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class EnemyFactory {

    private final ObjectMapper mapper;
    private final Dice dice;
    private final Factory behaviorFactory;

    @Inject
    public EnemyFactory(final ObjectMapper mapper, final Dice dice, final Factory behaviorFactory) {
        this.mapper = mapper;
        this.dice = dice;
        this.behaviorFactory = behaviorFactory;
    }

    @SuppressWarnings (value="unchecked")
    public Enemy create(final String name) throws Exception {
        final var result = loadData(name);

        return new Enemy(
                (String) result.get("name"),
                (int) result.get("health"),
                (int) result.get("armor"),
                (Dodge) new Dodge(dice).init((int) result.get("dodge")),
                (int) result.get("actions"),
                createBehaviorList((List<Map>) result.get("behaviors"))
        );
    }

    private List<Behavior> createBehaviorList(List<Map> behaviorListData) throws UnknownBehaviorTypeException {
        final var behaviorList = new ArrayList<Behavior>();
        for (var behaviorData : behaviorListData) {
           behaviorList.add(behaviorFactory.create(behaviorData));
        }
        return behaviorList;
    }

    private Map loadData(final String name) throws Exception {
        final var file = new File(getClass().getResource(String.format("/npcs/%s.json", name)).getFile());
        return mapper.readValue(file, HashMap.class);
    }
}
