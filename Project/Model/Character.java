package Model.Player;

import Model.Player.card.Card;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Character implements PlayerInterface{
    private final int id;
    private int money;
    private int loan;
    private int bills;
    private int position;
    private boolean roll;
    private boolean endTurn;
    private ArrayList<Card> cards = new ArrayList<Card>();

    public Character(int id){
        this.id = id;
        this.money = 0;
        this.loan = 0;
        this.bills = 0;
        this.position = 0;
    }

    public int rollDice(){
        return ThreadLocalRandom.current().nextInt(1,7);
    }

    public boolean endTurn(){
        return false;
    }


    public void seeCards(){

    }

    public int getId(){
        return this.id;
    }

    public int getMoney(){
        return this.money;
    }

    public int getLoan(){
        return this.loan;
    }

    public int getBills(){
        return this.bills;
    }

    public boolean canRoll(){
        return roll;
    }

    public int getPosition(){
        return this.position;
    }

    public boolean canEndTurn(){
        return this.endTurn;
    }

    public int getScore(){
        return money - bills - loan;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public void setLoan(int loan){
        this.loan = loan;
    }

    public void setBills(int bills){
        this.bills = bills;
    }

    public void setRoll(boolean b){
        this.roll = b;
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void setEndTurn(boolean b){
        this.endTurn = b;
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
}
