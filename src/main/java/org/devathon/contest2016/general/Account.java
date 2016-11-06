package org.devathon.contest2016.general;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class Account {


    private int machines;
    private int material;
    private AccountManager accountManager;

    public Account(int machines, int material, AccountManager accountManager) {
        this.material = material;
        this.accountManager = accountManager;
        if (machines < 0) throw new IllegalArgumentException("Insufficient funds.");
        this.machines = machines;
    }

    public Account(int machines, int material) {
        this(machines, material, null);
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
        if (this.accountManager != null) {
            accountManager.handleAccountChange(this);
        }
    }

    public void receive(Account income) {
        machines += income.getMachines();
        material += income.getMaterial();
    }

    public int getMaterial() {
        return material;
    }
}
