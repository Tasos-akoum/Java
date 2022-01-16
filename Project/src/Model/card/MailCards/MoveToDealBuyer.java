package Model.card.MailCards;

import Controller.Controller;
import Model.Player.character;
import Model.Tile.BuyerTile;

//Class MoveToDealBuyer implements the move to deal buyer card
public class MoveToDealBuyer extends MailCard{
    //Constructor: Constructs a new MoveToDealBuyer MailCard
    //Postcondition: Constructed card successfully
    public MoveToDealBuyer(){
        super("MoveToDeal", 0);
    }

    //Transformer(mutative): Move the character to the next buyer tile or deal tile
    //Postcondition: Moved character to the next buyer or deal tile
    //@param c is the character
    public void action(Controller g){
        character c = g.getCurrentPlayer();

        for(int i = c.getPositionX(); i < 5; i++){
            for(int j = c.getPositionY(); j < 7; j++){
                if(g.board.getTile(i,j) instanceof BuyerTile){
                    c.setPosition(i,j);
                    g.board.getTile(i,j).action(g);
                }
            }
        }

        c.setEndTurn(true);
    }
}
