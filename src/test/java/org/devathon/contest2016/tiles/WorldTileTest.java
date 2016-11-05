package org.devathon.contest2016.tiles;

import org.devathon.contest2016.tiles.general.RootTile;
import org.devathon.contest2016.tiles.general.TileManager;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class WorldTileTest {

    @Test
    public void testCalculateSides() throws Exception {
        Tile root = new RootTile();
        Manager manager = new TileManager(root);
        Tile neighbor = manager.create(new Coordinate(1, 0));
        assertThat(neighbor.getSides().get(3).getOther(), is(root.getSides().get(1)));
    }

    @Test
    public void testCalculateSidesLinks() throws Exception {
        Tile root = new RootTile();
        Manager manager = new TileManager(root);
        Tile neighbor = manager.create(new Coordinate(1, 0));
        Side side = neighbor.getSides().get(3);
        assertThat(side.getOther().getOther(), is(side));
    }
}