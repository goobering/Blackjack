package example.codeclan.com.blackjack;

import java.util.ArrayList;
import java.util.Collections;

import example.codeclan.com.blackjack.enums.Suit;
import example.codeclan.com.blackjack.enums.Value;


/**
 * Created by user on 22/06/2017.
 */

public class Deck
{
    private ArrayList<Card> cards;

    public Deck()
    {
        cards = new ArrayList<Card>();
    }

    private void addCard(Card card)
    {
        cards.add(card);
    }

    public void makeDeck()
    {
        Suit[] suits = Suit.values();
        Value[] values = Value.values();

        for (Suit suit : suits)
        {
            for (Value value : values)
            {
                Card newCard = new Card(suit.getSuit(), value.getName(), value.getValue());
                addCard(newCard);
            }
        }
    }

    public void shuffle()
    {
        Collections.shuffle(cards);
    }

    private void removeFromDeckByIndex(int index)
    {
        cards.remove(index);
    }

    public Card drawFirstCard()
    {
        Card drawnCard = cards.get(0);
        removeFromDeckByIndex(0);
        return drawnCard;
    }

    public int numCards()
    {
        return cards.size();
    }
}
