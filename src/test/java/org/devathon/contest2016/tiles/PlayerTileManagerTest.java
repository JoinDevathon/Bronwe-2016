package org.devathon.contest2016.tiles;

import org.bukkit.entity.Player;
import org.devathon.contest2016.general.Account;
import org.devathon.contest2016.general.AccountManager;
import org.devathon.contest2016.general.EmptyPlayer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.EnumSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class PlayerTileManagerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testBuyRandomTile() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        tileManager.generateRandomTile(player);
        assertThat(accountManager.get(player).getMachines(), is(10));

    }

    @Test
    public void testBuyRandomTileInsufficientFunds() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        Account account = accountManager.get(player);
        account.buy(new Account(20, 0));
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Insufficient funds.");
        tileManager.generateRandomTile(player);
    }

    @Test
    public void testBuyRandomTileIsTile() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        TileType type = tileManager.generateRandomTile(player);
        assertThat(type, is(notNullValue()));
    }

    @Test(timeout = 400L)
    public void testBuyRandomTileIsRandomTile() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player).receive(new Account(10000000, 10000000)); //Lucky!
        Set<TileType> tiles = EnumSet.allOf(TileType.class);

        while (tiles.size() > 0) {
            tiles.remove(tileManager.generateRandomTile(player));
        }
    }

    @Test
    public void testBuyRandomSavesRandomTile() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        for (int i = 0; i < 10; i++) {
            Player player = new EmptyPlayer();
            accountManager.addPlayer(player);
            TileType type = tileManager.generateRandomTile(player);
            assertThat(tileManager.getCurrentTile(player), is(type));
        }
    }

    @Test
    public void testBuyRandomDeletesCurrent() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        tileManager.generateRandomTile(player);
        tileManager.resetTile(player);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No tile available.");
        tileManager.getCurrentTile(player);
    }

    @Test
    public void testGetNoGeneratedThrowsException() throws Exception {
        AccountManager accountManager = new AccountManager();
        PlayerTileManager tileManager = new PlayerTileManager(accountManager);
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("No tile available.");
        tileManager.getCurrentTile(player);
    }

}