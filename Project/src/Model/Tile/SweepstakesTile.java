package Model.Tile;

import Controller.Controller;
import Model.Player.character;

//Class SweepstakesTile implements the sweepstakes tile
public class SweepstakesTile extends Tile{
    //Constructor: Constructs a new sweepstakes tile
    //Postcondition: Tile constructed successfully
    public SweepstakesTile(){
        super("Sweepstakes Tile");
    }

    @Override
    //Transformer(mutative): Rolls the dice and wins 1000 * the value of the dice euros
    //Postcondition: Player wins 1000 * dice.getValue() euros
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();

        currentPlayer.rollDice();
        currentPlayer.addMoney(1000 * currentPlayer.getDice().getValue());
        g.playSound("add_money.wav");
        this.showMessage("Ο παίχτης" + currentPlayer.getId() + " πήρε " + 1000 * currentPlayer.getDice().getValue() + "$");

        currentPlayer.setEndTurn(true);
    }
}
