package org.devathon.contest2016.tiles.interfaces;

import org.devathon.contest2016.general.Type;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Side {

    Type getType();

    Side getOther();

    Side setOther(Side other);

    Tile getTile();

    Compound getCompound();
}
