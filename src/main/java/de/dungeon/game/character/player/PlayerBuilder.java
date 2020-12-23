package de.dungeon.game.character.player;

import de.dungeon.game.character.Player;
import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;
import de.dungeon.game.rule.Damage;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {

    private final String name;
    private final Melee melee;
    private final Range range;
    private final Magic magic;
    private final Dodge dodge;
    private final int armor;
    private final List<Weapon> weaponList;

    private PlayerBuilder(final String name, final int melee, final int range, final int magic, final int dodge) {
        this.name = name;
        this.melee = new Melee(melee, 0);
        this.range = new Range(range, 0);
        this.magic = new Magic(magic, 0);
        this.dodge = new Dodge(dodge, 0);
        this.armor = 0;
        this.weaponList = new ArrayList<>() {{
            add(new Weapon("Axt", new Damage(1, 6, 0), Melee.class));
        }};
    }

    public static PlayerBuilder build(
            final String name,
            final int melee,
            final int range,
            final int magic,
            final int dodge
    ) {
        return new PlayerBuilder(name, melee, range, magic, dodge);
    }

    public PlayerBuilder withRangeModifier(final int modifier) {
        this.range.setModifier(modifier);
        return this;
    }

    public Player get() {
        return new Player(name, melee, range, magic, dodge, armor, weaponList);
    }
}
