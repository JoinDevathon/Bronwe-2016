package org.devathon.contest2016.tiles.general;

import org.devathon.contest2016.tiles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.devathon.contest2016.tiles.Rotation.opposite;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class WorldTile implements Tile {

    List<Side> sides;

    private Manager manager;

    public WorldTile(Manager manager, Coordinate coord) {
        sides = new ArrayList<>(4);
        this.manager = manager;
        List<Coordinate> neighbors = coord.getNeighbors();
        int size = neighbors.size();

        AtomicInteger i = new AtomicInteger(-1);
        while (i.incrementAndGet() < size) {
            AtomicBoolean found = new AtomicBoolean();
            Optional<Tile> tile = manager.get(neighbors.get(i.get()));

            tile.ifPresent(neighborTile -> {
                Side other = neighborTile.getSides().get(opposite(i.get()));
                sides.add(new WorldSide(this, other));
                found.set(true);
            });

            if (!found.get()) {
                sides.add(new WorldSide(this, (Type) null));
            }
        }
    }

    private static String firstLetter(Side o) {
        return o.toString().substring(0, 1);
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
