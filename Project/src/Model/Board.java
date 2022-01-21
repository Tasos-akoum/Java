package Model;

import Model.Player.character;
import Model.Tile.*;
import Model.card.DealCard;
import Model.card.MailCards.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

//Class Board: The class that makes the board ready for the start of the game
public class Board{
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
    private int jackpot;

    private ArrayList<DealCard> dealCards;
    private ArrayList<DealCard> disposedDealCards;
    private ArrayList<MailCard> mailCards;
    private ArrayList<MailCard> disposedMailCards;

    //Constructor: Constructs a new board and initializes everything it needs
    //Postcondition: Board constructed
    public Board(){
        this.tiles = new Tile[5][7];
        this.mailCards = new ArrayList<>();
        this.disposedMailCards = new ArrayList<>();
        this.dealCards = new ArrayList<>();
        this.disposedDealCards = new ArrayList<>();
        this.jackpot = 0;
        this.day_index = ThreadLocalRandom.current().nextInt(0,7);
        this.init_tiles();
        this.shuffleTiles();
        this.assignDays();
        this.init_cards();
        this.shuffleDealCards();
        this.shuffleMailCards();
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

    //Transformer(mutative): Assigns a day to each tile on the board
    //PostCondition: All tiles have dates
    public void assignDays(){
        int index = 0;

        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                index = (i * 7) + j;

                if(tiles[i][j] != null && !(tiles[i][j] instanceof StartTile)) {
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

    //Transformer(mutative): Swaps 2 tiles with each other
    //Postcondition: Tiles swapped
    public void tileSwap(int i, int j, int x, int y){
        Tile temp = tiles[i][j];
        tiles[i][j] = tiles[x][y];
        tiles[x][y] = temp;
    }


    //Transformer(mutative):Shuffles the tiles
    //Postcondition: Tiles shuffled
    public void shuffleTiles(){
        int x,y;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 7; j++){
                x = ThreadLocalRandom.current().nextInt(0, 4);
                y = ThreadLocalRandom.current().nextInt(0, 7);

                //We need to check if the tile is null or the instance of StartTile or PaydayTile because we do not
                //want to move those
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
                    //In the given file the advertisement cards have a weird typo and I had to copy paste the word from the .txt for it to work
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

        try{
            BufferedReader br = new BufferedReader(new FileReader(cldr.getResource("Resources/cardText/dealCards.csv").getFile()));

            while((line = br.readLine()) != null){
                String[] info = line.split(splitBy);
                String type = info[1];
                String message = info[2];
                String cost = info[3];
                String value = info[4];
                String image = info[5];

                dealCards.add(new DealCard(Integer.parseInt(cost), Integer.parseInt(value)));
                dealCards.get(dealCards.size() - 1).setMessage(message);
                dealCards.get(dealCards.size() - 1).setImage("Resources/cards/" + image);
            }
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    //Transformer(mutative): Shuffle the Deal cards on the board
    //Postcondition: Cards shuffled
    public void shuffleDealCards(){
        Collections.shuffle(dealCards);
    }

    //Transformer(mutative): Shuffle the Mail cards on the board
    //Postcondition: Cards shuffled
    public void shuffleMailCards(){
        Collections.shuffle(mailCards);
    }

    //Transformer(mutative): When the Deal cards' arraylist is empty, add all the cards back again and shuffle them
    //Postcondition: Deal cards replenished
    public void replenishDealCards(){
        dealCards.addAll(disposedDealCards);
        shuffleDealCards();
    }

    //Transformer(mutative): When the Mail cards' arraylist is empty, add all the cards back again and shuffle them
    //Postcondition: Mail cards replenished
    public void replenishMailCards(){
        mailCards.addAll(disposedMailCards);
        shuffleMailCards();
    }

    //Accessor(selector): Returns the mailCards arraylist
    //Postcondition: mailCards arraylist returned
    public ArrayList<MailCard> getMailCards(){
        return this.mailCards;
    }

    //Accessor(selector): Returns the disposedMailCards arraylist
    //Postcondition: disposedMailCards arraylist returned
    public ArrayList<MailCard> getDisposedMailCards(){
        return this.disposedMailCards;
    }

    //Accessor(selector): Returns the dealCards arraylist
    //Postcondition: dealCards arraylist returned
    public ArrayList<DealCard> getDealCards(){
        return this.dealCards;
    }

    //Accessor(selector): Returns the disposedDealCards arraylist
    //Postcondition: disposedDealCards arraylist returned
    public ArrayList<DealCard> getDisposedDealCards(){
        return this.disposedDealCards;
    }

    //Accessor(selector): Returns the jackpot's value
    //Postcondition: Jackpot returned
    public int getJackpot(){
        return this.jackpot;
    }

    //Transformer(mutative): Adds the amount value to jackpot
    //Postcondition: amount added to jackpot
    //@param amount is the amount added to the jackpot
    public void addToJackpot(int amount){
        this.jackpot += amount;
    }

    //Transformer(mutative): Gives the jackpot to the player c
    //Postcondition: Jackpot added to player c
    //@param c is the selected player
    public void giveJackpot(character c){
        c.addMoney(jackpot);
        JOptionPane.showMessageDialog(null, "Έφερες εξάρα, πήρες " + jackpot + "$ από το jackpot", "Jackpot", JOptionPane.INFORMATION_MESSAGE);
        jackpot = 0;
    }

    //Accessor(selector): Returns the tile in the i,j position of the board
    //Postcondition: Returned the selected tile
    //@param i,j is the index on the board
    public Tile getTile(int i, int j){
        return tiles[i][j];
    }

    //Transformer(mutative): Sets the tile in the i,j position of the board
    //Postcondition: The tile in the position i,j is set to tile
    //@param i,j is the index on the board
    //@param tile is the new tile that will be set on the board
    public void setTile(int i,int j, Tile tile){
        this.tiles[i][j] = tile;
    }

}
