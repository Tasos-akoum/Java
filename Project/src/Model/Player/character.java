package Model.Player;

import Controller.Controller;
import Model.card.Card;
import Model.Dice;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.ArrayList;

//Class character is responsible for the logic and actions of the player
public class character implements PlayerInterface{
    private final int id;
    private int money;
    private int loan;
    private int bills;
    private int position_x;
    private int position_y;
    private final Dice dice;
    private boolean roll;
    private boolean move;
    private boolean endTurn;
    private ArrayList<Card> cards = new ArrayList<Card>();

    //Constructor: Constructs a new character(Player) and sets all the values
    //Postcondition: constructed a character set its values to zero and its id as the given number
    //and sets all the boolean values in order to start the game
    public character(int id){
        this.id = id;
        this.money = 0;
        this.loan = 0;
        this.bills = 0;
        this.position_x = 0;
        this.position_y = 0;
        this.dice = new Dice();
        this.roll = true;
        this.move = true;
        this.endTurn = false;
    }

    //Observer: Returns true if the player can roll the dice, false otherwise
    //Postcondition: Returned if the player can roll the dice
    public boolean canRoll(){
        return roll;
    }

    //Observer: Returns true if the player has finished all his actions and can end his turn otherwise returns false
    //Postcondition: Returns if the player can end his turn
    public boolean canEndTurn(){
        return endTurn;
    }

    //Observer: Returns true if the player can move to a new position otherwise it returns false
    //Postcondition: Returns if the player can move
    public boolean canMove(){
        return move;
    }

    //Observer: Returns true if the player has finished playing otherwise returns false
    //Postcondition: Returns if the player has finished playing
    public boolean hasFinished(){
        return false;
    }


    //Accessor(selector): Returns character dice
    //Postcondition: character dice returned
    public Dice getDice(){
        return this.dice;
    }

    //Transformer(mutative): Character rolls the dice
    //Postcondition: Dice rolled and its value changed
    public void rollDice(){
        this.dice.rollDice();
        playSound("dice.wav");
    }

    //Observer: Returns true if the player has ended his turn otherwise returns false
    //Postcondition: Returns if the player has ended his turn
    public boolean hasEndedTurn(){
        return this.endTurn;
    }

    //Accessor(selector): Lets the character see the cards that they have
    //Postcondition: Character saw the cards
    public void seeCards(){

    }

    //Transformer(mutative): Sells the selected card
    //Postcondition: The selected card is sold giving the character money and is removed from the arraylist
    //@param card is the card that will be sold
    public void sellCard(Card card){
    }

    public void drawCard(Card card, Controller g){
        card.showCard(g);
    }

    public void addCard(Card card){
        cards.add(card);
    }

    public int calculateLoan(int amount){
        if(amount <= 1000){
            return 1000;
        }
        else{
            return ((amount / 1000) + 1) * 1000;
        }
    }

    public void replenishMoney(){
        if(this.money < 0){
            this.addLoan(this.money);
            this.money = 0;
        }
    }

    //Transformer(mutative): Sets the endTurn variable to true
    //Postcondition: The endTurn variable is set to true
    public void Played(){
        endTurn = true;
    }


    //Accessor(selector): Returns the id of the character
    //Postcondition: Returns the id of the character
    public int getId(){
        return this.id;
    }

    //Accessor(selector): Returns the money of the character
    //Postcondition: Returns the money of the character
    public int getMoney(){
        return this.money;
    }

    //Accessor(selector): Returns the loan of the character
    //Postcondition: Returned the loan of the character
    public int getLoan(){
        return this.loan;
    }

    //Accessor(selector): Returns the bills of the character
    //Postcondition: Returned the bills of the character
    public int getBills(){
        return this.bills;
    }

    public ArrayList<Card> getCards(){
        return this.cards;
    }

    //Accessor(selector): Returns the x position of the character
    //Postcondition: Returned the x position of the character
    public int getPositionX(){
        return this.position_x;
    }

    //Accessor(selector): Returns the y position of the character
    //Postcondition: Returned the y position of the character
    public int getPositionY(){
        return this.position_y;
    }

    //Accessor(selector): Returns the score of the character
    //Postcondition: Returned the score of the character
    public int getScore(){
        return money - bills - loan;
    }


    //Transformer(mutative): Sets the money of the character
    //Postcondition: The money value of the character is set to money
    //@param money is the new money value of the character
    public void setMoney(int money){
        this.money = money;
    }

    //Transformer(mutative): Sets the loan value of the character
    //Postcondition: The new loan value of the character is set to loan
    //@param loan is the new loan value of the character
    public void setLoan(int loan){
        this.loan = loan;
    }

    //Transformer(mutative): Sets the bill value of the character
    //Postcondition: The new bills value is changed to bills
    //@param bills the new bill value of the character
    public void setBills(int bills){
        this.bills = bills;
    }

    //Transformer(mutative): Sets the roll value of the character
    //Postcondition: roll is set to b
    //@param roll is the new value of roll
    public void setRoll(boolean roll){
        this.roll = roll;
    }

    //Transformer(mutative): Sets the move boolean value of the player
    //Postcondition: The move boolean value is set to b
    //@param move is the new boolean value of move
    public void setMove(boolean move){
        this.move = move;
    }

    //Transformer(mutative): Sets the position of the character
    //Postcondition: The position value is changed to position
    //@param position is the new position of the character
    public void setPosition(int x, int y){
        this.position_x = x;
        this.position_y = y;
    }

    //Transformer(mutative): Sets the endTurn value of the character
    //Postcondition: The endTurn value is changed to b
    //@param endTurn is the new boolean value of endTurn
    public void setEndTurn(boolean endTurn){
        this.endTurn = endTurn;
    }

    public void addMoney(int money){
        this.money += money;
    }

    public void addLoan(int loan){
        this.loan += loan;
    }

    public void addBills(int bills){
        this.bills += bills;
    }

    public void playSound(String file)
    {
        try
        {
            ClassLoader cldr = this.getClass().getClassLoader();
            URL url= cldr.getResource("Resources/audio/"+file);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip( );
            clip.open(audioInputStream);
            clip.start( );
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace( );
        }
    }

}
