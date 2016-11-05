package org.devathon.contest2016.tiles;

import java.util.Optional;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Manager {
    Optional<Tile> get(int x, int y);

    Tile create(int x, int y);

    int getTileCount();
}
