package org.devathon.contest2016.tiles;


import org.devathon.contest2016.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class Coordinate extends Pair<Integer, Integer> {

    /**
     * Creates a new coordinate.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Coordinate(int x, int y) {
        super(x, y);
    }

    public List<Coordinate> getNeighbors() {
        Coordinate above = create(0, 1);
        Coordinate left = create(1, 0);
        Coordinate below = create(0, -1);
        Coordinate right = create(-1, 0);
        return Arrays.asList(above, left, below, right);
    }

    private Coordinate create(Integer key, int y) {
        return new Coordinate(getOne() + key, getTwo() + y);
    }

}
