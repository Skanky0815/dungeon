package Character.Enemy;

import Character.Property.Dodge;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnemyFactory {

    public Enemy create(final String name) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();

        Map<String,Object> result = mapper.readValue(new File(this.getClass().getResource("/npcs/Eber.json").toURI()), HashMap.class);
        System.out.print(result);

        return new Enemy(
                (String) result.get("name"),
                (int) result.get("health"),
                (int) result.get("armor"),
                new Dodge((int) result.get("dodge")),
                (int) result.get("actions"),
                new ArrayList<Behavior>()
        );
    }
}
