package org.devathon.contest2016.tiles;

import org.devathon.contest2016.tiles.interfaces.Compound;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class TileCompound implements Compound {

    private Set<Tile> tiles;
    private Set<Side> sides;

    public TileCompound(Tile parent) {
        tiles = new HashSet<>();
        sides = new HashSet<>();
        tiles.add(parent);
    }

    @Override
    public void merge(Compound otherCompound) {
        otherCompound.getTiles().addAll(tiles);
        tiles = otherCompound.getTiles();
        otherCompound.getMembers().addAll(sides);
        sides = otherCompound.getMembers();
    }

    @Override
    public Set<Side> getMembers() {
        return sides;
    }

    @Override
    public Set<Tile> getTiles() {
        return tiles;
    }

    @Override
    public void addSide(Side side) {
        sides.add(side);
    }
}
