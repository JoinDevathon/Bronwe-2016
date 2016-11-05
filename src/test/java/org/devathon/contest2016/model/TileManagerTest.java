package org.devathon.contest2016.model;

import org.devathon.contest2016.tiles.RootTile;
import org.devathon.contest2016.tiles.TileManager;
import org.devathon.contest2016.tiles.WorldTile;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class TileManagerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testUnconnectedTileThrowsException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile does not exist.");
        new TileManager(new RootTile()).get(2, 2);
    }

    @Test
    public void testCreateUnconnectedTileThrowsException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile would not be connected.");
        new TileManager(new RootTile()).create(2, 2);
    }

    @Test
    public void testCreateConnectedTile() throws Exception {
        assertThat(new TileManager(new RootTile()).create(1, 0), is(instanceOf(WorldTile.class)));
    }

}