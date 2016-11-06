package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.general.Rotation;
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
        manager.create(new Coordinate(2, 2), TileType.DEFAULT, Rotation.NORMAL);
    }

    @Test
    public void testCreateConnectedTile() throws Exception {
        assertThat(manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.NORMAL), is(instanceOf(WorldTile.class)));
    }

    @Test
    public void testCreateRootThrowsException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile already exists.");
        manager.create(new Coordinate(0, 0), TileType.DEFAULT, Rotation.NORMAL);
    }

    @Test
    public void testExistingTileThrowsException() throws Exception {
        manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.NORMAL);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("This tile already exists.");
        manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.NORMAL);
    }

    @Test
    public void testWronglyPositionedTileThrowsException() throws Exception {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Invalidly positioned element.");
        manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.QUARTER);
    }

    @Test
    public void testGetTotal() throws Exception {
        assertThat(manager.getTileCount(), is(1));
        manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.NORMAL);
        assertThat(manager.getTileCount(), is(2));
    }

    @Test
    public void testGetTotalMultipleTiles() throws Exception {
        assertThat(manager.getTileCount(), is(1));
        manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.NORMAL);
        manager.create(new Coordinate(2, 0), TileType.DEFAULT, Rotation.NORMAL);
        manager.create(new Coordinate(3, 0), TileType.DEFAULT, Rotation.NORMAL);
        manager.create(new Coordinate(4, 0), TileType.DEFAULT, Rotation.NORMAL);
        manager.create(new Coordinate(0, 1), TileType.DEFAULT, Rotation.HALF);
        assertThat(manager.getTileCount(), is(6));
    }

    @Test
    public void testNewTileGetManager() throws Exception {
        assertThat(manager.create(new Coordinate(1, 0), TileType.DEFAULT, Rotation.NORMAL).getManager(), is(manager));
    }

}