package Model.Player;

import Controller.Controller;
import Model.card.Card;
import Model.Dice;
import Model.card.DealCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Class character is responsible for the logic and actions of the character
public class character implements PlayerInterface{
    private final int id;
    private int money;
    private int loan;
    private int bills;
    private int position_x;
    private int position_y;
    private int month;
    private final Dice dice;
    private boolean roll;
    private boolean move;
    private boolean sell;
    private boolean endTurn;
    private boolean finished;
    private transient ArrayList<Card> cards = new ArrayList<>();

    //Constructor: Constructs a new character(Player) and sets all the values
    //Postcondition: constructed a character set its values and its id as the given number
    public character(int id){
        this.id = id;
        this.money = 0;
        this.loan = 0;
        this.bills = 0;
        this.position_x = 0;
        this.position_y = 0;
        this.month = 1;
        this.dice = new Dice();
        this.roll = true;
        this.move = true;
        this.sell = false;
        this.finished = false;
        this.endTurn = false;
    }

    //Observer: Returns true if the character can roll the dice, false otherwise
    //Postcondition: Returned if the character can roll the dice
    public boolean canRoll(){
        return roll;
    }

    //Observer: Returns true if the character has finished all his actions and can end his turn otherwise returns false
    //Postcondition: Returns if the character can end his turn
    public boolean canEndTurn(){
        return endTurn;
    }

    //Observer: Returns true if the character can move to a new position otherwise it returns false
    //Postcondition: Returns if the character can move
    public boolean canMove(){
        return move;
    }

    //Observer: Returns true if the character has finished playing otherwise returns false
    //Postcondition: Returns if the character has finished playing
    public boolean hasFinished(){
        return finished;
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
    }

    //Observer: Returns true if the character has ended his turn otherwise returns false
    //Postcondition: Returns if the character has ended his turn
    public boolean hasEndedTurn(){
        return this.endTurn;
    }

    //Observer: Shows the selected card to the character
    //Postcondition: Selected card on screen and character chose action
    private void selectCard(DealCard c){
        JFrame parent = new JFrame();

        JDialog frame = new JDialog(parent, c.getType(), true);
        frame.setSize(650,250);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);
        frame.setResizable(false);

        JLabel image = new JLabel();
        image.setIcon(new ImageIcon(c.getImage()));
        JLabel message = new JLabel("<html>" + c.getMessage() + "<br>Τιμή αγοράς: " + c.getCost() + "<br>Τιμή πώλησης: " + c.getValue() + "</html>");
        JLabel cost_label = new JLabel("Τιμή αγοράς: " + c.getCost());
        JLabel value_label = new JLabel("Τιμή πώλησης: " + c.getValue());

        JButton choice1 = new JButton("Πούλησε");
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sellCard(c);
                frame.dispose();
            }
        });

        JButton choice2 = new JButton("Κλείσε");
        choice2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        frame.add(image,gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 25, 0, 0);
        frame.add(message,gbc);


        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        frame.add(choice1,gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 0);
        frame.add(choice2,gbc);

        frame.setVisible(true);
    }

    //Accessor(selector): Lets the character see the cards that they have
    //Postcondition: Character saw the cards
    public void seeCards(){
        JFrame parent = new JFrame(); //Only because the JDialog needs to attach to something

        JDialog frame = new JDialog(parent, "Your cards", true);
        frame.setSize(800, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(5,4));

        for(Card c : this.cards){
            if(c instanceof DealCard) {
                JButton button = new JButton();
                button.setIcon(new ImageIcon(c.getImage()));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectCard((DealCard) c);
                    }
                });
                frame.add(button);
            }
        }

        frame.setVisible(true);
    }

    //Transformer(mutative): Sells the selected card
    //Postcondition: The selected card is sold giving the character money and is removed from the arraylist
    //@param card is the card that will be sold
    public void sellCard(DealCard card){
        if(this.sell){
            this.money += card.getValue();
            cards.remove(card);
            this.sell = false;
        } else {
            JOptionPane.showMessageDialog(null, "Δεν είσαι σε θέση αγοραστή");
        }
    }

    //
    public void drawCard(Card card, Controller g){
        card.showCard(g);
    }

    //Transformer(mutative): Adds card to the character
    //Postcondition: Card card added to the character arraylist
    //@param card is the card to be added to the character
    public void addCard(Card card){
        cards.add(card);
    }

    //Transformer(mutative): If the character needs to get a loan, this function calculates how much money he needs
    //Postcondition: Loan calculated and returned
    public int calculateLoan(int amount){
        if(amount <= 1000){
            return 1000;
        }
        else{
            return ((amount / 1000) + 1) * 1000;
        }
    }


    //Transformer(mutative): Makes the character pay the intended amount
    //Postcondition: Character paid the amount
    //@param amount is the amount the character paid
    public void pay(int amount){
        if(this.money >= amount){
            this.money -= amount;
        }
        else{
            this.addLoan(this.calculateLoan(amount));
            this.addMoney(this.calculateLoan(amount));
            pay(amount);
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

    //Accessor(selector): Returns the cards arraylist
    //Postcondition: cards arraylist returned
    public ArrayList<Card> getCards(){
        return this.cards;
    }

    //Accessor(selector): Returns the sell value of the character
    //Postcondition: sell returned
    public boolean getSell(){
        return this.sell;
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

    //Accessor(selector): Returns the month value of the character
    //Postcondition: Returned the month value
    public int getMonth(){
        return this.month;
    }

    //Accessor(selector): Returns the score of the character
    //Postcondition: Returned the score of the character
    public int getScore(){
        return money - loan;
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

    /**
     * Transformer(mutative): Sets the bill value of the character
     * Postcondition: The new bills value of the character is set to bills
     * @param bills the new bill value of the character
     */
    public void setBills(int bills){
        this.bills = bills;
    }

    //Transformer(mutative): Sets the roll value of the character
    //Postcondition: roll is set to sell
    //@param roll is the new value of roll
    public void setRoll(boolean roll){
        this.roll = roll;
    }

    //Transformer(mutative): Sets the sell value of the character
    //Postcondition: sell is set to sell
    //@param sell is the new value of sell
    public void setSell(boolean sell){
        this.sell = sell;
    }

    //Transformer(mutative): Sets the move boolean value of the character
    //Postcondition: The move boolean value is set to move
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

    //Transformer(mutative): Sets the month value of the character
    //Postcondition: The month value is change to month
    //@param month is the new month of the character
    public void setMonth(int month){
        this.month = month;
    }

    //Transformer(mutative): Sets the endTurn value of the character
    //Postcondition: The endTurn value is changed to b
    //@param endTurn is the new boolean value of endTurn
    public void setEndTurn(boolean endTurn){
        this.endTurn = endTurn;
    }

    //Transformer(mutative): Sets the finished value of the character
    //Postcondition: The finished value is changed to finished
    //@param finished is the new boolean value of finished
    public void setFinished(boolean finished){
        this.finished = finished;
    }

    //Transformer(mutative): Adds money to the character
    //Postcondition: Added the money value to the character
    //@param money is the value of money added to the character
    public void addMoney(int money){
        this.money += money;
    }

    //Transformer(mutative): Adds loan to the character
    //Postcondition: Added the loan value to the character
    //@param loan is the value of loan added to the character
    public void addLoan(int loan){
        this.loan += loan;
    }

    //Transformer(mutative): Adds bills to the character
    //Postcondition: Added the bills value to the character
    //@param bills is the value of bills added to the character
    public void addBills(int bills){
        this.bills += bills;
    }



}
