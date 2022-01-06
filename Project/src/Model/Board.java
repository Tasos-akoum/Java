package Model;

import Model.Tile.*;
import Model.card.DealCard;
import Model.card.MailCards.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

//Class Board: The class that makes the board ready for the start of the game
public class Board {
    private Tile[][] tiles;
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

    private ArrayList<DealCard> dealCards;
    private ArrayList<MailCard> mailCards;
    private boolean isEmpty;

    //Constructor: Constructs a new board and initializes everything it needs
    //Postcondition: Board constructed
    public Board(){
        this.tiles = new Tile[5][7];
        this.mailCards = new ArrayList<>();
        this.dealCards = new ArrayList<>();
        this.isEmpty = true;
        this.day_index = ThreadLocalRandom.current().nextInt(0,7);
        this.init_tiles();
        this.shuffleTiles();
        this.assignDays(31);
        this.init_cards();
    }

    //Transformer(mutative): Initializes the tiles on the board
    //Postcondition: Initialized the tiles on the board
    private void init_tiles(){
        int index = 0;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 7; j++) {
                index = (i * 7) + j;

                if (index == 0) {
                    tiles[i][j] = new StartTile();
                }
                else if (index < 5) {
                    tiles[i][j] = new MessageTile(1);
                } else if (index < 9) {
                    tiles[i][j] = new MessageTile(2);
                } else if (index < 14) {
                    tiles[i][j] = new DealTile();
                } else if (index < 16) {
                    tiles[i][j] = new SweepstakesTile();
                } else if (index < 19) {
                    tiles[i][j] = new LotteryTile();
                } else if (index < 21) {
                    tiles[i][j] = new RadioContestTile();
                } else if (index < 27) {
                    tiles[i][j] = new BuyerTile();
                } else if (index < 29) {
                    tiles[i][j] = new FamilyCasinoNightTile();
                } else if (index < 31) {
                    tiles[i][j] = new YardSaleTile();
                }
            }
        }

        tiles[4][3] = new PaydayTile();
    }

    public void assignDays(int behindPlayerPosition){
        int index = 0;

        if(this.isEmpty){
            for(int i = 0; i < 5; i++) {
                for (int j = 1; j < 7; j++) {
                    index = (i * 7) + j;

                    if(tiles[i][j] != null)
                        tiles[i][j].setDay(days[day_index] + " " + index);

                    if (day_index == 6) {
                        day_index = 0;
                    } else {
                        day_index++;
                    }
                }
            }

            this.isEmpty = false;
        } else {
            for(int i = 0; i < behindPlayerPosition; i++) {
                for (int j = 1; j < 7; j++) {
                    index = (i * 7) + j;

                    if(tiles[i][j] != null)
                        tiles[i][j].setDay(days[day_index] + " " + index);

                    if (day_index == 6) {
                        day_index = 0;
                    } else {
                        day_index++;
                    }
                }
            }
        }
    }

    public void tileSwap(int i, int j, int x, int y){
        Tile temp = tiles[i][j];
        tiles[i][j] = tiles[x][y];
        tiles[x][y] = temp;
    }


    public void shuffleTiles(){
        int x,y;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 7; j++){
                x = ThreadLocalRandom.current().nextInt(0, 4);
                y = ThreadLocalRandom.current().nextInt(0, 7);

                if(tiles[i][j] != null && !(tiles[i][j] instanceof StartTile || tiles[i][j] instanceof PaydayTile)
                                       && !(tiles[x][y] instanceof StartTile || tiles[x][y] instanceof PaydayTile)){

                    tileSwap(i,j,x,y);
                }

            }
        }
    }

    //Transformer(mutative): Initialized the cards on the board
    //Postcondition: Initialized the cards on the board
    public void init_cards(){
        ClassLoader cldr = this.getClass().getClassLoader();
        String line = "";
        String splitBy = ",";

        try{
            BufferedReader br = new BufferedReader(new FileReader(cldr.getResource("Resources/cardText/mailCards.csv").getFile()));

            while((line = br.readLine()) != null){
                String[] info = line.split(splitBy);
                String type = info[1];
                String message = info[2];
                String choice = info[3];
                String euro = info[4];
                String image = info[5];

                switch (type){
                    case "Αdvertisement":
                        mailCards.add(new AdvertisementCard(Integer.parseInt(euro)));
                        break;
                    case "Bill":
                        mailCards.add(new BillCard(Integer.parseInt(euro)));
                        break;
                    case "Charity":
                        mailCards.add(new Charity(Integer.parseInt(euro)));
                        break;
                    case "MadMoney":
                        mailCards.add(new MadMoneyCard(Integer.parseInt(euro)));
                        break;
                    case "MoveToDealBuyer":
                        mailCards.add(new MoveToDealBuyer());
                        break;
                    case "PayTheNeighbor":
                        mailCards.add(new PayTheNeighborCard(Integer.parseInt(euro)));
                        break;
                    default:
                        break;
                }
                mailCards.get(mailCards.size() - 1).setMessage(message);
                mailCards.get(mailCards.size() - 1).setChoice(choice);
                mailCards.get(mailCards.size() - 1).setImage("Resources/cards/" + image);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<MailCard> getMailCards(){
        return this.mailCards;
    }

    //Accessor(selector): Returns the tile in the i position of the board
    //Postcondition: Returned the selected tile
    //@param i is the index on the board
    public Tile getTile(int i, int j){
        return tiles[i][j];
    }

    //Transformer(mutative): Sets the tile in the i position of the board
    //Postcondition: The tile in the position i is set to tile
    //@param i is the index on the board
    //@param tile is the new tile that will be set on the board
    public void setTile(int i,int j, Tile tile){
        this.tiles[i][j] = tile;
    }

    public static void main(String[] args) {
        Board board = new Board();

        board.getMailCards().get(45).showCard();
        System.out.println(4);
    }
}
