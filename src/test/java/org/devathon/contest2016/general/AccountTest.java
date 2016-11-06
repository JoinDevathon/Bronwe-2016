package org.devathon.contest2016.general;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Voronwe on 11/6/2016.
 */
public class AccountTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testGetAccount() throws Exception {
        assertThat(new Account(5).getMachines(), is(5));
    }

    @Test
    public void testCanAfford() throws Exception {
        Account myAccount = new Account(5);
        assertThat(myAccount.canAfford(new Account(3)), is(true));
    }

    @Test
    public void testCanNotAfford() throws Exception {
        Account myAccount = new Account(5);
        assertThat(myAccount.canAfford(new Account(10)), is(false));
    }

    @Test
    public void testBuy() throws Exception {
        Account myAccount = new Account(5);
        myAccount.buy(new Account(3));
        assertThat(myAccount.getMachines(), is(2));
    }

    @Test
    public void testInsufficientFunds() throws Exception {
        Account myAccount = new Account(5);
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Insufficient funds.");
        myAccount.buy(new Account(7));
    }

}