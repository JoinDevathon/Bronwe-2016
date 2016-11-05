package org.devathon.contest2016.tiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class WorldTile implements Tile {

    List<Side> sides;

    private Manager manager;

    public WorldTile(Manager manager, int x, int y) {
        this.manager = manager;
        this.sides = Arrays.asList(
                getTile(manager, x, y + 1, 2),
                getTile(manager, x + 1, y, 3),
                getTile(manager, x, y - 1, 0),
                getTile(manager, x - 1, y, 1));
    }

    private Side getTile(Manager manager, int x, int y, int i) {
        Optional<Tile> tile = manager.get(x, y);
        if (tile.isPresent()) {
            List<Side> sides = tile.get().getSides();
            return sides.get(i);
        } else
            return null;
    }

    @Override
    public List<Side> getSides() {
        return null;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
