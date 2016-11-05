package org.devathon.contest2016.tiles;

import org.devathon.contest2016.Pair;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class TileManager implements Manager {

    private Map<Pair<Integer, Integer>, Tile> tiles;

    public TileManager(Tile root) {
        tiles = new HashMap<>();
        tiles.put(new Pair<>(0, 0), root);

    }

    @Override
    public Tile get(int x, int y) {
        if (!exists(x, y)) {
            throw new IllegalArgumentException("This tile does not exist.");
        }

        return tiles.get(new Pair<>(x, y));
    }

    private boolean exists(int x, int y) {
        return tiles.containsKey(new Pair<>(x, y));
    }

    @Override
    public Tile create(int x, int y) {

        if (exists(x - 1, y) || exists(x + 1, y) || exists(x, y - 1) || exists(x, y + 1)) {
            return new WorldTile();
        }


        throw new IllegalArgumentException("This tile would not be connected.");
    }
}
