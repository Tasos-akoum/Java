package Model;

import Model.Tile.*;
import Model.card.DealCard;
import Model.card.MailCards.MailCard;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//Class Board: The class that makes the board ready for the start of the game
public class Board {
    private Tile[] tiles;
    private String[] days = {
            "Sunday",
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday"
    };

    private int day_index;

    private DealCard[][] dealCards = new DealCard[20][8];
    private MailCard[][] mailCards = new MailCard[48][4];
    private boolean isEmpty;

    //Constructor: Constructs a new board and initializes everything it needs
    //Postcondition: Board constructed
    public Board(){
        this.tiles = new Tile[32];
        this.isEmpty = true;
        this.day_index = ThreadLocalRandom.current().nextInt(0,7);
        this.init_tiles();
        this.shuffleTiles();
        this.assignDays(31);
    }

    //Transformer(mutative): Initializes the tiles on the board
    //Postcondition: Initialized the tiles on the board
    private void init_tiles(){
        tiles[0] = new StartTile();

        for(int i = 1; i < 31; i++){
            if(i < 5){
                tiles[i] = new MessageTile(1);
            }
            else if(i < 9){
                tiles[i] = new MessageTile(2);
            }
            else if(i < 14){
                tiles[i] = new DealTile();
            }
            else if(i < 16){
                tiles[i] = new SweepstakesTile();
            }
            else if(i < 19){
                tiles[i] = new LotteryTile();
            }
            else if(i < 21){
                tiles[i] = new RadioContestTile();
            }
            else if(i < 27){
                tiles[i] = new BuyerTile();
            }
            else if(i < 29){
                tiles[i] = new FamilyCasinoNightTile();
            }
            else if(i < 31){
                tiles[i] = new YardSaleTile();
            }
        }

        tiles[31] = new PaydayTile();
    }

    public void assignDays(int behindPlayerPosition){
        int i;

        if(this.isEmpty){
            for(i = 1; i < 32; i++){
                tiles[i].setDay(days[day_index] + " " + i);

                if(day_index == 6){
                    day_index = 0;
                } else {
                    day_index++;
                }
            }

            this.isEmpty = false;
        } else {
            for(i = 1; i < behindPlayerPosition; i++){
                tiles[i].setDay(days[day_index] + " " + i);

                if(day_index == 6){
                    day_index = 0;
                } else {
                    day_index++;
                }
            }
        }
    }

    public void tileSwap(int i, int j){
        Tile temp = tiles[i];
        tiles[i] = tiles[j];
        tiles[j] = temp;
    }

    public void printTiles(){
        for(int i = 0; i < 32; i++){
//            System.out.println(tiles[i].getClass().getSimpleName());
            System.out.println(tiles[i].getDay());
        }
    }

    public void shuffleTiles(){
        for(int i = 1; i < 31; i++){
            int j = ThreadLocalRandom.current().nextInt(1,31);

            tileSwap(i, j);
        }
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

    public static void main(String[] args) {
        Board board = new Board();

        board.printTiles();
    }
}
