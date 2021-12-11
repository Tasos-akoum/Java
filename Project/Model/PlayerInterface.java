package Model.Player;

import Model.Player.card.Card;

interface PlayerInterface{
    void seeCards();

    int getId();
    int getMoney();
    int getLoan();
    int getBills();
    int getPosition();
    int getScore();

    boolean canRoll();
    boolean canEndTurn();
    boolean hasEndedTurn();
    boolean hasFinished();


    void sellCard(Card card);
    void buyCard(Card card);

    void setMoney(int money);
    void setLoan(int loan);
    void setBills(int bills);
    void setRoll(boolean b);
    void setPosition(int position);
    void setEndTurn(boolean b);
    void Played();

    void addMoney(int money);
    void addLoan(int loan);
    void addBills(int bills);
}

