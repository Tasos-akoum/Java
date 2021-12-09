package Model.Player;

interface PlayerInterface{
    int rollDice();
    boolean endTurn();
    void seeCards();

    int getId();
    int getMoney();
    int getLoan();
    int getBills();
    int getPosition();
    int getScore();

    boolean canRoll();
    boolean canEndTurn();

    void setLoan(int loan);
    void setBills(int bills);
    void setRoll(boolean b);
    void setPosition(int position);
    void setEndTurn(boolean b);

    void addMoney(int money);
    void addLoan(int loan);
    void addBills(int bills);
}

