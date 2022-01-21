package Model.Tile;

import Controller.Controller;
import Model.Board;

//Class DealTile implements the deal tiles
public class DealTile extends Tile{
    //Constructor: Constructs a new deal tile
    //Postcondition: Deal tile constructed
    public DealTile(){
        super("Deal Tile");
    }

    @Override
    //Transformer(mutative): Draws a deal card
    //Postcondition: Deal card drawn and is accepted or ignored
    //@param g is the game controller
    public void action(Controller g) {
        Board b = g.board;

        g.playSound("draw.wav");
        g.getCurrentPlayer().drawCard(b.getDealCards().get(b.getDealCards().size() - 1), g);
        b.getDisposedDealCards().add(b.getDealCards().get(b.getDealCards().size() - 1));
        b.getDealCards().remove(b.getDealCards().size() - 1);
        if(b.getDealCards().size() == 0){
            b.replenishDealCards();
        }

        g.getCurrentPlayer().setEndTurn(true);
    }
}
