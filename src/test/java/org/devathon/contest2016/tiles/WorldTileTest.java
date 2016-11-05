package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.tiles.interfaces.Manager;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;
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