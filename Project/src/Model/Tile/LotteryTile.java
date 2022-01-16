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
    //@param c is the current player
    public void action(Controller g){
        int currentPlayerNumber = 0;
        int inactivePlayerNumber = 0;

        try {
            currentPlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Player" + g.getCurrentPlayer().getId() + " please choose a number", 1));
            while (currentPlayerNumber > 6 || currentPlayerNumber < 1) {
                try {
                    currentPlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Player" + g.getCurrentPlayer().getId() + " please choose a valid number (1 - 6)", 1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            inactivePlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Player" + g.getInactivePlayer().getId() + " please choose a different number", 1));
            while (inactivePlayerNumber > 6 || inactivePlayerNumber < 1 || inactivePlayerNumber == currentPlayerNumber) {
                try {
                    inactivePlayerNumber = Integer.parseInt(JOptionPane.showInputDialog("Player" + g.getInactivePlayer().getId() + " please choose a valid number " +
                            "(1 - 6, and not the same as the other player)", 1));
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            g.getCurrentPlayer().rollDice();
            while (g.getCurrentPlayer().getDice().getValue() != currentPlayerNumber && g.getCurrentPlayer().getDice().getValue() != inactivePlayerNumber)
                g.getCurrentPlayer().rollDice();

            g.playSound("dice.wav");

            if (g.getCurrentPlayer().getDice().getValue() == currentPlayerNumber) {
                g.getCurrentPlayer().addMoney(1000);
                this.showMessage("Player" + g.getCurrentPlayer().getId() + " got 1000 from the bank");
            } else if (g.getCurrentPlayer().getDice().getValue() == inactivePlayerNumber) {
                g.getInactivePlayer().addMoney(1000);
                this.showMessage("Player" + g.getInactivePlayer().getId() + " got 1000 from the bank");
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }

        g.getCurrentPlayer().setEndTurn(true);
    }

    public static void main(String[] args) {
        LotteryTile tile = new LotteryTile();
        tile.action(new Controller());
    }
}
