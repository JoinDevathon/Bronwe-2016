package org.devathon.contest2016.tiles;

/**
 * Created by Voronwe on 11/5/2016.
 */
public enum TileType {

    DEFAULT(Type.CITY, Type.ROAD, Type.LAND, Type.ROAD);

    private final Type top;
    private final Type right;
    private final Type bottom;
    private final Type left;

    TileType(Type top, Type right, Type bottom, Type left) {

        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public Type getLeft() {
        return left;
    }

    public Type getBottom() {
        return bottom;
    }

    public Type getRight() {
        return right;
    }

    public Type getTop() {
        return top;
    }
}
