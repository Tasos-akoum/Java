package View;

import Controller.Controller;
import Model.Player.character;
import Model.Tile.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

//Class View is the interface that the players interact with to play the game
public class View extends JFrame{

    private final int width = 1750;
    private final int height = 900;


    private Image image;
    private Controller game;
    private URL imageURL;
    private ClassLoader cldr;

    private JLayeredPane basic_panel;
    private JDesktopPane player1Field;
    private JDesktopPane player2Field;
    private JDesktopPane infoBox;

    private JLabel currentPlayer;
    private JLabel lastAction;
    private JLabel monthsLeft;
    private JLabel jackpot;
    private JLabel jackpot_value;
    private JLabel[][] tiles;
    private JLabel[][] days;
    private JLabel bg;
    private JLabel player1, player2;
    private JLabel player1_pawn, player2_pawn;
    private JLabel player1info, player2info;
    private JLabel logo;
    private JLabel dice1, dice2;

    private JButton rollDiceButton1, rollDiceButton2;
    private JButton getLoanButton1, getLoanButton2;
    private JButton endTurn1, endTurn2;
    private JButton showCards1, showCards2;

    //Constructor: Constructs a new View object which is the user interface and initializes everything needed to play the game
    //Postcondition: Window opened with everything set and the can start
    public View(){
        cldr = this.getClass().getClassLoader();
        game = new Controller();

        basic_panel = new JLayeredPane();
        player1Field = new JDesktopPane();
        player2Field = new JDesktopPane();
        infoBox = new JDesktopPane();
        tiles = new JLabel[5][7];
        days = new JLabel[5][7];
        bg = new JLabel();
        player1 = new JLabel("Player1");
        player1_pawn = new JLabel();
        player2 = new JLabel("Player2");
        player2_pawn = new JLabel();
        player1info = new JLabel("<html>Money: " + game.getPlayer(1).getMoney() + "<br>Loan: " + game.getPlayer(1).getLoan()
                                  + "<br>Bills: " + game.getPlayer(1).getBills() +"</html>");
        player2info = new JLabel("<html>Money: " + game.getPlayer(2).getMoney() + "<br>Loan: " + game.getPlayer(2).getLoan()
                + "<br>Bills: " + game.getPlayer(2).getBills() +"</html>");
        logo = new JLabel();
        dice1 = new JLabel();
        dice2 = new JLabel();
        rollDiceButton1 = new JButton("Roll Dice");
        rollDiceButton2 = new JButton("Roll Dice");
        getLoanButton1 = new JButton("Get Loan");
        getLoanButton2 = new JButton("Get Loan");
        endTurn1 = new JButton("End turn");
        endTurn2 = new JButton("End turn");
        showCards1 = new JButton("My DealCards");
        showCards2 = new JButton("My DealCards");

        imageURL = cldr.getResource("Resources/basic/logo.png");
        this.setIconImage(new ImageIcon(imageURL).getImage());

        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Test");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        basic_panel.setVisible(true);

        initComponents();
        init_tiles();
        init_buttons();
        this.add(basic_panel);
        this.setVisible(true);

        Update update = new Update();
        update.start();
    }

