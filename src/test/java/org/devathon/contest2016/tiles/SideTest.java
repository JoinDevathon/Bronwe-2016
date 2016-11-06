package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Type;
import org.devathon.contest2016.tiles.interfaces.Side;
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
        side = new WorldSide(null, Type.CITY, new TileCompound(null));
    }

    @Test
    public void testCreateSideCity() throws Exception {
        assertThat(side.getType(), is(Type.CITY));
    }

    @Test
    public void testCreateSideOther() throws Exception {
        WorldSide landSide = new WorldSide(null, Type.LAND, new TileCompound(null));
        assertThat(landSide.getType(), is(Type.LAND));
    }

    @Test
    public void testCreateEmptyOptional() throws Exception {
        assertThat(side.getOther(), is(nullValue()));
    }

    @Test
    public void testReturnOtherOtherIsSelf() throws Exception {
        new WorldSide(new RootTile(), side, new TileCompound(null));
        assertThat(side.getOther().getOther(), is(side));
    }

    @Test
    public void testGetOtherIsConstant() throws Exception {
        assertThat(side.getOther(), is(side.getOther()));
    }

    @Test
    public void testSetOtherIsOther() throws Exception {
        Side side = new WorldSide(new RootTile(), Type.CITY, new TileCompound(null));
        Side other = new WorldSide(new RootTile(), Type.CITY, new TileCompound(null));
        assertThat(side.setOther(other), is(other));
    }

    @Test
    public void testGetOtherIsOther() throws Exception {
        Side side = new WorldSide(new RootTile(), Type.CITY, new TileCompound(null));
        Side other = new WorldSide(new RootTile(), Type.CITY, new TileCompound(null));
        side.setOther(other);
        assertThat(side.getOther(), is(other));
    }

    @Test
    public void testSameCompound() throws Exception {
        Side side = new WorldSide(new RootTile(), Type.CITY, new TileCompound(null));
        Side other = new WorldSide(new RootTile(), Type.CITY, new TileCompound(null));
        side.setOther(other);
        assertThat(side.getCompound().getTiles() == other.getCompound().getTiles(), is(true));
    }

    @Test
    public void testCompoundContainsMembers() throws Exception {
        RootTile tile = new RootTile();
        Side side = new WorldSide(tile, Type.CITY, new TileCompound(tile));
        RootTile otherTile = new RootTile();
        Side other = new WorldSide(otherTile, Type.CITY, new TileCompound(otherTile));
        side.setOther(other);

        assertThat(side.getCompound().getTiles().contains(tile), is(true));
        assertThat(side.getCompound().getTiles().contains(otherTile), is(true));
    }

    @Test
    public void testCompoundContainsSideMembers() throws Exception {
        RootTile tile = new RootTile();
        Side side = new WorldSide(tile, Type.CITY, new TileCompound(tile));
        RootTile otherTile = new RootTile();
        Side other = new WorldSide(otherTile, Type.CITY, new TileCompound(otherTile));
        side.setOther(other);

        assertThat(side.getCompound().getMembers().contains(side), is(true));
        assertThat(side.getCompound().getMembers().contains(other), is(true));
    }


}