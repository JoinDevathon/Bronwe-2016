package org.devathon.contest2016.tiles;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Manager {
    Tile get(int x, int y);

    Tile create(int x, int y);
}
