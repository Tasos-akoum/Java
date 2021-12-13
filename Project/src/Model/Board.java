package Model;

import Model.Tile.Tile;
import Model.card.DealCard;
import Model.card.MailCards.MailCard;

//Class Board: The class that makes the board ready for the start of the game
public class Board {
    private Tile[] tiles;
    private DealCard[][] dealCards = new DealCard[20][8];
    private MailCard[][] mailCards = new MailCard[48][4];
    private boolean isEmpty;

    //Constructor: Constructs a new board and initializes everything it needs
    //Postcondition: Board constructed
    public Board(){
        this.tiles = new Tile[31];
        this.isEmpty = true;
    }

    //Transformer(mutative): Initializes the tiles on the board
    //Postcondition: Initialized the tiles on the board
    public void init_tiles(){

    }

    //Transformer(mutative): Initialized the cards on the board
    //Postcondition: Initialized the cards on the board
    public void init_cards(){

    }

    //Accessor(selector): Returns the tile in the i position of the board
    //Postcondition: Returned the selected tile
    //@param i is the index on the board
    public Tile getTile(int i){
        return tiles[i];
    }

    //Transformer(mutative): Sets the tile in the i position of the board
    //Postcondition: The tile in the position i is set to tile
    //@param i is the index on the board
    //@param tile is the new tile that will be set on the board
    public void setTile(int i, Tile tile){
        this.tiles[i] = tile;
    }
}
