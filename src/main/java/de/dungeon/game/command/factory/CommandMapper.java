package de.dungeon.game.command.factory;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.dungeon.game.scenery.factory.SceneryLinkMapper;

public class CommandMapper {

    private String key;
    private String text;
    @JsonProperty("do_text") private String doText;
    private String command;
    private String attribute;
    private int modifier;
    private SceneryLinkMapper failure;
    private SceneryLinkMapper success;

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

    public String getDoText() {
        return doText;
    }

    public void setDoText(String doText) {
        this.doText = doText;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public SceneryLinkMapper getFailure() {
        return failure;
    }

    public void setFailure(SceneryLinkMapper failure) {
        this.failure = failure;
    }

    public SceneryLinkMapper getSuccess() {
        return success;
    }

    public void setSuccess(SceneryLinkMapper success) {
        this.success = success;
    }
}
