package org.devathon.contest2016.model;

import org.devathon.contest2016.tiles.Type;
import org.devathon.contest2016.tiles.WorldSide;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
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
    public void testCreateNewOptionalOfSameType() throws Exception {
        assertThat(side.getOther().getType(), is(Type.CITY));
    }

    @Test
    public void testReturnOtherOtherIsSelf() throws Exception {
        assertThat(side.getOther().getOther(), is(side));
    }

    @Test
    public void testGetOtherIsConstant() throws Exception {
        assertThat(side.getOther(), is(side.getOther()));
    }


}