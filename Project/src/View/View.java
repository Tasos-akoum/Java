package View;

import Controller.Controller;
import Model.Player.character;
import Model.Tile.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.net.CookieHandler;
import java.net.URL;

public class View extends JFrame {

    private final int width = 1750;
    private final int height = 900;


    private Image image;
    private Controller game;
    private URL imageURL;
    private ClassLoader cldr;

    private ImagePane basic_panel;
    private JDesktopPane player1Field;
    private JDesktopPane player2Field;
    private JDesktopPane infoBox;

    private JLabel[][] tiles;
    private JLabel[][] days;
    private JLabel bg;
    private JLabel player1, player2;
    private JLabel player1_pawn, player2_pawn;
    private JLabel money1, money2;
    private JLabel loan1, loan2;
    private JLabel bills1, bills2;
    private JLabel logo;

    private JButton rollDiceButton1, rollDiceButton2;
    private JButton getLoanButton1, getLoanButton2;
    private JButton endTurn1, endTurn2;
    private JButton showCards1, showCards2;
    private JButton drawDealCard, drawMailCard;

    public View(){
        game = new Controller();
        cldr = this.getClass().getClassLoader();

        basic_panel = new ImagePane();
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
        money1 = new JLabel("Money: " + game.getPlayer(1).getMoney());
        money2 = new JLabel("Money: " + game.getPlayer(2).getMoney());
        loan1 = new JLabel("Loan: " + game.getPlayer(1).getLoan());
        loan2 = new JLabel("Loan: " + game.getPlayer(2).getLoan());
        bills1 = new JLabel("Bills: " + game.getPlayer(1).getBills());
        bills2 = new JLabel("Bills: " + game.getPlayer(2).getBills());
        logo = new JLabel();
        rollDiceButton1 = new JButton("Roll Dice");
        rollDiceButton2 = new JButton("Roll Dice");
        getLoanButton1 = new JButton("Get Loan");
        getLoanButton2 = new JButton("Get Loan");
        endTurn1 = new JButton("End turn");
        endTurn2 = new JButton("End turn");
        showCards1 = new JButton("My DealCards");
        showCards2 = new JButton("My DealCards");
        drawDealCard = new JButton();
        drawMailCard = new JButton();

        imageURL = cldr.getResource("Resources/basic/logo.png");
        this.setIconImage(new ImageIcon(imageURL).getImage());

        this.setSize(width,height);
        this.setResizable(false);
        this.setTitle("Test");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        basic_panel.setVisible(true);

        initComponents();
        init_tiles();
        this.add(basic_panel);
        this.setVisible(true);
    }

    private void getImage(String path, int width, int height){
        imageURL = cldr.getResource(path);
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width,height, Image.SCALE_SMOOTH);
    }

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

        tileDistance = 0;

        //Initialized each date label with days and dates
        for(int i = 0; i < 5; i++){
            int dayLabel_distance = 0;
            if(i != 4) {
                for (int j = 0; j < 7; j++) {
                    if(game.board.getTile(i,j) != null) {
                        days[i][j] = new JLabel(game.board.getTile(i, j).getDay(), SwingConstants.CENTER);
                        days[i][j].setBounds(180 * j + (dayLabel_distance * j), 200 + (i * tiles_height) + (tileDistance * i), tiles_width, 25);
                        days[i][j].setOpaque(true);
                        days[i][j].setBackground(Color.orange);
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
                        days[i][j].setBackground(Color.orange);
                        days[i][j].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.white));
                        basic_panel.add(days[i][j], JLayeredPane.PALETTE_LAYER);
                    }
                }
            }
        }

        this.updatePlayerPosition();

    }

    private void updatePlayerPosition(){
        character c1 = game.getPlayer(1);
        character c2 = game.getPlayer(2);

        player1_pawn.setBounds(tiles[c1.getPositionX()][c1.getPositionY()].getBounds().x + 50, tiles[c1.getPositionX()][c1.getPositionY()].getBounds().y + 5, 100, 100);
        player2_pawn.setBounds(tiles[c2.getPositionX()][c2.getPositionY()].getBounds().x + 50, tiles[c2.getPositionX()][c2.getPositionY()].getBounds().y + 40, 50, 100);
    }

    private void initComponents(){
        getImage("Resources/basic/logo.png", width-420,200);
        logo.setIcon(new ImageIcon(image));
        logo.setBounds(0,0,width - 420,200);
        basic_panel.add(logo);

        getImage("Resources/basic/bg_green.png", width, height);
        bg.setIcon((new ImageIcon(image)));
        bg.setBounds(0, 0, width, height);
        basic_panel.add(bg, JLayeredPane.DEFAULT_LAYER);

        getImage("Resources/basic/pawn_blue.png", 100, 100);
        player1_pawn.setIcon(new ImageIcon(image));
        player1_pawn.setBounds(50,230,100,100);
        game.getPlayer(1).setPosition(1,1);
        getImage("Resources/basic/pawn_yellow.png", 50, 100);
        player2_pawn.setIcon(new ImageIcon(image));

        basic_panel.add(player1_pawn, JLayeredPane.PALETTE_LAYER);

        PlayerInfoPane infoPane = new PlayerInfoPane();
        infoPane.setBounds(width - 270, 15,250,190);
        basic_panel.add(infoPane, JLayeredPane.PALETTE_LAYER);
    }

    public class ImagePane extends JDesktopPane{
        Image image = null;

        public ImagePane(){

        }

        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            g.drawImage(image, 0,0,this);
        }
    }

    public class PlayerInfoPane extends JPanel{
        public PlayerInfoPane() {
            this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.insets = new Insets(2, 2, 22, 2);

            this.add(player1, gbc);
            gbc.insets = new Insets(2,2,2,2);
            this.add(money1, gbc);
            this.add(loan1, gbc);
            gbc.weighty = 1;
            gbc.anchor = GridBagConstraints.FIRST_LINE_START;
            this.add(bills1, gbc);
            this.add(showCards1,gbc);
            this.add(rollDiceButton1, gbc);
            this.add(getLoanButton1, gbc);
            this.add(endTurn1, gbc);
        }
    }

    public static void main(String[] args) {
        View view = new View();
    }
}
