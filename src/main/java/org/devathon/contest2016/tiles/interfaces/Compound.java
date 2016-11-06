package org.devathon.contest2016.tiles.interfaces;

import java.util.Set;

/**
 * Created by Voronwe on 11/5/2016.
 */
public interface Compound {

    void merge(Compound otherCompound);

    Set<Side> getMembers();

    Set<Tile> getTiles();

    void addSide(Side side);

}
