package org.devathon.contest2016.tiles;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class RootTileTest {

    private Tile root;

    @Before
    public void setUp() throws Exception {
        root = new RootTile();
    }

    @Test
    public void testInitialSides() throws Exception {
        List<Side> sides = root.getSides();
        assertThat(sides.get(0).getType(), is(Type.CITY));
        assertThat(sides.get(1).getType(), is(Type.ROAD));
        assertThat(sides.get(2).getType(), is(Type.LAND));
        assertThat(sides.get(3).getType(), is(Type.ROAD));
        assertThat(sides.size(), is(4));
    }

    @Test
    public void testRootTileSidesKnowRootTile() throws Exception {
        assertThat(root.getSides().get(0).getTile(), is(root));
    }

    @Test
    public void testTileManager() throws Exception {
        assertThat(root.getManager().get(0, 0).get(), is(root));
    }
    @Test
    public void testConsistentTileManager() throws Exception {
        assertThat(root.getManager(), is(root.getManager()));
    }



}