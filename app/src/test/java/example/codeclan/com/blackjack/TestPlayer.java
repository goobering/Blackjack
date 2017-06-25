package example.codeclan.com.blackjack;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import example.codeclan.com.blackjack.enums.Suit;
import example.codeclan.com.blackjack.enums.Value;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23/06/2017.
 */

public class TestPlayer
{
    private Player player;
    private Card aceSpadesCard, aceHeartsCard, aceDiamondsCard, kingSpadesCard, tenSpadesCard, sevenSpadesCard;

    @Before
    public void before()
    {
        player = new Player("TestPlayer");
        aceSpadesCard = new Card(Suit.SPADES.getSuit(), Value.ACE.getName(), Value.ACE.getValue());
        aceHeartsCard = new Card(Suit.HEARTS.getSuit(), Value.ACE.getName(), Value.ACE.getValue());
        aceDiamondsCard = new Card(Suit.DIAMONDS.getSuit(), Value.ACE.getName(), Value.ACE.getValue());
        kingSpadesCard = new Card(Suit.SPADES.getSuit(), Value.KING.getName(), Value.KING.getValue());
        tenSpadesCard = new Card(Suit.SPADES.getSuit(), Value.TEN.getName(), Value.TEN.getValue());
        sevenSpadesCard = new Card(Suit.SPADES.getSuit(), Value.SEVEN.getName(), Value.SEVEN.getValue());
    }

    @Test
    public void testGetName()
    {
        assertEquals("TestPlayer", player.getName());
    }

    @Test
    public void testAddCard()
    {
        player.addCard(aceSpadesCard);
        Card playerCard = player.getCards().get(0);

        assertEquals(aceSpadesCard.getName(), playerCard.getName());
        assertEquals(aceSpadesCard.getValue(), playerCard.getValue());
        assertEquals(aceSpadesCard.getSuit(), playerCard.getSuit());
    }

    @Test
    public void testGetCards()
    {
        for (int i = 0; i < 52; i++)
        {
            player.addCard(aceSpadesCard);
        }

        ArrayList<Card> playerCards = player.getCards();

        //Test 52 cards returned
        assertEquals(52, playerCards.size());

        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for (int i = 0; i < playerCards.size(); i++)
        {
            drawnCards.add(playerCards.get(i));
        }

        // Test all cards are returned (set should only contain ace of spades)
        Set<Card> cards = new HashSet<Card>(drawnCards);
        assertEquals(1, cards.size());
    }

    @Test
    public void testSumCardValue()
    {
        //Add 10 aces @ 11 points each:
        for (int i = 0; i < 10; i++)
        {
            player.addCard(aceSpadesCard);
        }

        assertEquals(110, player.sumCardValue());
    }

    @Test
    public void testHasHighAce()
    {
        assertEquals(false, player.hasHighAce());
        player.addCard(aceSpadesCard);
        assertEquals(true, player.hasHighAce());
    }

    @Test
    public void testSwapOneHighAceLow()
    {
        player.addCard(aceSpadesCard);
        player.addCard(aceHeartsCard);
        player.addCard(aceDiamondsCard);

        assertEquals(33, player.sumCardValue());
        player.swapOneHighAceLow();
        assertEquals(23, player.sumCardValue());
        player.swapOneHighAceLow();
        assertEquals(13, player.sumCardValue());
        player.swapOneHighAceLow();
        assertEquals(3, player.sumCardValue());
    }

    @Test
    public void testDropCards()
    {
        for (int i = 0; i < 52; i++)
        {
            player.addCard(aceSpadesCard);
        }

        ArrayList<Card> playerCards = player.getCards();

        //Test 52 cards returned
        assertEquals(52, playerCards.size());

        player.dropCards();

        //Test hand value is 0
        assertEquals(0, player.sumCardValue());
        //Test hand size is 0
        assertEquals(0, player.getCards().size());
    }

    @Test
    public void testHasNatural()
    {
        assertEquals(false, player.hasNatural());

        player.addCard(aceSpadesCard);
        player.addCard(tenSpadesCard);

        assertEquals(true, player.hasNatural());
    }

    @Test
    public void testIsBusted()
    {
        assertEquals(false, player.isBusted());

        player.addCard(tenSpadesCard);
        player.addCard(aceSpadesCard);
        player.addCard(aceHeartsCard);

        assertEquals(true, player.isBusted());
    }

    @Test
    public void testConvertOneBustedAce()
    {
        player.addCard(tenSpadesCard);
        player.addCard(sevenSpadesCard);
        player.addCard(aceSpadesCard);

        assertEquals(true, player.isBusted());
        assertEquals(28, player.sumCardValue());
        player.convertBustedAces();
        assertEquals(false, player.isBusted());
        assertEquals(18, player.sumCardValue());
    }

    @Test
    public void testConvertTwoBustedAces()
    {
        player.addCard(tenSpadesCard);
        player.addCard(aceSpadesCard);
        player.addCard(aceHeartsCard);
        assertEquals(true, player.isBusted());
        assertEquals(32, player.sumCardValue());
        player.convertBustedAces();
        assertEquals(false, player.isBusted());
        assertEquals(12, player.sumCardValue());
    }
}
