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
    //@param g is the game controller
    public void action(Controller g){
        character c = g.getCurrentPlayer();

        breakpoint:
        for(int i = c.getPositionX(); i < 5; i++){
            for(int j = 0; j < 7; j++){
                if(g.board.getTile(i,j) instanceof BuyerTile && (i * 7) + j > (c.getPositionX() * 7) + c.getPositionY()){ //(i*7) + j is the position on the map if each tile is a number
                    g.playSound("move.wav");
                    c.setPosition(i,j);
                    g.board.getTile(i,j).action(g);
                    break breakpoint; //when we find the first buyer tile, get out of the loops and set endTurn to true
                }
            }
        }


        c.setEndTurn(true);
    }

}
