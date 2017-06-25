package example.codeclan.com.blackjack;

import java.util.ArrayList;

/**
 * Created by user on 23/06/2017.
 */

public class Game
{
    private ArrayList<Player> players;
    private Player dealer;
    private Deck deck;

    private ArrayList<Player> bustedPlayers;
    private ArrayList<Player> winningPlayers;
    private ArrayList<Player> tiedPlayers;
    private ArrayList<Player> naturalPlayers;
    private ArrayList<Player> limboPlayers;

    public Game(Deck deck)
    {
        players = new ArrayList<Player>();
        dealer = new Player("Dealer");
        this.deck = deck;

        bustedPlayers = new ArrayList<Player>();
        winningPlayers = new ArrayList<Player>();
        tiedPlayers = new ArrayList<Player>();
        naturalPlayers = new ArrayList<Player>();
        limboPlayers = new ArrayList<Player>();
    }

    public ArrayList<Player> getPlayers()
    {
        return players;
    }

    public void addPlayer(Player player)
    {
        players.add(player);
    }

    public Player getDealer()
    {
        return dealer;
    }

    public ArrayList<Player> getWinners()
    {
        return winningPlayers;
    }

    public ArrayList<Player> getLosers()
    {
        return bustedPlayers;
    }

    public ArrayList<Player> getTiedPlayers()
    {
        return tiedPlayers;
    }

    public ArrayList<Player> getNaturals()
    {
        return naturalPlayers;
    }

    public ArrayList<Player> getLimboPlayers()
    {
        return limboPlayers;
    }

    public void movePlayerFromTo(Player player, ArrayList<Player> origin, ArrayList<Player> destination)
    {
        origin.remove(player);
        destination.add(player);
    }

    public void moveAllFromTo(ArrayList<Player> origin, ArrayList<Player> destination)
    {
        while(origin.size() > 0)
        {
            Player player = origin.get(0);
            movePlayerFromTo(player, origin, destination);
        }
    }

    public void dealCardTo(Player player)
    {
        player.addCard(deck.drawFirstCard());
    }

    public void dealNumCardsTo(Player player, int numCards)
    {
        for(int i = 0; i < numCards; i++)
        {
            dealCardTo(player);
        }
    }

    public void dealNumCardsToAllPlayers(int numCards)
    {
        for(Player player : players)
        {
            dealNumCardsTo(player, numCards);
        }
    }
}
