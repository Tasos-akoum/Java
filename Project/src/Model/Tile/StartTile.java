package Model.Tile;

import Controller.Controller;

//Class StartTile is only used to identify the start tile and set the players endTurn boolean value to true
public class StartTile extends Tile{
    public StartTile(){
        super("StartTile");
    }

    //Transformer(mutative): Sets the player's endTurn condition to true
    //Postcondition: Current player's endTurn condition set to true
    //@param g is the game controller
    public void action(Controller g){
        g.getCurrentPlayer().setEndTurn(true);
    }
}
