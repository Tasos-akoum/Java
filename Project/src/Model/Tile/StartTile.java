package Model.Tile;

import Controller.Controller;

public class StartTile extends Tile{
    public StartTile(){
        super("StartTile");
    }

    public void action(Controller g){
        g.getCurrentPlayer().setEndTurn(true);
    }
}
