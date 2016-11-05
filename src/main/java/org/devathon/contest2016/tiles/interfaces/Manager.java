package org.devathon.contest2016.tiles.interfaces;

import org.devathon.contest2016.general.Coordinate;

import java.util.Optional;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Manager {
    Optional<Tile> get(Coordinate coord);

    Tile create(Coordinate coord);

    int getTileCount();
}
