package Model.Tile;

import Controller.Controller;
import Model.Player.character;

import javax.swing.*;

//Class LotteryTile implements the lottery tile
public class LotteryTile extends Tile{
    //Constructor: Constructs a new lottery tile
    //Postcondition: Lottery tile constructed
    public LotteryTile(){
        super("Lottery Tile");
    }

    @Override
    //Transformer(mutative): Makes the 2 players choose a number and if the dice rolls the number the player gets 1000 euros
    //Postcondition: One random player got the money
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();
        character inactivePlayer = g.getInactivePlayer();

        int currentPlayerNumber = 0;
        int inactivePlayerNumber = 0;

        try {
            currentPlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Παίχτη " + currentPlayer.getId() + " διάλεξε έναν αριθμό", 1));
            while (currentPlayerNumber > 6 || currentPlayerNumber < 1) {
                try {
                    currentPlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Παίχτη " + currentPlayer.getId() + " διάλεξε έναν έγκυρο αριθμό (1 - 6)", 1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            inactivePlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Παίχτη " + inactivePlayer.getId() + " διάλεξε έναν διαφορετικό αριθμό", 1));
            while (inactivePlayerNumber > 6 || inactivePlayerNumber < 1 || inactivePlayerNumber == currentPlayerNumber) {
                try {
                    inactivePlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Παίχτη " + inactivePlayer.getId() + " διάλεξε έναν έγκυρο αριθμό " +
                            "(1 - 6, και διαφορετικό απο του πρώτου παίχτη)", 1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            currentPlayer.rollDice();
            while (currentPlayer.getDice().getValue() != currentPlayerNumber && currentPlayer.getDice().getValue() != inactivePlayerNumber)
                currentPlayer.rollDice();

            g.playSound("dice.wav");

            if (currentPlayer.getDice().getValue() == currentPlayerNumber) {
                currentPlayer.addMoney(1000);
                this.showMessage("Ο παίχτης " + currentPlayer.getId() + " πήρε 1000$ απο την τράπεζα");
            } else if (currentPlayer.getDice().getValue() == inactivePlayerNumber) {
                inactivePlayer.addMoney(1000);
                this.showMessage("Ο παίχτης " + inactivePlayer.getId() + " πήρε 1000$ απο την τράπεζα");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        currentPlayer.setEndTurn(true);
    }

}
