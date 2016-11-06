package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.general.Type;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.ArrayList;
import java.util.List;

import static org.devathon.contest2016.general.Type.*;

/**
 * Created by Voronwe on 11/5/2016.
 */
public enum TileType {

    DEFAULT(CITY, ROAD, LAND, ROAD),
    NATURE(LAND),

    CITY_ENTRY(CITY, LAND, ROAD, LAND),
    CITY_END(CITY, LAND, LAND, LAND),
    CITY_SIDE(CITY, LAND),
    CITY_EDGE(CITY, CITY, LAND, CITY),
    CITY_EDGE_ENTRY(CITY, CITY, ROAD, CITY),
    CITY_CENTER(CITY),

    ROAD_STRAIGHT(ROAD, LAND, ROAD, LAND),
    ROAD_CORNER(LAND, ROAD),
    JUNCTION_FULL(ROAD),
    JUNCTION(LAND, ROAD, ROAD, ROAD),

    QUARRY_ENTRY(MINE, LAND, ROAD, LAND),
    QUARRY_END(MINE, LAND, LAND, LAND),
    QUARRY_SIDE(MINE, LAND),
    QUARRY(MINE);

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

    TileType(Type allSides) {
        this(allSides, allSides, allSides, allSides);
    }

    TileType(Type firstHalf, Type secondHalf) {
        this(firstHalf, secondHalf, secondHalf, firstHalf);
    }

    public Type[] getTypes() {
        return new Type[]{top, right, bottom, left};
    }

    public List<Side> asSidesList(Tile rootTile, Rotation rotation) {
        List<Side> sides = new ArrayList<>(4);

        Type[] types = getTypes();
        for (int i = 0, typesLength = types.length; i < typesLength; i++) {
            Type type = types[Rotation.rotate(Rotation.values()[i], rotation).ordinal()];
            sides.add(new WorldSide(rootTile, type));
        }

        return sides;
    }
}
