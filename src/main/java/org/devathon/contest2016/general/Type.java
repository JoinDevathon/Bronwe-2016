package org.devathon.contest2016.general;

import org.bukkit.Material;

/**
 * Created by Voronwe on 11/5/2016.
 */
public enum Type {

    CITY(Material.STONE), LAND(Material.GRASS), ROAD(Material.GRASS_PATH), MINE(Material.IRON_ORE);

    private Material material;

    Type(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
