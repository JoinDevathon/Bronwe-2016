package org.devathon.contest2016.general;

import org.bukkit.Location;
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
            accounts.put(player, new Account(20, 100, this));
        handleAccountChange(get(player));
        return get(player);
    }

    public Account get(Player player) {
        return accounts.get(player);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        addPlayer(player);
        player.teleport(new Location(event.getPlayer().getWorld(), 8, 100, 8));
    }

    void handleAccountChange(Account account) {
        if (accounts.containsValue(account)) {
            accounts.entrySet().stream().filter(e -> e.getValue().equals(account)).findFirst().ifPresent(
                    e -> sendAccountInfo(account, e.getKey())
            );
        }
    }

    private void sendAccountInfo(Account account, Player player) {
        player.sendMessage(String.format("You now have %d Machines and %d Material.",
                account.getMachines(), account.getMaterial()));
    }
}
