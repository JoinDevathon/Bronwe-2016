package org.devathon.contest2016;

import java.util.Objects;

/**
 * Created by Voronwe on 11/5/2016.
 */
public class Pair<O, T> {

    private final O one;
    private final T two;

    public Pair(O one, T two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public boolean equals(Object o) {
        return o == this
                || o != null
                && o instanceof Pair
                && ((Pair) o).getOne().equals(getOne())
                && ((Pair) o).getTwo().equals(getTwo());
    }

    public O getOne() {
        return one;
    }

    public T getTwo() {
        return two;
    }

    @Override
    public int hashCode() {
        return Objects.hash(one, two);
    }
}
