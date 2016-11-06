package org.devathon.contest2016.general;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class AccountManagerTest {

    @Test
    public void testAddPlayerInitialMachines() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        assertThat(accountManager.get(player).getMachines(), is(20));
    }

    @Test
    public void testAddPlayerInitialMaterials() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        assertThat(accountManager.get(player).getMaterial(), is(100));
    }

    @Test
    public void testAddPlayerBuyAccount() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        accountManager.get(player).buy(new Account(10, 75));
        assertThat(accountManager.get(player).getMachines(), is(10));
    }

    @Test
    public void testAddTwice() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player);
        accountManager.get(player).buy(new Account(10, 75));
        accountManager.addPlayer(player);
        assertThat(accountManager.get(player).getMachines(), is(10));
    }

    @Test
    public void testGetPlayerAccount() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        assertThat(accountManager.addPlayer(player).getMachines(), is(20));
    }

    @Test
    public void testLoginEvent() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        PlayerJoinEvent event = new PlayerJoinEvent(player, "Hi");
        accountManager.onPlayerJoin(event);
        accountManager.get(player).buy(new Account(10, 75));
        accountManager.addPlayer(player);
        assertThat(accountManager.get(player).getMachines(), is(10));
    }

    @Test
    public void testBuyInitialMaterials() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player).buy(new Account(0, 75));
        assertThat(accountManager.get(player).getMaterial(), is(25));
    }

    @Test
    public void testReceiveMaterials() throws Exception {
        AccountManager accountManager = new AccountManager();
        Player player = new EmptyPlayer();
        accountManager.addPlayer(player).receive(new Account(0, 10));
        assertThat(accountManager.get(player).getMaterial(), is(110));
    }

    @Test
    public void testReceiveInitialAccountMessage() throws Exception {
        AccountManager accountManager = new AccountManager();
        AtomicInteger count = new AtomicInteger(-1);
        Player player = new EmptyPlayer() {
            @Override
            public void sendMessage(String s) {
                assertThat(s, is("You now have 20 Machines and 100 Material."));
                count.incrementAndGet();
            }
        };
        accountManager.addPlayer(player);
        assertThat(count.get(), is(0));
    }

    @Test
    public void testReceiveOtherAccountMessage() throws Exception {
        AccountManager accountManager = new AccountManager();
        AtomicInteger count = new AtomicInteger(-2);
        Player player = new EmptyPlayer() {
            @Override
            public void sendMessage(String s) {
                if (count.getAndIncrement() == -1)
                    assertThat(s, is("You now have 19 Machines and 99 Material."));
            }
        };
        accountManager.addPlayer(player).buy(new Account(1, 1));
        assertThat(count.get(), is(0));
    }

}