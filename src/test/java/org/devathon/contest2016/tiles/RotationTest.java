package org.devathon.contest2016.tiles;

import org.junit.Test;

import static org.devathon.contest2016.tiles.Rotation.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class RotationTest {

    @Test
    public void testRotate() throws Exception {
        assertThat(rotate(NORMAL, QUARTER), is(QUARTER));
    }

    @Test
    public void testRotateDifferentBase() throws Exception {
        assertThat(rotate(THREE_QUARTER, HALF), is(QUARTER));
    }

}