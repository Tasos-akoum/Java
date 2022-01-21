package Model.Player;

import Controller.Controller;
import Model.card.Card;
import Model.Dice;
import Model.card.DealCard;

import java.util.ArrayList;

//Interface for the character class
interface PlayerInterface{
    void seeCards();

    Dice getDice();
    int getId();
    int getMoney();
    int getLoan();
    int getBills();
    int getMonth();
    int getPositionX();
    int getPositionY();
    int getScore();
    ArrayList<Card> getCards();

    boolean canRoll();
    boolean canMove();
    boolean canEndTurn();
    boolean hasFinished();


    void selectCard(DealCard c);
    void drawCard(Card card, Controller g);
    void sellCard(DealCard card);
    void addCard(Card card);

    void rollDice();
    void setMoney(int money);
    void setLoan(int loan);
    void setBills(int bills);
    void setMonth(int month);
    void setMove(boolean move);
    void setRoll(boolean b);
    void setSell(boolean sell);
    void setFinished(boolean finished);
    void setPosition(int x, int y);
    void setEndTurn(boolean endTurn);
    int calculateLoan(int amount);
    void pay(int amount);

    void addMoney(int money);
    void addLoan(int loan);
    void addBills(int bills);

}

