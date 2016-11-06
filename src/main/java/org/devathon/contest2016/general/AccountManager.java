package org.devathon.contest2016.general;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class AccountManager implements Listener {

    private Map<Player, Account> accounts = new HashMap<>();

    public Account addPlayer(Player player) {
        if (!accounts.containsKey(player))
            accounts.put(player, new Account(20, 100));
        return get(player);
    }

    public Account get(Player player) {
        return accounts.get(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        addPlayer(event.getPlayer());
    }
}
