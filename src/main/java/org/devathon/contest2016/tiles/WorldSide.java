package org.devathon.contest2016.tiles;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class WorldSide implements Side {

    private Tile parent;
    private final Type type;
    private Side otherSide; // HELLO FROM THE OTHER SIDE!

    public WorldSide(Tile parent, Type type) {
        this.parent = parent;
        this.type = type;
    }

    private WorldSide(Side other) {
        this(other.getTile(), other.getType());
        this.otherSide = other;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Side getOther() {
        if (otherSide == null)
            otherSide = new WorldSide(this);
        return otherSide;
    }

    @Override
    public Tile getTile() {
        return parent;
    }
}
