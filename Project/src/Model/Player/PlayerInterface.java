package Model.Player;

import Model.card.Card;
import Model.Dice;

//Interface for the character class
interface PlayerInterface{
    void seeCards();

    Dice getDice();
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
    void replenishMoney();

    void drawCard(Card card);
    void rollDice();
    void sellCard(Card card);
    void buyCard(Card card);
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

