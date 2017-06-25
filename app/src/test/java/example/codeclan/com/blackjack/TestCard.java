package example.codeclan.com.blackjack;

import org.junit.Before;
import org.junit.Test;

import example.codeclan.com.blackjack.enums.Suit;
import example.codeclan.com.blackjack.Card;
import example.codeclan.com.blackjack.enums.Value;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23/06/2017.
 */

public class TestCard
{
    private Card card;

    @Before
    public void before()
    {
        card = new Card(Suit.SPADES.getSuit(), Value.ACE.getName(), Value.ACE.getValue());
    }

    @Test
    public void testGetSuit()
    {
        assertEquals("Spades", card.getSuit());
    }

    @Test
    public void testGetName()
    {
        assertEquals("ACE", card.getName());
    }

    @Test
    public void testGetValue()
    {
        assertEquals(11, card.getValue());
    }

    @Test
    public void testSetValue()
    {
        card.setValue(999);
        assertEquals(999, card.getValue());
    }
}
