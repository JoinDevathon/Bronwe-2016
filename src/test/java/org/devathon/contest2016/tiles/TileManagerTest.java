package org.devathon.contest2016.tiles;

import org.devathon.contest2016.tiles.general.RootTile;
import org.devathon.contest2016.tiles.general.TileManager;
import org.devathon.contest2016.tiles.general.WorldTile;
import org.junit.Before;
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
    private TileManager manager;

    @Before
    public void setUp() throws Exception {
        manager = new TileManager(new RootTile());
    }

    @Test
    public void testUnconnectedTileThrowsException() throws Exception {
        manager.get(new Coordinate(2, 2));
    }

    @Test
    public void testCreateUnconnectedTileThrowsException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile would not be connected.");
        manager.create(new Coordinate(2, 2));
    }

    @Test
    public void testCreateConnectedTile() throws Exception {
        assertThat(manager.create(new Coordinate(1, 0)), is(instanceOf(WorldTile.class)));
    }

    @Test
    public void testCreateRootThrowsException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile already exists.");
        manager.create(new Coordinate(0, 0));
    }

    @Test
    public void testExistingTileThrowsException() throws Exception {
        manager.create(new Coordinate(1, 0));
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile already exists.");
        manager.create(new Coordinate(1, 0));
    }

    @Test
    public void testGetTotal() throws Exception {
        assertThat(manager.getTileCount(), is(1));
        manager.create(new Coordinate(1, 0));
        assertThat(manager.getTileCount(), is(2));
    }

    @Test
    public void testNewTileGetManager() throws Exception {
        assertThat(manager.create(new Coordinate(1, 0)).getManager(), is(manager));
    }

}