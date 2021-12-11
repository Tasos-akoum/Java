package Model.Player;

import Model.Player.Tile.Tile;

public class Board {
    private Tile[] board;
    private boolean isEmpty;

    //Constructor: Constructs a new board
    public Board(){
        this.board = new Tile[31];
        this.isEmpty = true;
    }

    //Transformer(mutative): Initializes the tiles on the board
    //Postcondition: Initialized the tiles on the board
    public void init_tiles(){

    }

    //Accessor(selector): Returns the tile in the i position of the board
    //Postcondition: Returned the selected tile
    //@param i is the index on the board
    public Tile getTile(int i){
        return board[i];
    }

    //Transformer(mutative): Sets the tile in the i position of the board
    //Postcondition: The tile in the position i is set to tile
    //@param i is the index on the board
    //@param tile is the new tile that will be set on the board
    public void setTile(int i, Tile tile){
        this.board[i] = tile;
    }
}
