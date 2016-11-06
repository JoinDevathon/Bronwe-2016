package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.general.Type;
import org.devathon.contest2016.tiles.interfaces.Manager;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.List;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class RootTile implements Tile {

    private final List<Side> sides = TileType.DEFAULT.asSidesList(this, Rotation.NORMAL);
    private final TileManager tileManager = new TileManager(this);

    @Override
    public List<Side> getSides() {
        return sides;
    }

    @Override
    public Manager getManager() {
        return tileManager;
    }

    @Override
    public Type getCenter() {
        return Type.ROAD;
    }

    @Override
    public String toString() {
        return String.format("" +
                "# C #%n" +
                "R # R%n" +
                "# L #"
        );
    }
}
