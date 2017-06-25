package example.codeclan.com.blackjack;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 23/06/2017.
 */

public class TestGame
{
    private Game game;
    private Deck deck;
    private Player player1, player2;

    @Before
    public void before()
    {
        //Deck is unshuffled:
        // [Spades, Clubs, Diamonds, Hearts]
        // [2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A]
        deck = new Deck();
        deck.makeDeck();

        game = new Game(deck);

        player1 = new Player("Alice");
        player2 = new Player("Bob");
    }

    @Test
    public void testGetPlayers()
    {
        game.addPlayer(player1);
        ArrayList<Player> players = game.getPlayers();
        assertEquals(1, players.size());
        assertEquals("Alice", players.get(0).getName());
    }

    @Test
    public void testAddPlayer()
    {
        assertEquals(0, game.getPlayers().size());
        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());
        assertEquals("Alice", game.getPlayers().get(0).getName());
    }

    @Test
    public void testGetDealer()
    {
        Player dealer = game.getDealer();
        assertEquals("Dealer", dealer.getName());
    }

    @Test
    public void testGetWinners()
    {
        game.addPlayer(player1);
        assertEquals(0, game.getWinners().size());
        game.movePlayerFromTo(player1, game.getPlayers(), game.getWinners());
        assertEquals(1, game.getWinners().size());
        assertEquals(player1, game.getWinners().get(0));
    }

    @Test
    public void testGetLosers()
    {
        game.addPlayer(player1);
        assertEquals(0, game.getLosers().size());
        game.movePlayerFromTo(player1, game.getPlayers(), game.getLosers());
        assertEquals(1, game.getLosers().size());
        assertEquals(player1, game.getLosers().get(0));
    }

    @Test
    public void testGetTiedPlayers()
    {
        game.addPlayer(player1);
        assertEquals(0, game.getTiedPlayers().size());
        game.movePlayerFromTo(player1, game.getPlayers(), game.getTiedPlayers());
        assertEquals(1, game.getTiedPlayers().size());
        assertEquals(player1, game.getTiedPlayers().get(0));
    }

    @Test
    public void testGetNaturals()
    {
        game.addPlayer(player1);
        assertEquals(0, game.getNaturals().size());
        game.movePlayerFromTo(player1, game.getPlayers(), game.getNaturals());
        assertEquals(1, game.getNaturals().size());
        assertEquals(player1, game.getNaturals().get(0));
    }

    @Test
    public void testGetLimboPlayers()
    {
        game.addPlayer(player1);
        assertEquals(0, game.getLimboPlayers().size());
        game.movePlayerFromTo(player1, game.getPlayers(), game.getLimboPlayers());
        assertEquals(1, game.getLimboPlayers().size());
        assertEquals(player1, game.getLimboPlayers().get(0));
    }

    @Test
    public void testMovePlayerFromTo()
    {
        game.addPlayer(player1);
        assertEquals(1, game.getPlayers().size());
        assertEquals(0, game.getWinners().size());
        game.movePlayerFromTo(player1, game.getPlayers(), game.getWinners());
        assertEquals(0, game.getPlayers().size());
        assertEquals(1, game.getWinners().size());
        assertEquals(player1, game.getWinners().get(0));
    }

    @Test
    public void testMoveAllFromTo()
    {
        assertEquals(0, game.getPlayers().size());
        game.addPlayer(player1);
        game.addPlayer(player2);
        assertEquals(2, game.getPlayers().size());
        assertEquals(0, game.getWinners().size());
        game.moveAllFromTo(game.getPlayers(), game.getWinners());
        assertEquals(0, game.getPlayers().size());
        assertEquals(2, game.getWinners().size());
    }

    @Test
    public void testDealCardTo()
    {
        game.addPlayer(player1);
        assertEquals(0, player1.sumCardValue());
        assertEquals(0, player1.getCards().size());

        game.dealCardTo(player1);
        //Check card is 2 of spades
        assertEquals(2, player1.getCards().get(0).getValue());
        assertEquals("TWO", player1.getCards().get(0).getName());
        assertEquals("Spades", player1.getCards().get(0).getSuit());
        //Check only 1 card dealt
        assertEquals(2, player1.sumCardValue());
        assertEquals(1, player1.getCards().size());
    }

    @Test
    public void testDealNumCardsTo()
    {
        game.addPlayer(player1);
        assertEquals(0, player1.sumCardValue());
        assertEquals(0, player1.getCards().size());

        game.dealNumCardsTo(player1, 2);
        //Check first card is 2 of spades
        assertEquals(2, player1.getCards().get(0).getValue());
        assertEquals("TWO", player1.getCards().get(0).getName());
        assertEquals("Spades", player1.getCards().get(0).getSuit());
        //Check second card is 3 of spades
        assertEquals(3, player1.getCards().get(1).getValue());
        assertEquals("THREE", player1.getCards().get(1).getName());
        assertEquals("Spades", player1.getCards().get(0).getSuit());
        //Check only 2 cards dealt
        assertEquals(5, player1.sumCardValue());
        assertEquals(2, player1.getCards().size());
    }

    @Test
    public void testDealNumCardsToAllPlayers()
    {
        game.addPlayer(player1);
        assertEquals(0, player1.sumCardValue());
        assertEquals(0, player1.getCards().size());

        game.addPlayer(player2);
        assertEquals(0, player2.sumCardValue());
        assertEquals(0, player2.getCards().size());

        game.dealNumCardsToAllPlayers(1);
        //Check player 1's card is 2 of spades
        assertEquals(2, player1.getCards().get(0).getValue());
        assertEquals("TWO", player1.getCards().get(0).getName());
        assertEquals("Spades", player1.getCards().get(0).getSuit());
        //Check player 2's card is 3 of spades
        assertEquals(3, player2.getCards().get(0).getValue());
        assertEquals("THREE", player2.getCards().get(0).getName());
        assertEquals("Spades", player2.getCards().get(0).getSuit());
        //Check only 1 card dealt to player1
        assertEquals(2, player1.sumCardValue());
        assertEquals(1, player1.getCards().size());
        //Check only 1 card dealt to player2
        assertEquals(3, player2.sumCardValue());
        assertEquals(1, player2.getCards().size());
    }
}