package example.codeclan.com.blackjack;

import java.util.ArrayList;

/**
 * Created by user on 23/06/2017.
 */

public class Player
{
    private String name;
    private ArrayList<Card> cards;

    public Player(String name)
    {
        this.name = name;
        cards = new ArrayList<Card>();
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Card> getCards()
    {
        return cards;
    }

    public void addCard(Card card)
    {
        cards.add(card);
    }

    public int sumCardValue()
    {
        int sum = 0;
        for(Card c : cards)
        {
            sum += c.getValue();
        }
        return sum;
    }

    public boolean hasHighAce()
    {
        boolean hasHighAce = false;
        for(Card card : cards)
        {
            if(card.getName().equals("ACE") && card.getValue() == 11)
            {
                hasHighAce = true;
            }
        }
        return hasHighAce;
    }

    public void swapOneHighAceLow()
    {
        int counter = 0;
        boolean swapped = false;

        while(swapped == false)
        {
            Card currentCard = cards.get(counter);
            if(currentCard.getName().equals("ACE") && currentCard.getValue() == 11)
            {
                currentCard.setValue(1);
                swapped = true;
            }
            counter++;
        }
    }

    public void dropCards()
    {
        cards.clear();
    }

    public boolean hasNatural()
    {
        return (getCards().size() == 2 && sumCardValue() == 21);
    }

    public boolean isBusted()
    {
        return sumCardValue() > 21;
    }

    public void convertBustedAces()
    {
        while(isBusted())
        {
            if(hasHighAce())
            {
                swapOneHighAceLow();
            }
            else
                break;
        }
    }
}