    //Transformer(mutative): Stores the image in the path in the variable image and scales its width and height
    //Postcondition: Image stores and width and height set
    //@param path is the path to the image file, @param width is the width of the image
    //@param height is the height of the image
    private void getImage(String path, int width, int height){
        imageURL = cldr.getResource(path);
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width,height, Image.SCALE_SMOOTH);
    }

    //Transformer(mutative): Sets an image to all tiles depending on their type
    //Postcondition: All tiles have images
    //@param tileLabel displays the image
    private void tilesSetImage(JLabel tileLabel, Tile tile, int width, int height){
        if(tile instanceof BuyerTile){
            getImage("Resources/basic/buyer.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof DealTile){
            getImage("Resources/basic/deal.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof FamilyCasinoNightTile){
            getImage("Resources/basic/casino.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof LotteryTile){
            getImage("Resources/basic/lottery.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof MessageTile){
                getImage("Resources/basic/lottery.png", width, height);
                tileLabel.setIcon(new ImageIcon(image));
                switch (((MessageTile) tile).getValue()){
                    case 1:
                        getImage("Resources/basic/mc1.png", width, height);
                        tileLabel.setIcon(new ImageIcon(image));
                        break;
                    case 2:
                        getImage("Resources/basic/mc2.png", width, height);
                        tileLabel.setIcon(new ImageIcon(image));
                        break;
                    default:
                        break;
                }

        }
        else if(tile instanceof PaydayTile){
            getImage("Resources/basic/pay.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof RadioContestTile){
            getImage("Resources/basic/radio.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof StartTile){
            getImage("Resources/basic/start.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof SweepstakesTile){
            getImage("Resources/basic/sweep.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
        else if(tile instanceof YardSaleTile){
            getImage("Resources/basic/yard.png", width, height);
            tileLabel.setIcon(new ImageIcon(image));
        }
    }

    //Transformer(mutative): Initializes some labels and adds them to the basic_panel layerpane
    //Postcondition: Components initialized
    private void initComponents(){
        //Initialize the logo
        getImage("Resources/basic/logo.png", width-420,200);
        logo.setIcon(new ImageIcon(image));
        logo.setBounds(0,0,width - 420,200);
        basic_panel.add(logo);

        //Initialize background image
        getImage("Resources/basic/bg_green.png", width, height);
        bg.setIcon((new ImageIcon(image)));
        bg.setBounds(0, 0, width, height);
        basic_panel.add(bg, JLayeredPane.DEFAULT_LAYER);

        //Initialize player pawns on the panel
        getImage("Resources/basic/pawn_blue.png", 100, 100);
        player1_pawn.setIcon(new ImageIcon(image));
        basic_panel.add(player1_pawn, JLayeredPane.MODAL_LAYER);

        getImage("Resources/basic/pawn_yellow.png", 100, 100);
        player2_pawn.setIcon(new ImageIcon(image));
        basic_panel.add(player2_pawn, JLayeredPane.MODAL_LAYER);

        getImage("Resources/basic/jackpot.png", 400,90);
        jackpot = new JLabel();
        jackpot.setIcon(new ImageIcon(image));
        jackpot.setBounds(width - 900, height - 160, 400, 90);
        basic_panel.add(jackpot, JLayeredPane.PALETTE_LAYER);

        jackpot_value = new JLabel("Jackpot: 0");
        jackpot_value.setBounds(width - 750, height - 60, 150, 20);
        jackpot_value.setForeground(Color.white);
        jackpot_value.setFont(new Font(Font.DIALOG, Font.BOLD, 18));
        basic_panel.add(jackpot_value, JLayeredPane.PALETTE_LAYER);

        init_playerFields();
        init_infoBox();
    }

    //Transformer(mutative): Initializes tiles
    //Postcondition: Tiles initialized
    private void init_tiles(){
        int tiles_width = 190;
        int tiles_height = 108;
        int tileDistance = 0;

        //Initialize each tile label with images
        for(int i = 0; i < 5; i++) {
            if(i != 4) {
                for (int j = 0; j < 7; j++) {
                    if (game.board.getTile(i, j) != null) {
                        tiles[i][j] = new JLabel();
                        tilesSetImage(tiles[i][j], game.board.getTile(i, j), tiles_width, tiles_height);
                        tiles[i][j].setBounds(190 * j, 225 + (i * tiles_height) + (tileDistance * i), tiles_width, tiles_height);
                        tiles[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));
                        basic_panel.add(tiles[i][j], JLayeredPane.PALETTE_LAYER);
                    }
                    tileDistance = 25;
                }

            } else {
                for (int j = 0; j < 4; j++) {
                    if(game.board.getTile(i,j) != null) {
                        tiles[i][j] = new JLabel();
                        tilesSetImage(tiles[i][j], game.board.getTile(i, j), tiles_width, tiles_height);
                        tiles[i][j].setBounds(190 * j, 225 + (i * tiles_height) + (tileDistance * i), tiles_width, tiles_height);
                        tiles[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));
                        basic_panel.add(tiles[i][j], JLayeredPane.PALETTE_LAYER);
                    }
                }
            }
        }

        //Initialized each date label with days and dates
        this.setDays(Color.orange);
    }

    //Transformer(mutative):Sets a date to each tile with a colored background
    //Postcondition: days set
    //@param color is the color of the label's background
    private void setDays(Color color){
        int tiles_width = 190;
        int tiles_height = 108;
        int tileDistance = 0;

        //Initialized each date label with days and dates
        for(int i = 0; i < 5; i++){
            int dayLabel_distance = 0;
            if(i != 4) {
                for (int j = 0; j < 7; j++) {
                    if(game.board.getTile(i,j) != null) {
                        days[i][j] = new JLabel(game.board.getTile(i, j).getDay(), SwingConstants.CENTER);
                        days[i][j].setBounds(180 * j + (dayLabel_distance * j), 200 + (i * tiles_height) + (tileDistance * i), tiles_width, 25);
                        days[i][j].setOpaque(true);
                        days[i][j].setBackground(color);
                        days[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));
                        basic_panel.add(days[i][j], JLayeredPane.PALETTE_LAYER);

                        dayLabel_distance = 10;
                    }
                }
                tileDistance = 25;
            } else {
                for (int j = 0; j < 4; j++) {
                    if(game.board.getTile(i,j) != null) {
                        days[i][j] = new JLabel(game.board.getTile(i, j).getDay(), SwingConstants.CENTER);
                        days[i][j].setBounds(190 * j + (dayLabel_distance * j), 200 + (i * tiles_height) + (tileDistance * i), tiles_width, 25);
                        days[i][j].setOpaque(true);
                        days[i][j].setBackground(color);
                        days[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));
                        basic_panel.add(days[i][j], JLayeredPane.PALETTE_LAYER);
                    }
                }
            }
        }

    }

    //Transformer(mutative): Update the days and dates everytime a player goes to the next month
    //Postcondition: Days updated
    //@param color is the color of the label's background
    private void updateDays(Color color) {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 7; j++){
                if(days[i][j] != null) {
                    days[i][j].setText(game.board.getTile(i,j).getDay());
                    days[i][j].setBackground(color);
                }
            }
        }
    }

    //Transformer(mutative): Initializes infobox
    //Postcondition: infobox initialized
    private void init_infoBox(){
        infoBox.setBounds(width - 395, 250, 380, 150);
        infoBox.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));
        infoBox.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        infoBox.add(new JLabel("Info Box"), gbc);

        monthsLeft = new JLabel("Months left: " + game.getMonthsLeft());
        gbc.gridy = 1;
        infoBox.add(monthsLeft, gbc);

        currentPlayer = new JLabel("Current Player: Player" + game.getCurrentPlayer().getId());
        gbc.gridy = 2;
        infoBox.add(currentPlayer, gbc);

        lastAction = new JLabel("-->");
        gbc.gridy = 3;
        infoBox.add(lastAction, gbc);

        basic_panel.add(infoBox, JLayeredPane.PALETTE_LAYER);
    }



    //Transformers(mutative): Initializes player fields
    //Postcondition: Player fields initialized
    private void init_playerFields(){
        player1Field.setBounds(width - 395, 10, 380, 190);
        player1Field.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.blue));
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        player1Field.setLayout(layout);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        player1Field.add(player1, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,10,0);
        player1Field.add(player1info, gbc);

        gbc.insets = new Insets(2,5,2,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        gbc.weighty = 0;
        player1Field.add(rollDiceButton1, gbc);

        getImage("Resources/basic/dice-1.jpg", 80,40);
        dice1.setIcon(new ImageIcon(image));
        dice1.setBounds(width - 150, 120, 80, 40);
        basic_panel.add(dice1, JLayeredPane.MODAL_LAYER);

        gbc.gridx = 0;
        gbc.gridy = 3;
        player1Field.add(getLoanButton1, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        player1Field.add(showCards1, gbc);
        gbc.gridx = 1;
        player1Field.add(endTurn1, gbc);

        basic_panel.add(player1Field, JLayeredPane.PALETTE_LAYER);


        player2Field.setBounds(width - 395, height - 250, 380, 190);
        player2Field.setBorder(BorderFactory.createMatteBorder(3,3,3,3, Color.yellow));
        player2Field.setLayout(layout);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        player2Field.add(player2, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0,0,10,0);
        player2Field.add(player2info, gbc);

        gbc.insets = new Insets(2,5,2,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 2;
        gbc.weighty = 0;
        player2Field.add(rollDiceButton2, gbc);

        getImage("Resources/basic/dice-1.jpg", 80,40);
        dice2.setIcon(new ImageIcon(image));
        dice2.setBounds(width - 150, height - 140, 80, 40);
        basic_panel.add(dice2, JLayeredPane.MODAL_LAYER);

        gbc.gridx = 0;
        gbc.gridy = 3;
        player2Field.add(getLoanButton2, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        player2Field.add(showCards2, gbc);
        gbc.gridx = 1;
        player2Field.add(endTurn2, gbc);

        basic_panel.add(player2Field, JLayeredPane.PALETTE_LAYER);
    }

    //Transformer(mutative): Initializes the buttons and their actions
    //Postcondition: Buttons initialized and can perform actions
    private void init_buttons(){
        rollDiceButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rollDice(game.getPlayer(1));
                game.changePlayerPosition(game.getPlayer(1));
            }
        });

        rollDiceButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.rollDice(game.getPlayer(2));
                game.changePlayerPosition(game.getPlayer(2));
            }
        });

        showCards1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer(1).seeCards();
            }
        });

        showCards2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getPlayer(2).seeCards();
            }
        });

        getLoanButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getLoan(game.getPlayer(1));
            }
        });

        getLoanButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getLoan(game.getPlayer(2));
            }
        });

        endTurn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.endTurn(game.getPlayer(1));
                if(game.getCurrentMonth() == 2){ //Check current month
                    updateDays(Color.blue); //Update the days on the user interface
                }
                else if(game.getCurrentMonth() == 3){
                    updateDays(Color.green);
                }
                gameEnd(); //Check if the game has ended
            }
        });

        endTurn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.endTurn(game.getPlayer(2));
                if(game.getCurrentMonth() == 2){ //Check current month
                    updateDays(Color.blue); //Update the days on the user interface
                }
                else if(game.getCurrentMonth() == 3){
                    updateDays(Color.green);
                }
                gameEnd(); //Check if the game has ended
            }
        });
    }

    //Transformer(mutative): Update each player's pawn position
    //Postcondition: Player's pawn updated
    private void updatePlayerPosition(){
        character c1 = game.getPlayer(1);
        character c2 = game.getPlayer(2);

        if(c1.getPositionY() == c2.getPositionY() && c1.getPositionX() == c2.getPositionX()) {
            player1_pawn.setBounds(tiles[c1.getPositionX()][c1.getPositionY()].getBounds().x + 20, tiles[c1.getPositionX()][c1.getPositionY()].getBounds().y + 5, 100, 100);
            player2_pawn.setBounds(tiles[c2.getPositionX()][c2.getPositionY()].getBounds().x + 80, tiles[c2.getPositionX()][c2.getPositionY()].getBounds().y + 5, 100, 100);
        } else {
            player1_pawn.setBounds(tiles[c1.getPositionX()][c1.getPositionY()].getBounds().x + 50, tiles[c1.getPositionX()][c1.getPositionY()].getBounds().y + 5, 100, 100);
            player2_pawn.setBounds(tiles[c2.getPositionX()][c2.getPositionY()].getBounds().x + 50, tiles[c2.getPositionX()][c2.getPositionY()].getBounds().y + 5, 100, 100);
        }
    }

    //Transformer(mutative): Updates the player pawns on the board
    //Postcondition: Player pawns updated
    private void updatePlayerInfo(){
        player1info.setText("<html>Money: " + game.getPlayer(1).getMoney() + "<br>Loan: " + game.getPlayer(1).getLoan()
                + "<br>Bills: " + game.getPlayer(1).getBills() +"</html>");
        player2info.setText("<html>Money: " + game.getPlayer(2).getMoney() + "<br>Loan: " + game.getPlayer(2).getLoan()
                + "<br>Bills: " + game.getPlayer(2).getBills() +"</html>");
    }

    //Transformer(mutative): Updates the infobox info
    //Postcondition: infobox info updated
    private void updateInfoBox(){
        character c = game.getCurrentPlayer();

        currentPlayer.setText("Current Player: Player" + c.getId());
        if(c.getId() == 1)
            currentPlayer.setForeground(Color.blue);
        else
            currentPlayer.setForeground(Color.orange); //Orange instead of yellow for better clarity

        monthsLeft.setText("Months left: " + game.getMonthsLeft());

        Tile tile = game.board.getTile(c.getPositionX(), c.getPositionY());

        if(tile instanceof BuyerTile){
            lastAction.setText("-->You can sell one of your Deal cards");
        }
        else if(tile instanceof DealTile){
            lastAction.setText("-->Draw 1 Deal card");
        }
        else if(tile instanceof FamilyCasinoNightTile){
            lastAction.setText("-->It's family casino night!");
        }
        else if(tile instanceof LotteryTile){
            lastAction.setText("-->Prepare to enter the lottery!");
        }
        else if(tile instanceof MessageTile){
            switch (((MessageTile) tile).getValue()){
                case 1:
                    lastAction.setText("-->Draw 1 Mail card");
                    break;
                case 2:
                    lastAction.setText("-->Draw 2 Mail cards");
                    break;
                default:
                    break;
            }

        }
        else if(tile instanceof PaydayTile){
            lastAction.setText("-->PayDay!");
        }
        else if(tile instanceof RadioContestTile){
            lastAction.setText("-->Today is radio contest day!");
        }
        else if(tile instanceof SweepstakesTile){
            lastAction.setText("-->You just rolled on sweepstakes!");
        }
        else if(tile instanceof YardSaleTile){
            lastAction.setText("-->Roll the dice and pay the bank 100 * the amount you rolled");
        }
    }

    //Transformer(mutative): Updated dice image depending on their values
    //Postcondition: Dice images updated
    private void updateDiceImage(){
        getImage("Resources/basic/dice-" + game.getPlayer(1).getDice().getValue() + ".jpg", 80, 40);
        dice1.setIcon(new ImageIcon(image));

        getImage("Resources/basic/dice-" + game.getPlayer(2).getDice().getValue() + ".jpg", 80, 40);
        dice2.setIcon(new ImageIcon(image));
    }

    //Transformer(mutative): Update the buttons so each player can see if it's his turn
    //Postcondition: Buttons updated
    private void updateButtons(){
        if(game.getCurrentPlayer().getId() != 1){
            rollDiceButton1.setBackground(Color.black);
            getLoanButton1.setBackground(Color.black);

            rollDiceButton2.setBackground(null);
            getLoanButton2.setBackground(null);
        } else {
            rollDiceButton2.setBackground(Color.black);
            getLoanButton2.setBackground(Color.black);

            rollDiceButton1.setBackground(null);
            getLoanButton1.setBackground(null);
        }

        if(!game.getPlayer(1).canEndTurn())
            endTurn1.setBackground(Color.black);
        else
            endTurn1.setBackground(null);

        if(!game.getPlayer(2).canEndTurn())
            endTurn2.setBackground(Color.black);
        else
            endTurn2.setBackground(null);
    }

    //Transformer(mutative): Shows the winner and asks to start a new game if the game has finished
    //Postcondition: Game finished and players asked to end or start again
    private void gameEnd(){
        if(game.hasEnded()) {
            if(game.getWinner() != 0)
                JOptionPane.showMessageDialog(null, "Νίκησε ο παίχτης " +  game.getWinner() + " με σκορ "  + game.getPlayer(game.getWinner()).getScore() + "!",
                        "Τέλος παιχνιδιού", JOptionPane.INFORMATION_MESSAGE);
            else
                JOptionPane.showMessageDialog(null, "Το παιχνίδι πήγε ισοπαλία", "Τέλος παιχνιδιού", JOptionPane.INFORMATION_MESSAGE);

            this.dispose();
            int choice = JOptionPane.showConfirmDialog(null, "Would you like to start a new game?","New game" ,JOptionPane.YES_NO_OPTION);
            if(choice == JOptionPane.YES_OPTION){
                new View();
            }
            else if(choice == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        }
    }

    //Class Update extends class Thread and is responsible for updating the info of the game every 300 milliseconds
    private class Update extends Thread{
        @Override
        //Transformer(mutative): Updates the basic information needed on the user interface
        //Postcondition: Information updated
        public void run() {
            try{
                while(true){
                    updatePlayerInfo();
                    updatePlayerPosition();
                    updateInfoBox();
                    updateDiceImage();
                    updateButtons();
                    jackpot_value.setText("Jackpot: " + game.board.getJackpot());
                    Thread.sleep(300);
                }
            } catch (InterruptedException e){
                System.err.println(e.getMessage());
            }
        }
    }

}
