package Model.Tile;

import Controller.Controller;
import Model.Player.character;

//Class YardSaleTile implements the yard sale tile
public class YardSaleTile extends Tile{
    //Constructor: Constructs a new yard sale tile
    //Postcondition: Yard sale tile constructed successfully
    public YardSaleTile(){
        super("YardSale Tile");
    }

    @Override
    //Transformer(mutative): Player rolls dice and pays 100 * dice value
    //Postcondition: Player paid the value
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();

        currentPlayer.rollDice();
        currentPlayer.pay(currentPlayer.getDice().getValue() * 100);
        g.playSound("doom.wav");
        this.showMessage("Ο παίχτης" + currentPlayer.getId() + " πλήρωσε " + currentPlayer.getDice().getValue() * 100 + "$");

        currentPlayer.setEndTurn(true);

    }
}
