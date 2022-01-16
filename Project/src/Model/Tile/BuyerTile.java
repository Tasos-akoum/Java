package Model.Tile;

import Controller.Controller;
import Model.card.DealCard;

import javax.swing.*;

//Class BuyerTile implements the buyer tile
public class BuyerTile extends Tile{
    //Constructor: Constructs a new Buyer Tile
    //Postcondition: Buyer tile constructed
    public BuyerTile(){
        super("Buyer Tile");
    }

    @Override
    //Transformer(mutative): The player can sell a Deal Card, if he has more than one he needs to be given a choice
    //Postcondition: Selected card sold
    //@param c is the current player
    public void action(Controller g){
        if(g.getCurrentPlayer().getCards().size() == 1){
            if(g.getCurrentPlayer().getCards().get(0) instanceof DealCard)
                g.getCurrentPlayer().setSell(true);
                g.getCurrentPlayer().sellCard((DealCard) g.getCurrentPlayer().getCards().get(0));
        }
        else if(g.getCurrentPlayer().getCards().size() > 1){
            g.getCurrentPlayer().setSell(true);
            g.getCurrentPlayer().seeCards();
        }
        else {
            JOptionPane.showMessageDialog(null, "Δεν έχεις κάρτες συμφωνίας");
        }
        g.getCurrentPlayer().setEndTurn(true);
    }

}
