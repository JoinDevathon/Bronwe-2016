package org.devathon.contest2016.tiles;

import org.bukkit.entity.Player;
import org.devathon.contest2016.general.Account;
import org.devathon.contest2016.general.AccountManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class PlayerTileManager {
    private final AccountManager accountManager;
    private final Map<Player, TileType> currentTiles;

    public PlayerTileManager(AccountManager accountManager) {

        this.accountManager = accountManager;
        currentTiles = new HashMap<>();
    }

    public TileType generateRandomTile(Player player) {
        accountManager.get(player).buy(new Account(10, 0));

        Random random = new Random();
        int rand = random.nextInt(TileType.values().length);
        TileType randomTile = TileType.values()[rand];
        currentTiles.put(player, randomTile);
        return randomTile;
    }

    public TileType getCurrentTile(Player player) {
        TileType tileType = currentTiles.get(player);
        if (tileType == null) throw new IllegalArgumentException("No tile available.");
        return tileType;
    }

    public void resetTile(Player player) {
        currentTiles.put(player, null);
    }
}
