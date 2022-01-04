package View;

import Controller.Controller;

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
        player2 = new JLabel("Player2");
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

    private void init_tiles(){
//        for()
        int tiles_width = (width - 265) / 7;
        int tiles_height = 108;
//        tiles[0] = new JLabel();
//        this.getImage("Resources/basic/mc1.png", tiles_width, tiles_height);
//        tiles[0].setIcon(new ImageIcon(image));
//        tiles[0].setBounds(0, 190, tiles_width,tiles_height);

//        tiles[10] = new JLabel();
//        this.getImage("Resources/basic/mc2.png", tiles_width, tiles_height);
//        tiles[10].setIcon(new ImageIcon(image));
//        tiles[10].setBounds(0, 190 + tiles_height, tiles_width,tiles_height);
//        basic_panel.add(tiles[10]);

        int tileDistance = 0;

        for(int i = 0; i < 5; i++) {
            if(i != 4) {
                for (int j = 0; j < 7; j++) {
                    tiles[i][j] = new JLabel();
                    this.getImage("Resources/basic/mc1.png", tiles_width, tiles_height);
                    tiles[i][j].setIcon(new ImageIcon(image));
                    tiles[i][j].setBounds(190 * j, 225 + (i * tiles_height) + (tileDistance * i), tiles_width, tiles_height);
                    basic_panel.add(tiles[i][j], JLayeredPane.PALETTE_LAYER);
                }
                tileDistance = 25;

            } else {
                for (int j = 0; j < 4; j++) {
                    tiles[i][j] = new JLabel();
                    this.getImage("Resources/basic/mc1.png", tiles_width, tiles_height);
                    tiles[i][j].setIcon(new ImageIcon(image));
                    tiles[i][j].setBounds(180 * j, 225 + (i * tiles_height) + (tileDistance * i), tiles_width, tiles_height);
                    basic_panel.add(tiles[i][j], JLayeredPane.PALETTE_LAYER);
                }
            }
        }

        int tile_index = 0; //Is the index for the tile array in the class Board
        tileDistance = 0;

        for(int i = 0; i < 5; i++){
            int dayLabel_distance = 0;
            if(i != 4) {
                for (int j = 0; j < 7; j++) {
                    days[i][j] = new JLabel(game.board.getTile(tile_index).getDay(), SwingConstants.CENTER);
                    days[i][j].setBounds(180 * j + (dayLabel_distance * j), 200 +(i * tiles_height) + (tileDistance * i), tiles_width, 25);
                    days[i][j].setOpaque(true);
                    days[i][j].setBackground(Color.orange);
                    basic_panel.add(days[i][j], JLayeredPane.PALETTE_LAYER);

                    tile_index++;
                    dayLabel_distance = 10;
                }
                 tileDistance = 25;
            } else {
                for (int j = 0; j < 4; j++) {
                    days[i][j] = new JLabel(game.board.getTile(tile_index).getDay(), SwingConstants.CENTER);
                    days[i][j].setBounds(180 * j + (dayLabel_distance * j), 200 +(i * tiles_height) + (tileDistance * i), tiles_width, 25);
                    days[i][j].setOpaque(true);
                    days[i][j].setBackground(Color.orange);
                    basic_panel.add(days[i][j], JLayeredPane.PALETTE_LAYER);

                    tile_index++;
                }
            }
        }



//        basic_panel.add(tiles[0]);
    }

    private void initComponents(){
        basic_panel.setBackground(new Color(49, 161, 36));

        player1.setBounds(width - 265, 15, 100, 20);
        money1.setBounds(width - 265, 50, 100, 20);
        loan1.setBounds(width - 265, 70, 100, 20);
        bills1.setBounds(width - 265, 90, 100, 20);
        rollDiceButton1.setBounds(width - 265, 120, 100, 20);
        getLoanButton1.setBounds(width - 265, 145, 100, 20);
        endTurn1.setBounds(width - 265, 170, 100,20);
        showCards1.setBounds(width - 175, 15,133,20);

        player1Field.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.blue));
        player1Field.setBounds(width - 270,10,250,190);

        player2.setBounds(width - 265, height - 245, 100, 20);
        money2.setBounds(width - 265, height - 210, 100, 20);
        loan2.setBounds(width - 265, height - 190, 100, 20);
        bills2.setBounds(width - 265, height - 170, 100, 20);
        rollDiceButton2.setBounds(width - 265, height - 140, 100, 20);
        getLoanButton2.setBounds(width - 265, height - 115, 100, 20);
        endTurn2.setBounds(width - 265, height - 90, 100,20);
        showCards2.setBounds(width - 175, height - 245,133,20);

        player2Field.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.yellow));
        player2Field.setBounds(width - 270,height - 250,250,190);

//        imageURL = cldr.getResource("Resources/basic/logo.png");
//        Image image = new ImageIcon(imageURL).getImage();
//        image = image.getScaledInstance(width - 271,200, Image.SCALE_SMOOTH);
        getImage("Resources/basic/logo.png", width-330,200);
        logo.setIcon(new ImageIcon(image));
        logo.setBounds(0,0,width - 398,200);
        basic_panel.add(logo);

        getImage("Resources/basic/bg_green.png", width, height);
        bg.setIcon((new ImageIcon(image)));
        bg.setBounds(0, 0, width, height);
        basic_panel.add(bg, JLayeredPane.DEFAULT_LAYER);

//        basic_panel.add(player1);
//        basic_panel.add(money1);
//        basic_panel.add(loan1);
//        basic_panel.add(bills1);
//        basic_panel.add(rollDiceButton1);
//        basic_panel.add(getLoanButton1);
//        basic_panel.add(endTurn1);
//        basic_panel.add(showCards1);
//
//        basic_panel.add(player2);
//        basic_panel.add(money2);
//        basic_panel.add(loan2);
//        basic_panel.add(bills2);
//        basic_panel.add(rollDiceButton2);
//        basic_panel.add(getLoanButton2);
//        basic_panel.add(endTurn2);
//        basic_panel.add(showCards2);
//
//        basic_panel.add(player1Field, JLayeredPane.DEFAULT_LAYER);
//        basic_panel.add(player2Field, JLayeredPane.DEFAULT_LAYER);

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
