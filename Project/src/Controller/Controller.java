package Controller;

import Model.Board;
import Model.Player.character;
import Model.Turn;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;

//Class Controller is the class that connects all the assets of the game and controls the game flow
public class Controller{
    private boolean hasEnded;
    private final character c1, c2;
    public Board board;
    private Turn turn = new Turn();
    private URL imageURL;
    private ClassLoader cldr = this.getClass().getClassLoader();
    private Image image;

    //Constructor: Constructs a game controller
    //Postcondition: Constructed a controller and sets the game up to start
    public Controller(){
        board = new Board();

        c1 = new character(1);
        c2 = new character(2);


        c1.setPosition(0, 0);
        c2.setPosition(0, 0);

        c1.setMoney(3500);
        c2.setMoney(3500);

        if(ThreadLocalRandom.current().nextInt(1,3) == 1) {
            turn.setCurrentPlayer(c1);
            turn.setInactivePlayer(c2);
        }
        else {
            turn.setCurrentPlayer(c2);
            turn.setInactivePlayer(c1);
        }

        this.hasEnded = false;
    }



    //Observer: Returns true if the game has started, otherwise returns false
    //Postcondition: Return if the game has started
    public boolean hasEnded(){
        return this.hasEnded;
    }

    //Transformer(mutative): Sets the value of the hasStarted variable
    //Postcondition: Sets the hasStarted value as b
    //@param b is the new value of hasStarted
    public void setHasEnded(boolean b){
        this.hasEnded = b;
    }

    //Accessor(selector): Returns the player with the given id
    //Postcondition: Player returned
    //@param id is the selected player's id
    public character getPlayer(int id){
        if(id == 1)
            return c1;
        else if (id == 2)
            return c2;
        else
            System.err.println("Invalid ID");

        return null;
    }

    //Accessor(selector): Returns the current player
    //Postcondition: Current player returned
    public character getCurrentPlayer(){
        return this.turn.getCurrentPlayer();
    }

    //Accessor(selector): Returns the inactive player
    //Postcondition: Inactive player returned
    public character getInactivePlayer(){
        return this.turn.getInactivePlayer();
    }

    //Transformer(mutative): Changes player position on the board
    //Postcondition: Player position changed
    //@param c is the character who will have his position changed
    public void changePlayerPosition(character c){
        if(c.canMove()){
            if(c.getPositionX() == 4){
                if(c.getPositionY() + c.getDice().getValue() > 3)
                    c.setPosition(4,3);
                else
                    c.setPosition(c.getPositionX(), c.getPositionY() + c.getDice().getValue());

            } else {
                if(c.getPositionY() + c.getDice().getValue() > 6) {
                    if(c.getPositionX() + 1 == 4 && c.getPositionY() + c.getDice().getValue() - 7 > 3) {
                        c.setPosition(4,3);
                    } else {
                        c.setPosition(c.getPositionX() + 1, c.getPositionY() + c.getDice().getValue() - 7);
                    }

                }
                else
                    c.setPosition(c.getPositionX(), c.getPositionY() + c.getDice().getValue());
            }

            this.playSound("move.wav");
            c.setMove(false);
            checkForDayEvent(c);
            board.getTile(c.getPositionX(), c.getPositionY()).action(this);
        }
    }

    //Observer: Checks if the day has an event and play that event
    //Postcondition: Played event if the day has one, else do nothing
    public void checkForDayEvent(character c){
        String str = board.getTile(c.getPositionX(), c.getPositionY()).getDay();
        String[] day = str.split(" ", 2);

         switch (day[0]){
             case "Sunday":
                 SundayFootball(c);
                 break;
             case "Thursday":
                 CryptoThursday(c);
                 break;
        }
    }

    //Transformer(mutative): Implement the Sunday Football event
    //Postcondition: Sunday football event complete
    private void SundayFootball(character c){
        JFrame parent = new JFrame();

        JDialog frame = new JDialog(parent, "Sunday Football Day", true);
        frame.setSize(700,250);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);
        frame.setResizable(false);

