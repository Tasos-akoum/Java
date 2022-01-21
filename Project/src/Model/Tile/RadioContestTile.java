package Model.Tile;

import Controller.Controller;
import Model.Player.character;

//Class RadioContestTile implements the radio contest tile
public class RadioContestTile extends Tile{
    //Constructor: Constructs a new radio contest tile
    //Postcondition: Radio contest tile created successfully
    public RadioContestTile(){
        super("RadioContest Tile");
    }

    @Override
    //Transformer(mutative): Gives 1000 euros to the player who will roll the most value on the dice
    //Postcondition: The player with the most value on the dice gets the 1000 euros
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();
        character inactivePlayer = g.getInactivePlayer();

        currentPlayer.rollDice();
        inactivePlayer.rollDice();

        g.playSound("add_money.wav");
        if(currentPlayer.getDice().getValue() > inactivePlayer.getDice().getValue()) {
            currentPlayer.addMoney(1000);
            this.showMessage("Ο παίχτης" + currentPlayer.getId() + " πήρε 1000$");
        }
        else if(currentPlayer.getDice().getValue() < inactivePlayer.getDice().getValue()) {
            inactivePlayer.addMoney(1000);
            this.showMessage("Ο παίχτης" + inactivePlayer.getId() + " πήρε 1000$");
        }
        else
            this.action(g);

        currentPlayer.setEndTurn(true);
    }


}
