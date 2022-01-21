package Model.Tile;

import Controller.Controller;
import Model.Player.character;
import Model.card.DealCard;

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
    //@param g is the game controller
    public void action(Controller g){
        character currentPlayer = g.getCurrentPlayer();

        if(currentPlayer.getCards().size() == 1){
            currentPlayer.setSell(true);
            currentPlayer.sellCard((DealCard) currentPlayer.getCards().get(0));
            this.showMessage("Πούλησες την μόνη κάρτα που έχεις");
        }
        else if(currentPlayer.getCards().size() > 1){
            currentPlayer.setSell(true);
            currentPlayer.seeCards();
        }
        else {
            this.showMessage("Δεν έχεις κάρτες συμφωνίας");
        }
        currentPlayer.setEndTurn(true);
    }

}
