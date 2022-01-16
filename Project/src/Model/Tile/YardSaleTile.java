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
    //@param c is the current player
    public void action(Controller g){
        g.getCurrentPlayer().rollDice();
        g.getCurrentPlayer().pay(g.getCurrentPlayer().getDice().getValue() * 100);
        g.playSound("doom.wav");
        this.showMessage("Ο παίχτης" + g.getCurrentPlayer().getId() + " πλήρωσε " + g.getCurrentPlayer().getDice().getValue() * 100 + "$");

        g.getCurrentPlayer().setEndTurn(true);

    }
}
