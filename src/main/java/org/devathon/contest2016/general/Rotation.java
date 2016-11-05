package org.devathon.contest2016.general;

/**
 * Created by Voronwe on 11/5/2016.
 */
public enum Rotation {

    NORMAL(2),
    QUARTER(3),
    HALF(0),
    THREE_QUARTER(1);

    private int opposite;

    Rotation(int opposite) {
        this.opposite = opposite;
    }

    public static int opposite(int input) {
        return values()[input].opposite;
    }

    public static Rotation rotate(Rotation normal, Rotation rotate) {
        return values()[(normal.ordinal() + rotate.ordinal()) % 4];
    }
}
