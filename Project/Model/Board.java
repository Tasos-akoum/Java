package Model.Player;

import Model.Player.Tile.Tile;

public class Board {
    private Tile[] board;
    private boolean isEmpty;

    public Board(){
        this.board = new Tile[31];
        this.isEmpty = true;
    }


}
