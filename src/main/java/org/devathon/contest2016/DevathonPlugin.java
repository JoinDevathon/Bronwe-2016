package org.devathon.contest2016;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.general.Account;
import org.devathon.contest2016.general.AccountManager;
import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.tiles.PlayerTileManager;
import org.devathon.contest2016.tiles.RootTile;
import org.devathon.contest2016.tiles.interfaces.Manager;

import java.util.logging.Logger;

public class DevathonPlugin extends JavaPlugin {

    public static final Account INCOME = new Account(0, 100);
    private Logger log = Logger.getLogger("minecraft");
    private Manager manager;
    private AccountManager accountManager;
    private PlayerTileManager tileManager;

    @Override
    public void onEnable() {
        log.info("[Machines] Plugin started.");
        manager = new RootTile().getManager();
        accountManager = new AccountManager();
        tileManager = new PlayerTileManager(accountManager);
        getServer().getPluginManager().registerEvents(accountManager, this);

        World world = getServer().getWorld("world");
        Chunk chunk = world.getChunkAt(new Location(world, 0, 0, 0));
        chunk.getBlock(0, 100, 0).setType(Material.STONE);
        new IncomeRunnable(this);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chunk chunk = player.getLocation().getChunk();

            attemptCommandExecution(command.getName(), player, chunk, args);

        } else {
            sender.sendMessage("You need to be a player.");
        }
        return true;
    }

    private void attemptCommandExecution(String commandName, Player player, Chunk chunk, String[] args) {
        Coordinate coord = new Coordinate(chunk.getX(), chunk.getZ());
        try {
            if (commandName.equalsIgnoreCase("get")) {
                player.sendMessage(tileManager.generateRandomTile(player).toString());
            } else if (commandName.equalsIgnoreCase("info")) {
                player.sendMessage(manager.get(coord).get().toString());
            } else if (commandName.equalsIgnoreCase("buy")) {
                buyCommand(player, args);
            } else if (commandName.equalsIgnoreCase("create")) {
                createCommand(player, chunk, args, coord);
            }
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
        }
    }

    private void createCommand(Player player, Chunk chunk, String[] args, Coordinate coord) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Specify a rotation!");
        } else {
            String newTile = manager.create(coord, tileManager.getCurrentTile(player), Rotation.valueOf(args[0].toUpperCase())).toString();
            player.sendMessage(newTile);
            highlight(chunk);
            tileManager.resetTile(player);
        }
    }

    private void buyCommand(Player player, String[] args) {
        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "Specify an amount of machines!");
        } else {
            Account account = accountManager.get(player);
            int amount = parseInt(args[0]);
            account.buy(new Account(0, 50 * amount));
            account.receive(new Account(amount, 0));
        }
    }

    private int parseInt(String arg) {
        try {

            return Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input: " + arg);
        }
    }

    private void highlight(Chunk chunk) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                Block block = chunk.getBlock(i, 100, j);
                if (block.getType().equals(Material.STONE))
                    block.setType(Material.GOLD_BLOCK);
                else
                    block.setType(Material.STONE);
            }
        }
    }

    private class IncomeRunnable implements Runnable {
        Server server;

        IncomeRunnable(Plugin plugin) {
            this.server = plugin.getServer();
            server.getScheduler().scheduleSyncRepeatingTask(plugin, this, 600, 600);
        }

        @Override
        public void run() {
            server.getOnlinePlayers().forEach(player -> accountManager.get(player).receive(INCOME));
        }
    }
}

