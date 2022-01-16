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
    void rollDice();
    int getId();
    int getMoney();
    int getLoan();
    int getBills();
    int getPositionX();
    int getPositionY();
    int getScore();

    boolean canRoll();
    boolean canMove();
    boolean canEndTurn();
    boolean hasEndedTurn();
    boolean hasFinished();

    int calculateLoan(int amount);
    void pay(int amount);

    ArrayList<Card> getCards();
    void drawCard(Card card, Controller g);
    void sellCard(DealCard card);
    void addCard(Card card);

    void setMoney(int money);
    void setLoan(int loan);
    void setBills(int bills);
    void setMove(boolean move);
    void setRoll(boolean b);
    void setPosition(int x, int y);
    void setEndTurn(boolean b);
    void Played();

    void addMoney(int money);
    void addLoan(int loan);
    void addBills(int bills);

}

