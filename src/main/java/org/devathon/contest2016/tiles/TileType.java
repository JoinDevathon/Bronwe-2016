package org.devathon.contest2016.tiles;

import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.general.Type;
import org.devathon.contest2016.tiles.interfaces.Compound;
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.devathon.contest2016.general.Type.*;

/**
 * Created by Voronwe on 11/5/2016.
 */
public enum TileType {

    DEFAULT(CITY, ROAD, LAND, ROAD, ROAD),
    NATURE(LAND),

    CITY_ENTRY(CITY, LAND, ROAD, LAND),
    CITY_END(CITY, LAND, LAND, LAND),
    CITY_SIDE(CITY, LAND),
    CITY_EDGE(CITY, CITY, LAND, CITY),
    CITY_EDGE_ENTRY(CITY, CITY, ROAD, CITY),
    CITY_CENTER(CITY),

    ROAD_STRAIGHT(ROAD, LAND, ROAD, LAND),
    ROAD_CORNER(LAND, ROAD, ROAD, LAND, ROAD),
    JUNCTION_FULL(ROAD),
    JUNCTION(LAND, ROAD, ROAD, ROAD, ROAD),

    QUARRY_ENTRY(MINE, LAND, ROAD, LAND),
    QUARRY_END(MINE, LAND, LAND, LAND),
    QUARRY_SIDE(MINE, LAND),
    QUARRY(MINE);

    public static final int TILE_COUNT = 4;
    private final Type top;
    private final Type right;
    private final Type bottom;
    private final Type left;
    private final Type center;

    TileType(Type top, Type right, Type bottom, Type left, Type center) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
        this.center = center;
    }

    TileType(Type top, Type right, Type bottom, Type left) {
        this(top, right, bottom, left, top);
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

    public Type getCenter() {
        return center;
    }

    public List<Side> asSidesList(Tile rootTile, Rotation rotation) {
        List<Side> sides = new ArrayList<>(TILE_COUNT);
        Compound mainCompound = new TileCompound(rootTile);

        Type[] types = getTypes();
        for (int i = 0; i < TILE_COUNT; i++) {
            Type type = types[Rotation.rotate(Rotation.values()[i], rotation).ordinal()];
            sides.add(new WorldSide(rootTile, type, type.equals(getCenter()) ? mainCompound : new TileCompound(rootTile)));
        }

        return sides;
    }

    @Override
    public String toString() {
        return String.format("%s %s", super.toString(), Arrays.toString(getTypes()));
    }
}
