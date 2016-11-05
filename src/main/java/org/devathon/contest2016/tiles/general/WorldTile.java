package org.devathon.contest2016.tiles.general;

import org.devathon.contest2016.tiles.Manager;
import org.devathon.contest2016.tiles.Side;
import org.devathon.contest2016.tiles.Tile;

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
                createSide(manager, x, y + 1, 2),
                createSide(manager, x + 1, y, 3),
                createSide(manager, x, y - 1, 0),
                createSide(manager, x - 1, y, 1)
        );
    }

    private Side createSide(Manager manager, int x, int y, int i) {
        Optional<Tile> tile = manager.get(x, y);
        if (tile.isPresent()) {
            List<Side> sides = tile.get().getSides();
            return new WorldSide(sides.get(i));
        } else
            return new WorldSide(this, null);
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
                sides.get(0).toString().substring(0, 1),
                sides.get(3).toString().substring(0, 1),
                sides.get(1).toString().substring(0, 1),
                sides.get(2).toString().substring(0, 1)
        );
    }
}
