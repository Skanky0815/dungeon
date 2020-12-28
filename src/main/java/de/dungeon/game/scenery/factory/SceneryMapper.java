package de.dungeon.game.scenery.factory;

import de.dungeon.game.command.factory.CommandMapper;

import java.util.List;

public class SceneryMapper {

    private String key;
    private String text;
    private String enemy;
    private List<CommandMapper> commands;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }

    public boolean hasEnemy() {
        return null != enemy;
    }

    public List<CommandMapper> getCommands() {
        return commands;
    }

    public void setCommands(List<CommandMapper> commands) {
        this.commands = commands;
    }
}
