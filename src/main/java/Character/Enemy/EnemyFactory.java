package Character.Enemy;

import Character.Property.Dodge;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnemyFactory {

    private final ObjectMapper mapper = new ObjectMapper();

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

    private List<Behavior> createBehaviorList(List<Map> behaviorListData) throws Exception {
        final var behaviorList = new ArrayList<Behavior>();
        for (Map behaviorData : behaviorListData) {
            behaviorList.add(new Behavior(
                    (String) behaviorData.get("text"),
                    (int) behaviorData.get("min"),
                    (int) behaviorData.get("max"))
            );
        }
        return behaviorList;
    }

    private Map loadData(final String name) throws Exception {
        final var file = new File(getClass().getResource(String.format("/npcs/%s.json", name)).toURI());
        return mapper.readValue(file, HashMap.class);
    }
}
