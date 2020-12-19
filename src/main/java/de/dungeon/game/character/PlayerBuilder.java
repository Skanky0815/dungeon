package de.dungeon.game.character;

import de.dungeon.game.character.property.Dodge;
import de.dungeon.game.character.property.Magic;
import de.dungeon.game.character.property.Melee;
import de.dungeon.game.character.property.Range;

public class PlayerBuilder {

    private String name;
    private Melee melee;
    private Range range;
    private Magic magic;
    private Dodge dodge;
    private int armor;

    public PlayerBuilder build(final String name, final int melee, final int range, final int magic, final int dodge) {
        this.name = name;
        this.melee = new Melee(melee, 0);
        this.range = new Range(range, 0);
        this.magic = new Magic(magic, 0);
        this.dodge = new Dodge(dodge, 0);
        this.armor = 0;
        return this;
    }

    public Player get() {
        return new Player(name, melee, range, magic, dodge, armor);
    }
}
