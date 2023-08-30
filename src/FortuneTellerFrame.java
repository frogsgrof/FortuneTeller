import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class FortuneTellerFrame extends JFrame {

    ImageIcon crystalBallIcon, starIcon, smallStarIcon;
    Image frameIcon;
    Font headerFont, fortuneFont, btnFont;
    final float HEADER_FONT_SIZE = 40f,
            FRTN_FONT_SIZE = 22f,
            BTN_FONT_SIZE = 24f;
    Color indigo, darkIndigo, offBlack;
    MatteBorder frameBorder, headerBorder;

    // for fortunes
    ArrayList<String> fortunes;
    int lastIndex;
    Random ran = new Random();

    // Swing Containers
    JPanel topPnl, midPnl, btmPnl;
    JLabel topLbl;
    JTextArea textArea;
    JScrollPane scrollPane;
    JButton fortuneBtn, quitBtn;

    public FortuneTellerFrame(int screenWidth, int screenHeight) {
        getResources();
        fortunes = createFortuneList();

        // creates the top label and sets its stats
        topLbl = new JLabel(crystalBallIcon);
        topLbl.setText("Fortune Teller!");
        topLbl.setFont(headerFont);
        topLbl.setHorizontalTextPosition(JLabel.CENTER);
        topLbl.setVerticalTextPosition(JLabel.TOP);
        topLbl.setForeground(Color.white);
        topLbl.setAlignmentY(Box.TOP_ALIGNMENT);
        topLbl.setAlignmentX(Box.CENTER_ALIGNMENT);
        topLbl.setIconTextGap(1);
        topLbl.setPreferredSize(new Dimension(1000, 260));

        // creates the TextArea, where the fortunes will go
        textArea = new JTextArea();
        textArea.setOpaque(true);
        textArea.setBackground(darkIndigo);
        textArea.setForeground(Color.lightGray);
        textArea.setFont(fortuneFont);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(10, 20, 10, 20));
        textArea.setSize(this.getWidth() * 3 / 4, this.getHeight() / 2);

        // creates a JScrollPane and adds the TextArea to it
        scrollPane = new JScrollPane(textArea);
        scrollPane.setSize(screenWidth * 3 / 4, screenHeight / 2);
        scrollPane.setAlignmentX(Box.CENTER_ALIGNMENT);

        // creates the buttons
        fortuneBtn = new JButton("Read my fortune!");
        quitBtn = new JButton("Quit");
        initButtons();

        // creates the three main panels
        topPnl = new JPanel();
        midPnl = new JPanel();
        btmPnl = new JPanel();
        // this method configures the panels and adds their sub-components
        initPanels();

        // adds a BoxLayout to the frame
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        // adds the JPanels to the frame
        add(topPnl);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 20)));
        add(midPnl);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 50)));
        add(btmPnl);
        add(Box.createRigidArea(new Dimension(this.getWidth(), 10)));

        // sets the fields of the frame
        setTitle("Fortune Teller");
        setIconImage(frameIcon);
        getContentPane().setBackground(indigo);
        getRootPane().setBorder(frameBorder);

        setSize(new Dimension(screenWidth * 3 / 4, 800));
        setLocation(screenWidth / 8, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates instances of Color, ImageIcon, Border, and Font
     */
    private void getResources() {

        indigo = new Color(25, 0, 77, 255);
        offBlack = new Color(8, 0, 26, 255);
        darkIndigo = new Color(17, 0, 51, 255);

        File dir = new File(System.getProperty("user.dir"));
        File imageDir = new File(dir + "\\images\\");
        File fontDir = new File(dir + "\\fonts\\");

        crystalBallIcon = new ImageIcon(imageDir + "\\crystal_ball.gif","Crystal ball");
        starIcon = new ImageIcon(imageDir + "\\stars.png");
        smallStarIcon = new ImageIcon(imageDir + "\\small_star.png");

        frameBorder = new MatteBorder(20, 40, 20, 40, starIcon);
        headerBorder = new MatteBorder(smallStarIcon);

        try {
            frameIcon = ImageIO.read(new File(imageDir + "\\moon.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File headerFontFile = new File(fontDir + "\\Moonstar.ttf");
        File fortuneFontFile = new File(fontDir + "\\Butler_Medium.otf");
        File btnFontFile = new File(fontDir + "\\evanescent_p.ttf");

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            headerFont = Font.createFont(Font.TRUETYPE_FONT, headerFontFile).deriveFont(Font.PLAIN, HEADER_FONT_SIZE);
            fortuneFont = Font.createFont(Font.TRUETYPE_FONT, fortuneFontFile).deriveFont(Font.PLAIN, FRTN_FONT_SIZE);
            btnFont = Font.createFont(Font.TRUETYPE_FONT, btnFontFile).deriveFont(Font.BOLD, BTN_FONT_SIZE);
            ge.registerFont(headerFont);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Configures all JPanels (top, middle, and bottom) and adds their respective components.
     */
    private void initPanels() {
        topPnl.setLayout(new BoxLayout(topPnl, BoxLayout.X_AXIS));
        topPnl.setBackground(offBlack);
        topPnl.setBorder(headerBorder);
        topPnl.setOpaque(true);

        midPnl.setLayout(new BoxLayout(midPnl, BoxLayout.X_AXIS));
        midPnl.setOpaque(false);

        btmPnl.setLayout(new BoxLayout(btmPnl, BoxLayout.X_AXIS));
        btmPnl.setOpaque(false);
        btmPnl.setAlignmentY(Box.BOTTOM_ALIGNMENT);

        topPnl.add(Box.createHorizontalGlue());
        topPnl.add(topLbl);
        topPnl.add(Box.createHorizontalGlue());

        midPnl.add(Box.createHorizontalGlue());
        midPnl.add(scrollPane);
        midPnl.add(Box.createHorizontalGlue());

        btmPnl.add(Box.createHorizontalGlue());
        btmPnl.add(fortuneBtn);
        btmPnl.add(Box.createHorizontalGlue());
        btmPnl.add(quitBtn);
        btmPnl.add(Box.createHorizontalGlue());
    }

    /**
     * Configures the two buttons and adds anonymous ActionListeners to them.
     */
    private void initButtons() {

        fortuneBtn.setAlignmentX(JButton.LEFT_ALIGNMENT);
        fortuneBtn.setMargin(new Insets(5, 5, 5, 5));
        fortuneBtn.setFont(btnFont);

        fortuneBtn.addActionListener(e -> textArea.append('~' + tellFortune() + '\n'));

        quitBtn.setAlignmentX(JButton.RIGHT_ALIGNMENT);
        quitBtn.setMargin(new Insets(5, 5, 5, 5));
        quitBtn.setFont(btnFont);

        quitBtn.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
    }

    /**
     * Creates ArrayList with 10 fortunes.
     * @return ArrayList of fortunes
     */
    private static ArrayList<String> createFortuneList() {
        return new ArrayList<>(Arrays.asList(
                "You will stumble upon a hidden treasure map.",
                "The road to riches is paved with homework.",
                "Respect your elders. You could inherit a large sum of money.",
                "No fortune today, try again tomorrow.",
                "To be idle is to be foolish.",
                "Learn from your mistakes. Try not to make them again.",
                "You may want to run, but you should stay and fight.",
                "You think it's a secret, but they know.",
                "You are not illiterate.",
                "He who slithers along the ground is not always a foe. "
        ));
    }

    /**
     * Picks an index at random and gets the fortune at that index of the fortune list.
     * @return Fortune
     */
    private String tellFortune() {
        int i;
        do {
            i = ran.nextInt(fortunes.size());
        } while (i == lastIndex);
        lastIndex = i;
        return fortunes.get(i);
    }
}
