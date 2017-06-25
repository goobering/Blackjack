package example.codeclan.com.blackjack;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import example.codeclan.com.blackjack.enums.Suit;
import example.codeclan.com.blackjack.enums.Value;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23/06/2017.
 */

public class TestDeck
{
    private Card card;
    private Deck deck;

    @Before
    public void before()
    {
        card = new Card(Suit.SPADES.getSuit(), Value.ACE.getName(), Value.ACE.getValue());
        deck = new Deck();
    }

    @Test
    public void testGetNumCards()
    {
        assertEquals(0, deck.numCards());
    }

    @Test
    public void testMakeDeck()
    {
        //Test for 52 cards in deck
        deck.makeDeck();
        assertEquals(52, deck.numCards());

        ArrayList<Card> drawnCards = new ArrayList<Card>();
        for(int i = 0; i < deck.numCards(); i++)
        {
            drawnCards.add(deck.drawFirstCard());
        }

        // Test all cards are unique - set contents are always distinct
        Set<Card> cards = new HashSet<Card>(drawnCards);
        assertEquals(false, cards.size() < drawnCards.size());
    }

    @Test
    public void testDrawFirstCard()
    {
        deck.makeDeck();
        //Deck is unshuffled - first card is always two of spades
        card = new Card(Suit.SPADES.getSuit(), Value.TWO.getName(), Value.TWO.getValue());
        Card drawnCard = deck.drawFirstCard();

        assertEquals(card.getName(), drawnCard.getName());
        assertEquals(card.getValue(), drawnCard.getValue());
        assertEquals(card.getSuit(), drawnCard.getSuit());
    }
}
