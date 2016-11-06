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
import org.devathon.contest2016.tiles.interfaces.Side;
import org.devathon.contest2016.tiles.interfaces.Tile;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static org.bukkit.Material.AIR;

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
        highlight(chunk);
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
                player.sendMessage(Arrays.toString(manager.get(coord).get().getSides().toArray()));
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
            Tile newTile = manager.create(coord, tileManager.getCurrentTile(player), Rotation.valueOf(args[0].toUpperCase()));
            player.sendMessage(Arrays.toString(newTile.getSides().toArray()));
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
        Tile current = manager.get(new Coordinate(chunk.getX(), chunk.getZ())).get();
        Material center = current.getCenter().getMaterial();
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                Block block = chunk.getBlock(i, 100, j);
                block.setType(center);
            }
        }
        chunk.getBlock(0, 100, 15).setType(AIR);
        chunk.getBlock(15, 100, 0).setType(AIR);
        chunk.getBlock(0, 100, 0).setType(AIR);
        chunk.getBlock(15, 100, 15).setType(AIR);

        List<Side> sides = current.getSides();
        Material top = sides.get(0).getType().getMaterial();
        Material right = sides.get(1).getType().getMaterial();
        Material bottom = sides.get(2).getType().getMaterial();
        Material left = sides.get(3).getType().getMaterial();
        for (int i = 1; i < 15; i++) {
            chunk.getBlock(i, 100, 15).setType(top);
            chunk.getBlock(15, 100, i).setType(right);
            chunk.getBlock(i, 100, 0).setType(bottom);
            chunk.getBlock(0, 100, i).setType(left);
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

