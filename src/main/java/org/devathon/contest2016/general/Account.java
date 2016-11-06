package org.devathon.contest2016.general;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class Account {


    private int machines;

    public Account(int machines) {
        this.machines = machines;
    }

    public int getMachines() {
        return machines;
    }

    public boolean canAfford(Account other) {
        return other.getMachines() <= machines;
    }

    public void buy(Account other) {
        if (!canAfford(other)) throw new IllegalArgumentException("Insufficient funds.");
        this.machines -= other.getMachines();
    }
}
