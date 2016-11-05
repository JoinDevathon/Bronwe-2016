package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Type;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.ArrayList;
import java.util.List;

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

    public Type[] getTypes() {
        return new Type[]{top, right, bottom, left};
    }

    public List<Side> asSidesList(Tile rootTile) {
        List<Side> sides = new ArrayList<>(4);

        for (Type type : getTypes()) {
            sides.add(new WorldSide(rootTile, type));
        }

        return sides;
    }
}
