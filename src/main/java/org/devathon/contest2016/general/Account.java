package org.devathon.contest2016.general;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class Account {


    private int machines;
    private int material;

    public Account(int machines, int material) {
        this.material = material;
        if (machines < 0) throw new IllegalArgumentException("Insufficient funds.");
        this.machines = machines;
    }

    public int getMachines() {
        return machines;
    }

    public boolean canAfford(Account other) {
        return other.getMachines() <= machines
                && other.getMaterial() <= material;
    }

    public void buy(Account other) {
        if (!canAfford(other)) throw new IllegalArgumentException("Insufficient funds.");
        this.machines -= other.getMachines();
        this.material -= other.getMaterial();
    }

    public void receive(Account income) {
        machines += income.getMachines();
        material += income.getMaterial();
    }

    public int getMaterial() {
        return material;
    }
}
