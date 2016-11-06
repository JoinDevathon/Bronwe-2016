package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.tiles.interfaces.Manager;
import org.junit.Before;
import org.junit.Test;

import static org.devathon.contest2016.general.Rotation.NORMAL;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class DisplayTest {

    public static final String NEWLINE = String.format("%n");
    public static final Coordinate ABOVE = new Coordinate(0, 1);
    public static final Coordinate BELOW = new Coordinate(0, -1);
    public static final Coordinate RIGHT = new Coordinate(1, 0);
    private Manager manager;

    @Before
    public void setUp() throws Exception {
        manager = new RootTile().getManager();
    }

    @Test
    public void testRoot() throws Exception {
        assertThat(new RootTile().toString(), is("" +
                        "# C #" + NEWLINE +
                        "R # R" + NEWLINE +
                        "# L #"
                )
        );
    }

    @Test
    public void testCoordinates() throws Exception {
        assertThat(new Coordinate(3, 4).toString(), is("3 4"));
    }

    @Test
    public void testTile() throws Exception {

        assertThat(manager.create(RIGHT, TileType.DEFAULT, NORMAL).toString(), is("" +
                        "# C #" + NEWLINE +
                        "R   R" + NEWLINE +
                        "# L #"
                )
        );
    }

    @Test
    public void testTileRotated() throws Exception {

        assertThat(manager.create(RIGHT, TileType.DEFAULT, Rotation.HALF).toString(), is("" +
                        "# L #" + NEWLINE +
                        "R   R" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testTileDowntown() throws Exception {
        assertThat(manager.create(ABOVE, TileType.CITY_CENTER, NORMAL).toString(), is("" +
                        "# C #" + NEWLINE +
                        "C   C" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testTileQuarry() throws Exception {
        assertThat(manager.create(new Coordinate(-1, 0), TileType.QUARRY_ENTRY, Rotation.QUARTER).toString(), is("" +
                        "# L #" + NEWLINE +
                        "M   R" + NEWLINE +
                        "# L #"
                )
        );
    }

    @Test
    public void testTileQuarryEnd() throws Exception {
        assertThat(manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF).toString(), is("" +
                        "# L #" + NEWLINE +
                        "L   L" + NEWLINE +
                        "# M #"
                )
        );
    }

    @Test
    public void testFullQuarry() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(new Coordinate(0, -2), TileType.QUARRY, NORMAL).toString(), is("" +
                        "# M #" + NEWLINE +
                        "M   M" + NEWLINE +
                        "# M #"
                )
        );
    }

    @Test
    public void testSideQuarry() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(new Coordinate(0, -2), TileType.QUARRY_SIDE, NORMAL).toString(), is("" +
                        "# M #" + NEWLINE +
                        "M   L" + NEWLINE +
                        "# L #"
                )
        );
    }

    @Test
    public void testSideCity() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(ABOVE, TileType.CITY_SIDE, Rotation.HALF).toString(), is("" +
                        "# L #" + NEWLINE +
                        "L   C" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testEndCity() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(ABOVE, TileType.CITY_END, Rotation.HALF).toString(), is("" +
                        "# L #" + NEWLINE +
                        "L   L" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testCityEntry() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(ABOVE, TileType.CITY_ENTRY, Rotation.HALF).toString(), is("" +
                        "# R #" + NEWLINE +
                        "L   L" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testCityEdge() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(ABOVE, TileType.CITY_EDGE, Rotation.HALF).toString(), is("" +
                        "# L #" + NEWLINE +
                        "C   C" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testCityEdgeEntry() throws Exception {
        manager.create(BELOW, TileType.QUARRY_END, Rotation.HALF);
        assertThat(manager.create(ABOVE, TileType.CITY_EDGE_ENTRY, Rotation.HALF).toString(), is("" +
                        "# R #" + NEWLINE +
                        "C   C" + NEWLINE +
                        "# C #"
                )
        );
    }

    @Test
    public void testStraightRoad() throws Exception {
        assertThat(manager.create(RIGHT, TileType.ROAD_STRAIGHT, Rotation.QUARTER).toString(), is("" +
                        "# L #" + NEWLINE +
                        "R   R" + NEWLINE +
                        "# L #"
                )
        );
    }

    @Test
    public void testRoadCorner() throws Exception {
        assertThat(manager.create(new Coordinate(-1, 0), TileType.ROAD_CORNER, NORMAL).toString(), is("" +
                        "# L #" + NEWLINE +
                        "L   R" + NEWLINE +
                        "# R #"
                )
        );
    }

    @Test
    public void testJunctionFull() throws Exception {
        assertThat(manager.create(new Coordinate(-1, 0), TileType.JUNCTION_FULL, NORMAL).toString(), is("" +
                        "# R #" + NEWLINE +
                        "R   R" + NEWLINE +
                        "# R #"
                )
        );
    }
    @Test
    public void testJunction() throws Exception {
        assertThat(manager.create(new Coordinate(-1, 0), TileType.JUNCTION, NORMAL).toString(), is("" +
                        "# L #" + NEWLINE +
                        "R   R" + NEWLINE +
                        "# R #"
                )
        );
    }
    @Test
    public void testNature() throws Exception {
        assertThat(manager.create(BELOW, TileType.NATURE, NORMAL).toString(), is("" +
                        "# L #" + NEWLINE +
                        "L   L" + NEWLINE +
                        "# L #"
                )
        );
    }

}