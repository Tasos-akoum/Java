package View;

import javax.swing.*;
import java.awt.*;
import java.net.CookieHandler;
import java.net.URL;

public class View extends JFrame {

    private final int width = 1600;
    private final int height = 900;

    private URL imageURL;
    private ClassLoader cldr;
    private JDesktopPane basic_panel;
    private JDesktopPane player1Field;
    private JDesktopPane player2Field;
    private JDesktopPane infoBox;
    private JButton rollDiceButton1, rollDiceButton2;
    private JButton getLoanButton1, getLoanButton2;
    private JButton endTurn1, endTurn2;
    private JButton showCards1, showCards2;
    private JButton drawDealCard, drawMailCard;

    public View(){
        basic_panel = new JDesktopPane();
        player1Field = new JDesktopPane();
        player2Field = new JDesktopPane();
        infoBox = new JDesktopPane();
        rollDiceButton1 = new JButton("Roll Dice");
        rollDiceButton2 = new JButton();
        getLoanButton1 = new JButton("Get Loan");
        getLoanButton2 = new JButton();
        endTurn1 = new JButton("End turn");
        endTurn2 = new JButton();
        showCards1 = new JButton();
        showCards2 = new JButton();
        drawDealCard = new JButton();
        drawMailCard = new JButton();

        cldr = this.getClass().getClassLoader();
        imageURL = cldr.getResource("images/logo.png");
        this.setIconImage(new ImageIcon(imageURL).getImage());

        this.setSize(width,height);
        this.setBackground(new Color(0,0,0));
        this.setResizable(false);
        this.setTitle("Test");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents(){
        basic_panel.setBackground(new Color(64, 232, 57));

        player1Field.setBackground(Color.yellow);
        player1Field.setBounds(width - 270,height - 250,250,200);

        player2Field.setBackground(Color.blue);
        player2Field.setBounds(width - 270,10,250,200);

        basic_panel.add(player1Field);
        basic_panel.add(player2Field);
        this.add(basic_panel);
        this.setVisible(true);
    }


    public static void main(String[] args) {
        View view = new View();
    }
}
