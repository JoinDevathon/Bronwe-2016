package org.devathon.contest2016.tiles;

import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;
import org.devathon.contest2016.general.Type;

/**
 * Created by Voronwe on 11/5/2016. :)
 */
public class WorldSide implements Side {

    private final Tile parent;
    private final Type type;
    private Side otherSide; // HELLO FROM THE OTHER SIDE!

    public WorldSide(Tile parent, Type type) {
        this.parent = parent;
        this.type = type;
    }

    public WorldSide(Tile parent, Side other) {
        this(parent, other.getType());
        this.otherSide = other;
        otherSide.setOther(this);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public Side getOther() {
        return otherSide;
    }

    @Override
    public Side setOther(Side other) {
        this.otherSide = other;
        return otherSide;
    }

    @Override
    public Tile getTile() {
        return parent;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", getType(),
                otherSide == null ? "null" : otherSide.getType());
    }
}
