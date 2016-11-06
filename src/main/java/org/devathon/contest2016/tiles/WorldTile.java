package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.tiles.interfaces.Manager;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.devathon.contest2016.general.Rotation.opposite;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class WorldTile implements Tile {

    List<Side> sides;

    private Manager manager;

    public WorldTile(Manager manager, Coordinate coord, TileType type, Rotation rotation) {
        sides = new ArrayList<>(4);
        this.manager = manager;

        sides = type.asSidesList(this, rotation);
        List<Coordinate> neighbors = coord.getNeighbors();
        int size = sides.size();


        AtomicInteger i = new AtomicInteger(-1);
        while (i.incrementAndGet() < size) {
            int index = i.get();
            Optional<Tile> tile = manager.get(neighbors.get(index));
            tile.ifPresent(neighborTile -> linkSides(index, neighborTile));
        }
    }

    private static String firstLetter(Side o) {
        return o.toString().substring(0, 1);
    }

    private void linkSides(int index, Tile neighborTile) {
        Side other = neighborTile.getSides().get(opposite(index));
        Side own = sides.get(index);
        if (!own.getType().equals(other.getType()))
            throw new IllegalArgumentException("Invalidly positioned element");
        sides.set(index, new WorldSide(this, other));
    }

    @Override
    public List<Side> getSides() {
        return sides;
    }

    @Override
    public Manager getManager() {
        return manager;
    }

    @Override
    public String toString() {
        return String.format("" +
                        "# %s #%n" +
                        "%s   %s%n" +
                        "# %s #",
                firstLetter(sides.get(0)),
                firstLetter(sides.get(3)),
                firstLetter(sides.get(1)),
                firstLetter(sides.get(2))
        );
    }
}
