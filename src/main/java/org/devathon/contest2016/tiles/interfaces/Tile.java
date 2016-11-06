package org.devathon.contest2016.tiles.interfaces;

import org.devathon.contest2016.general.Type;

import java.util.List;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Tile {

    List<Side> getSides();

    Manager getManager();

    Type getCenter();

}
