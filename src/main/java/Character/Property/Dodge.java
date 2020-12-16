package Character.Property;

public class Dodge extends Property {

    public Dodge(final int value) {
        this(value, 0);
    }

    public Dodge(final int value, final int modifier) {
        super(value, modifier);
    }
}
