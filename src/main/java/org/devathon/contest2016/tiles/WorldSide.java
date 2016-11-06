package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Type;
import org.devathon.contest2016.tiles.interfaces.Compound;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

/**
 * Created by Voronwe on 11/5/2016. :)
 */
public class WorldSide implements Side {

    private final Tile parent;
    private final Type type;
    private Side otherSide; // HELLO FROM THE OTHER SIDE!
    private Compound compound;

    public WorldSide(Tile parent, Type type, Compound compound) {
        this.parent = parent;
        this.type = type;
        this.compound = compound;
        compound.addSide(this);
    }

    public WorldSide(Tile parent, Side other, Compound compound) {
        this(parent, other.getType(), compound);
        this.otherSide = other;
        otherSide.setOther(this);
        compound.addSide(this);
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
        this.getCompound().merge(other.getCompound());
        return otherSide;
    }

    @Override
    public Tile getTile() {
        return parent;
    }

    @Override
    public Compound getCompound() {
        return compound;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", getType(),
                otherSide == null ? "null" : otherSide.getType());
    }
}
