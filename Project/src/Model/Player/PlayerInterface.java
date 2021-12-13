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
    int getPosition();
    int getScore();

    boolean canRoll();
    boolean canMove();
    boolean canEndTurn();
    boolean hasEndedTurn();
    boolean hasFinished();


    void rollDice();
    void sellCard(Card card);
    void buyCard(Card card);

    void setMoney(int money);
    void setLoan(int loan);
    void setBills(int bills);
    void setMove(boolean move);
    void setRoll(boolean b);
    void setPosition(int position);
    void setEndTurn(boolean b);
    void Played();

}

