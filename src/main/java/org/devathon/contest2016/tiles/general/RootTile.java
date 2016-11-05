package org.devathon.contest2016.tiles.general;

import org.devathon.contest2016.tiles.Manager;
import org.devathon.contest2016.tiles.Side;
import org.devathon.contest2016.tiles.Tile;
import org.devathon.contest2016.tiles.Type;

import java.util.Arrays;
import java.util.List;

import static org.devathon.contest2016.tiles.Type.*;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class RootTile implements Tile {

    private final List<Side> sides = Arrays.asList(side(CITY), side(ROAD), side(LAND), side(ROAD));
    private final TileManager tileManager = new TileManager(this);

    private WorldSide side(Type city) {
        return new WorldSide(this, city);
    }

    @Override
    public List<Side> getSides() {
        return sides;
    }

    @Override
    public Manager getManager() {
        return tileManager;
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
