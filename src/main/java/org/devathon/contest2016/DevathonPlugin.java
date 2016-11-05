package org.devathon.contest2016;

import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.tiles.RootTile;

import java.util.logging.Logger;

public class DevathonPlugin extends JavaPlugin {

    private Logger log = Logger.getLogger("minecraft");

    @Override
    public void onEnable() {
        log.info("[Machines] Plugin started.");
        new RootTile();
    }

    @Override
    public void onDisable() {}
}

