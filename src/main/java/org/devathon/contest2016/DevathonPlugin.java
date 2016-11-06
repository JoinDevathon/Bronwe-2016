package org.devathon.contest2016;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.general.AccountManager;
import org.devathon.contest2016.general.Coordinate;
import org.devathon.contest2016.general.Rotation;
import org.devathon.contest2016.tiles.RootTile;
import org.devathon.contest2016.tiles.TileType;
import org.devathon.contest2016.tiles.interfaces.Manager;

import java.util.logging.Logger;

public class DevathonPlugin extends JavaPlugin {

    private Logger log = Logger.getLogger("minecraft");
    private Manager manager;
    private AccountManager accountManager;

    @Override
    public void onEnable() {
        log.info("[Machines] Plugin started.");
        manager = new RootTile().getManager();
        accountManager = new AccountManager();
        getServer().getPluginManager().registerEvents(accountManager, this);

        World world = getServer().getWorld("world");
        Chunk chunk = world.getChunkAt(new Location(world, 0, 0, 0));
        chunk.getBlock(0, 100, 0).setType(Material.STONE);
    }

    @Override
    public void onDisable() {
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chunk chunk = player.getLocation().getChunk();

            attemptCommandExecution(command.getName(), player, chunk);

        } else {
            sender.sendMessage("You need to be a player.");
        }
        return true;
    }

    private void attemptCommandExecution(String commandName, Player player, Chunk chunk) {
        Coordinate coord = new Coordinate(chunk.getX(), chunk.getZ());
        try {
            if (commandName.equalsIgnoreCase("get")) {
                player.sendMessage(manager.get(coord).get().toString());
            } else if (commandName.equalsIgnoreCase("create")) {
                String newTile = manager.create(coord, TileType.DEFAULT, Rotation.NORMAL).toString();
                player.sendMessage(newTile);
                highlight(chunk);
            }
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + e.getMessage());
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
}