        JLabel image_label = new JLabel();
        imageURL = cldr.getResource("Resources/basic/Barcelona_Real.jpg");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(250,150,Image.SCALE_SMOOTH);
        image_label.setIcon(new ImageIcon(image));
        JLabel message = new JLabel("Στοιχιμάτισε 500 ευρώ για το ντερμπι Μπαρτσελόνα-Ρεάλ");

        JButton choice1 = new JButton("Νίκη Μπαρτσελόνα");
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.rollDice();
                switch (c.getDice().getValue()){
                    case 1:
                    case 2:
                        playSound("add_money.wav");
                        JOptionPane.showMessageDialog(null,"Η Μπαρτσελόνα νίκησε, κέρδισες 1000$");
                        c.addMoney(1000);
                        break;
                    default:
                        playSound("doom.wav");
                        JOptionPane.showMessageDialog(null,"H Μπαρτσελόνα έχασε, έχασες 500$");
                        c.addMoney(-500);
                        break;
                }
                frame.dispose();
            }
        });

        JButton choice2 = new JButton("Ισοπαλία");
        choice2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.rollDice();
                switch (c.getDice().getValue()){
                    case 3:
                    case 4:
                        playSound("add_money.wav");
                        JOptionPane.showMessageDialog(null,"To σκόρ πήγε ισοπαλία, κέρδισες 1000$");
                        c.addMoney(1000);
                        break;
                    default:
                        playSound("doom.wav");
                        JOptionPane.showMessageDialog(null,"Το σκόρ δεν πήγε ισοπαλία, έχασες 500$");
                        c.addMoney(-500);
                        break;
                }
                frame.dispose();
            }
        });

        JButton choice3 = new JButton("Νίκη Ρεάλ");
        choice3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.rollDice();
                switch (c.getDice().getValue()){
                    case 5:
                    case 6:
                        playSound("add_money.wav");
                        JOptionPane.showMessageDialog(null,"H Ρεάλ νίκησε, κέρδισες 1000$");
                        c.addMoney(1000);
                        break;
                    default:
                        playSound("doom.wav");
                        JOptionPane.showMessageDialog(null,"H Ρεάλ έχασε, έχασες 500$");
                        c.addMoney(-500);
                        break;
                }
                frame.dispose();
            }
        });

        JButton choice4 = new JButton("Αγνόησε");
        choice4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        gbc.weighty = 0;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 0);
        frame.add(image_label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 25, 0, 0);
        frame.add(message, gbc);


        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weightx = 0;
        frame.add(choice1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 0);
        frame.add(choice2, gbc);

        gbc.gridx = 2;
        frame.add(choice3, gbc);

        gbc.gridx = 3;
        frame.add(choice4, gbc);

        frame.setVisible(true);
    }

    //Transformer(mutative): Implement the Crypto Thursday event
    //Postcondition: Crypto Thursday event complete
    private void CryptoThursday(character c){
        JFrame parent = new JFrame();

        JDialog frame = new JDialog(parent, "Crypto Thursday", true);
        frame.setSize(550,250);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        frame.setLayout(layout);
        frame.setResizable(false);

        JLabel image_label = new JLabel();
        imageURL = cldr.getResource("Resources/basic/crypto.jpeg");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(250,150,Image.SCALE_SMOOTH);
        image_label.setIcon(new ImageIcon(image));
        JLabel message = new JLabel("Πόνταρε σε κρυπτονομίσματα 300$");

        JButton choice1 = new JButton("Πόνταρε στο κρυπτονόμισμα");
        choice1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                c.rollDice();
                switch (c.getDice().getValue()){
                    case 1:
                    case 2:
                        playSound("doom.wav");
                        JOptionPane.showMessageDialog(null,"Τo κρυπτονόμισμα πάτωσε, χάνεις τα λεφτά σου");
                        c.addMoney(-300);
                        break;
                    case 3:
                    case 4:
                        playSound("win.wav");
                        JOptionPane.showMessageDialog(null,"Τo κρυπτονόμισμα έμεινε σταθερό, παίρνεις τα λεφτά σου πίσω");
                        break;
                    case 5:
                    case 6:
                        playSound("add_money.wav");
                        JOptionPane.showMessageDialog(null,"Τo κρυπτονόμισμα εκτοξεύτικε, παίρνεις διπλάσια λεφτά");
                        c.addMoney(600);
                        break;
                }
                frame.dispose();
            }
        });

        JButton choice2 = new JButton("Παράβλεψε το ποντάρισμα");
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
        frame.add(image_label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 25, 0, 0);
        frame.add(message, gbc);


        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        frame.add(choice1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 10, 0, 10);
        frame.add(choice2, gbc);

        frame.setVisible(true);
    }

    //Transformer(mutative): If player c can roll the dice, he rolls the dice
    //Postcondition: Player rolled the dice if able, else did nothing
    public void rollDice(character c){
        if(c.canRoll()){
            this.playSound("dice.wav");
            c.rollDice();
            if(c.getDice().getValue() == 6){
                this.playSound("jackpot.wav");
                board.giveJackpot(c);
            }
            c.setRoll(false);
        }
    }

    //Transformer(mutative): Lets the player get a loan
    //Postcondition: Loan given
    //@param c is the player getting the loan
    public void getLoan(character c){
        JFrame frame = new JFrame();
        try {
            int loan = Integer.parseInt(JOptionPane.showInputDialog("How much money do you want? (Must be a multiple of 1000"));
            if (loan > 0 && loan % 1000 == 0)
                c.addMoney(loan);
            else
                JOptionPane.showMessageDialog(frame ,"Invalid amount");
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    //Transformer(mutative): If the player c can end turn, ends turn
    //Postcondition: If able the player ended turn, else did nothing
    public void endTurn(character c){
        if(c.canEndTurn()){
            changeCurrentPlayer();
            this.playSound("end_turn.wav");
        }
    }

    //Transformer(mutative): Changes who the current player is
    //Postcondition: Current player is changed
    public void changeCurrentPlayer(){
        if(turn.getCurrentPlayer().getId() == 1){
            if(c2.hasFinished()) {
                turn.setCurrentPlayer(c1);
                turn.setInactivePlayer(c2);
            } else {
                turn.setCurrentPlayer(c2);
                turn.setInactivePlayer(c1);
            }

        } else {
            if(c1.hasFinished()) {
                turn.setCurrentPlayer(c2);
                turn.setInactivePlayer(c1);
            } else {
                turn.setCurrentPlayer(c1);
                turn.setInactivePlayer(c2);
            }
        }
    }


    //Accessor(selector): Returns the winner of the game or declares draw
    //Postcondition: Returned the winner or draw (0 for draw)
    public int getWinner(){
        if(c1.getScore() > c2.getScore())
            return 1;
        else if(c1.getScore() < c2.getScore())
            return 2;
        else
            return 0;
    }

    //Accessor(selector): Returns total months in the game
    //Postcondition: Total months returned
    public int getTotalMonths(){
        return turn.getTotalMonths();
    }

    //Accessor(selector): Returns the current month
    //Postcondition: Current month returned
    public int getCurrentMonth(){
        return turn.getCurrentMonth();
    }

    //Accessor(selector): Returns how many months are left in the game
    //Postcondition: Months left returned
    public int getMonthsLeft(){
        return this.turn.monthsLeft();
    }

    //Transformer(mutative): Increments the current month by one value
    //Postcondition: Current month incremented
    public void incrementMonth(){
        this.turn.setCurrentMonth(turn.getCurrentMonth() + 1);
    }

    //Observer: Returns true if the game has finished, false otherwise
    //Postcondition: Returns true if the game has finished
    public boolean gameHasFinished(){
        return (this.getPlayer(1).hasFinished() && this.getPlayer(2).hasFinished());
    }

    //Transformer(mutative): Plays the selected sound file
    //Postcondition: Sound file played
    //@param file is the file we want to play
    public void playSound(String file)
    {
        try
        {
            ClassLoader cldr = this.getClass().getClassLoader();
            URL url= cldr.getResource("Resources/audio/"+file);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

}
