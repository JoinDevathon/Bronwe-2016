package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.tiles.interfaces.Manager;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class TileManager implements Manager {

    private Map<Coordinate, Tile> tiles;

    public TileManager(Tile root) {
        tiles = new HashMap<>();
        put(new Coordinate(0, 0), root);

    }

    @Override
    public Optional<Tile> get(Coordinate coord) {
        return Optional.ofNullable(tiles.getOrDefault(coord, null));
    }

    private boolean exists(Coordinate coord) {
        return tiles.containsKey(coord);
    }

    @Override
    public Tile create(Coordinate coord) {
        if (exists(coord))
            throw new IllegalArgumentException("This tile already exists.");

        if (!hasExistingNeighbor(coord))
            throw new IllegalArgumentException("This tile would not be connected.");

        put(coord, new WorldTile(this, coord));
        return get(coord).get();
    }

    @Override
    public int getTileCount() {
        return tiles.size();
    }

    private void put(Coordinate coord, Tile tile) {
        tiles.put(coord, tile);
    }

    private boolean hasExistingNeighbor(Coordinate coord) {
        return coord.getNeighbors().stream().anyMatch(this::exists);
    }
}
