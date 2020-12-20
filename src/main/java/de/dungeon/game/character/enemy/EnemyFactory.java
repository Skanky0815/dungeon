package de.dungeon.game.character.enemy;

import com.google.inject.Singleton;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.rule.Damage;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class EnemyFactory {

    private final ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings (value="unchecked")
    public Enemy create(final String name) throws Exception {
        final var result = loadData(name);

        return new Enemy(
                (String) result.get("name"),
                (int) result.get("health"),
                (int) result.get("armor"),
                new Dodge((int) result.get("dodge")),
                (int) result.get("actions"),
                createBehaviorList((List<Map>) result.get("behaviors"))
        );
    }

    private List<Behavior> createBehaviorList(List<Map> behaviorListData) {
        final var behaviorList = new ArrayList<Behavior>();
        for (var behaviorData : behaviorListData) {
           behaviorList.add(createBehavior(behaviorData));
        }
        return behaviorList;
    }

    private Behavior createBehavior(final Map behaviorData) {
        if (behaviorData.get("damage") != null) {
           return new Behavior(
                    (String) behaviorData.get("text"),
                    (int) behaviorData.get("min"),
                    (int) behaviorData.get("max"),
                    createDamage((Map) behaviorData.get("damage"))
            );
        }

        return new Behavior(
                (String) behaviorData.get("text"),
                (int) behaviorData.get("min"),
                (int) behaviorData.get("max")
        );
    }

    private Damage createDamage(final Map damageData) {
        return new Damage(
                (int) damageData.get("diceCount"),
                (int) damageData.get("diceType"),
                (int) damageData.get("modifier")
        );
    }

    private Map loadData(final String name) throws Exception {
        final var file = new File(getClass().getResource(String.format("/npcs/%s.json", name)).getFile());
        return mapper.readValue(file, HashMap.class);
    }
}
