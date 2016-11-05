package org.devathon.contest2016.tiles;

import org.devathon.contest2016.tiles.general.RootTile;
import org.devathon.contest2016.tiles.general.WorldSide;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class SideTest {

    private WorldSide side;

    @Before
    public void setUp() throws Exception {
        side = new WorldSide(null, Type.CITY);
    }

    @Test
    public void testCreateSideCity() throws Exception {
        assertThat(side.getType(), is(Type.CITY));
    }

    @Test
    public void testCreateSideOther() throws Exception {
        WorldSide landSide = new WorldSide(null, Type.LAND);
        assertThat(landSide.getType(), is(Type.LAND));
    }

    @Test
    public void testCreateEmptyOptional() throws Exception {
        assertThat(side.getOther(), is(nullValue()));
    }

    @Test
    public void testReturnOtherOtherIsSelf() throws Exception {
        new WorldSide(new RootTile(), side);
        assertThat(side.getOther().getOther(), is(side));
    }

    @Test
    public void testGetOtherIsConstant() throws Exception {
        assertThat(side.getOther(), is(side.getOther()));
    }

    @Test
    public void testSetOtherIsOther() throws Exception {
        Side side = new WorldSide(new RootTile(), Type.CITY);
        Side other = new WorldSide(new RootTile(), Type.CITY);
        assertThat(side.setOther(other), is(other));
    }
    @Test
    public void testGetOtherIsOther() throws Exception {
        Side side = new WorldSide(new RootTile(), Type.CITY);
        Side other = new WorldSide(new RootTile(), Type.CITY);
        side.setOther(other);
        assertThat(side.getOther(), is(other));
    }


}