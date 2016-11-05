package org.devathon.contest2016.tiles;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Side {

    Type getType();

    Side getOther();

    Tile getTile();
}
