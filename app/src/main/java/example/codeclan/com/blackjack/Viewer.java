package example.codeclan.com.blackjack;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 23/06/2017.
 */

public class Viewer
{
    private Scanner sc;

    public Viewer()
    {
        sc = new Scanner(System.in);
    }

    public int getNumPlayers()
    {
        while(true)
        {
            System.out.println("How many players?");
            String input = sc.nextLine();
            try
            {
                return Integer.parseInt(input);

            }
            catch(NumberFormatException nfe)
            {
                System.out.println("Invalid input! Please try again.");
                System.out.println();
            }
        }
    }

    public String getPlayerName(int playerNumber)
    {
        System.out.println(String.format("Please enter player %d's name: ", playerNumber));
        String input = sc.nextLine();
        return input;
    }

    public void showDealerFirstCardScript()
    {
        System.out.println("Dealer's first card is: ");
    }

    public void showCardAtPosition(Player player, int position)
    {
        String name = player.getCards().get(position).getName();
        String suit = player.getCards().get(position).getSuit();
        System.out.println(String.format("%s of %s.", name, suit));
    }

    public char getPlayerChoice()
    {
        System.out.println("(S)tick or (T)wist?");
        String input = sc.nextLine();
        System.out.println();
        return input.toUpperCase().charAt(0);
    }

    public void showPlayerCards(Player player)
    {
        System.out.println(String.format("%s's hand:", player.getName()));
        for(Card card : player.getCards())
        {
            String name = card.getName();
            String suit = card.getSuit();
            System.out.println(String.format("%s of %s.", name, suit));
        }
        System.out.println();
    }

    public void showNaturalWin(Player player)
    {
        System.out.println(String.format("%s scores blackjack!", player.getName()));
        System.out.println();
    }

    public void showBusted(Player player)
    {
        System.out.println(String.format("%s is busted!", player.getName()));
        System.out.println();
    }

    public void showEnd(Game game)
    {
        System.out.println();
        System.out.println("FINAL SCORES");
        System.out.println();
        showPlayerScore(game.getDealer());
        showCategoryPlayerScores(game.getNaturals(), "Scored a natural:");
        showCategoryPlayerScores(game.getWinners(), "Winners are:");
        showCategoryPlayerScores(game.getTiedPlayers(), "Tied with dealer:");
        showCategoryPlayerScores(game.getLosers(), "Losers are:");
    }

    private void showCategoryPlayerScores(ArrayList<Player> players, String message)
    {
        if(players.size() > 0)
        {
            System.out.println(message);
            showScoresForPlayers(players);
        }
    }

    private void showPlayerScore(Player player)
    {
        System.out.println(String.format("%s: %d", player.getName(), player.sumCardValue()));
    }

    public void showScoresForPlayers(ArrayList<Player> players)
    {
        for(Player player : players)
        {
            showPlayerScore(player);
        }
        System.out.println();
    }

    public void showDealerStickScript()
    {
        System.out.println("Dealer sticks.");
        System.out.println();
    }
}
