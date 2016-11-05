package org.devathon.contest2016.tiles.general;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class DisplayTest {

    public static final String NEWLINE = String.format("%n");

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
    public void testTile() throws Exception {
        ;
        assertThat(new RootTile().getManager().create(1, 0).toString(), is("" +
                        "# n #" + NEWLINE +
                        "R   n" + NEWLINE +
                        "# n #"
                )
        );
    }

}