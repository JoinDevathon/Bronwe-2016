package org.devathon.contest2016.tiles.interfaces;

import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.tiles.TileType;

import java.util.Optional;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Manager {
    Optional<Tile> get(Coordinate coord);

    Tile create(Coordinate coord, TileType type, Rotation rotation);

    int getTileCount();
}
