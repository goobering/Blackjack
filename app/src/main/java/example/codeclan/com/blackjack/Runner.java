package example.codeclan.com.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 23/06/2017.
 */

public class Runner
{
    public static void main(String[] args)
    {
        Viewer viewer = new Viewer();

        //Set up deck
        Deck deck = new Deck();
        deck.makeDeck();
        deck.shuffle();

        //Create game with deck
        Game game = new Game(deck);

        //Get the user to input how many players
        int numPlayers = viewer.getNumPlayers();

        //Create [x] players and add to game
        for(int i = 0; i < numPlayers; i++)
        {
            Player player = new Player(viewer.getPlayerName(i+1));
            game.addPlayer(player);
        }

        //Deal 2 cards to dealer
        game.dealNumCardsTo(game.getDealer(), 2);

        //Deal 2 cards to each player
        game.dealNumCardsToAllPlayers(2);

        //Show the dealer's first card
        viewer.showDealerFirstCardScript();
        viewer.showCardAtPosition(game.getDealer(), 0);

        //Loop around every player once
        while(game.getPlayers().size() > 0)
        {
            Player player = game.getPlayers().get(0);

            //Show player their cards
            viewer.showPlayerCards(player);

            //Check for natural
            if(player.hasNatural())
            {
                viewer.showNaturalWin(player);
                //Shunt player into relevant category
                game.movePlayerFromTo(player, game.getPlayers(), game.getNaturals());

                //Move on to next player
                continue;
            }

            //Stick user in infinite loop till they're done twisting or bust.
            char playerChoice = 'Z';
            while(playerChoice != 'S')
            {
                //Player chooses stick or twist
                playerChoice = viewer.getPlayerChoice();

                //If T(wist) then deal another card and loop
                if(playerChoice == 'T')
                {
                    game.dealNumCardsTo(player, 1);
                    viewer.showPlayerCards(player);

                    //If hand is over 21, try swapping a high ace for low until not bust or no more aces
                    player.convertBustedAces();
                }

                //If player's bust, shunt them into Losers category and leave input loop
                if(player.isBusted())
                {
                    viewer.showBusted(player);
                    game.movePlayerFromTo(player, game.getPlayers(), game.getLosers());
                    break;
                }
            }
            //If done twisting and not bust, move into Limbo category
            if(!player.isBusted())
            {
                game.movePlayerFromTo(player, game.getPlayers(), game.getLimboPlayers());
            }
        }

        //Show dealer's second card
        viewer.showPlayerCards(game.getDealer());

        //If dealer has natural, everyone who doesn't loses:
        if(game.getDealer().hasNatural())
        {
            viewer.showNaturalWin(game.getDealer());

            //Move non-natural winners and limbo players to losers category:
            game.moveAllFromTo(game.getWinners(), game.getLosers());
            game.moveAllFromTo(game.getLimboPlayers(), game.getLosers());

            viewer.showEnd(game);
            return;
        }

        //Dealer draws till they hit 17 or go bust
        while(game.getDealer().sumCardValue() < 17)
        {
            game.dealCardTo(game.getDealer());
            viewer.showPlayerCards(game.getDealer());

            //If hand is over 21, try swapping a high ace for low until no more aces
            game.getDealer().convertBustedAces();
        }

        //Set dealer's score to 0 if busted
        if(game.getDealer().isBusted())
        {
            viewer.showBusted(game.getDealer());
            game.getDealer().dropCards();
        }
        //If not busted, dealer sticks
        else
        {
            viewer.showDealerStickScript();
        }

        //Loop around players in limbo (no naturals, not bust)
        while(game.getLimboPlayers().size() > 0)
        {
            Player player = game.getLimboPlayers().get(0);
            //If player score beats dealer score move player into winners category
            if(player.sumCardValue() > game.getDealer().sumCardValue())
            {
                game.movePlayerFromTo(player, game.getLimboPlayers(), game.getWinners());
            }
            //If player score loses to dealer score move player into losers category
            else if(player.sumCardValue() < game.getDealer().sumCardValue())
            {
                game.movePlayerFromTo(player, game.getLimboPlayers(), game.getLosers());
            }
            //If tied with dealer score move player into tied category
            else
            {
                game.movePlayerFromTo(player, game.getLimboPlayers(), game.getTiedPlayers());
            }
        }

        //Display final scores
        viewer.showEnd(game);
    }
}
