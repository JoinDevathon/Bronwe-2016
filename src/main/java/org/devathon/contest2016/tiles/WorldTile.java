package org.devathon.contest2016.tiles;

import java.util.List;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class WorldTile implements Tile {

    private Manager manager;

    public WorldTile(Manager manager) {
        this.manager = manager;
    }

    @Override
    public List<Side> getSides() {
        return null;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
