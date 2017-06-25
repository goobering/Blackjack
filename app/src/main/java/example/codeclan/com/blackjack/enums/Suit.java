package example.codeclan.com.blackjack.enums;

/**
 * Created by user on 22/06/2017.
 */

public enum Suit
{
    SPADES("Spades"),
    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts");

    private String suit;

    Suit(String suit)
    {
        this.suit = suit;
    }

    public String getSuit()
    {
        return suit;
    }
}

