import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SokobanMenu extends JFrame {
    private CardLayout cl = new CardLayout();
    private JPanel screenPanel = new JPanel(cl);
    private GameMap map;
    private MapControl mapControl;
    private String fileName;


    public SokobanMenu() {
        JPanel menuPanel = new JPanel();
        JLabel sokobanLabel = new JLabel("Sokoban");
        JButton startButton = new JButton("Start");
        JButton scoreboardButton = new JButton("Scoreboard");
        JButton quiteButton = new JButton("Quit");

        setSize(700,700);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));
        menuPanel.add(sokobanLabel);
        menuPanel.add(startButton);
        menuPanel.add(scoreboardButton);
        menuPanel.add(quiteButton);


        startButton.addActionListener(e -> {SokobanMapSelect();});
        scoreboardButton.addActionListener(e -> SokobanMapSelect());
        quiteButton.addActionListener(e -> System.exit(0));



        screenPanel.add(menuPanel, "Menu");
        cl.show(screenPanel,"Menu");
        add(screenPanel);



        setLocationRelativeTo(null);
        pack();
        this.setVisible(true);
    }

    public void SokobanMapSelect () {
        setLayout(new FlowLayout());
        JPanel mapsPanel = new JPanel();
        JPanel buttonPanel = new ButtonTable(4,10, this);
        JButton backToMenu = new JButton("Back");
        mapsPanel.add(buttonPanel);
        mapsPanel.add(backToMenu);
        backToMenu.addActionListener(e -> cl.show(screenPanel,"Menu"));
        buttonPanel.setAlignmentY(SwingConstants.CENTER);
        screenPanel.add(mapsPanel, "Table");
        cl.show(screenPanel, "Table");
        pack();

    }


    public void SokobanGameFrame (String filename) throws Exception {
        fileName = filename;
        map = new GameMap();

        map.loadMap("src/"+fileName+".txt"); //TODO: Mapokat behúzni
        int rows = map.getRows(), columns = map.getColumns();

        JPanel gamePanel =  new JPanel(new GridLayout(rows,columns));
        mapControl = new MapControl(map,gamePanel);
        mapControl.drawMap();


        setFocusable(true);
        addKeyListener(new InputKeyListener());

        screenPanel.add(gamePanel, "Game");
        cl.show(screenPanel, "Game");
        pack();

    }

    public class InputKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
           int i = mapControl.moveCharacter(e.getKeyChar());
           if (i==0) {
               JPanel losePanel = new JPanel();
               JLabel loseLabel = new JLabel("Lost!");
               JButton backButton = new JButton("Back to menu");
               backButton.addActionListener(e1 -> {cl.show(screenPanel,"Menu");pack();});
               losePanel.add(loseLabel);
               losePanel.add(backButton);
               screenPanel.add(losePanel, "Win");
               cl.show(screenPanel, "Win");



           } else if (i==1) {
                JPanel winPanel = new JPanel();
                JLabel winLabel = new JLabel("Win!");
                JButton backButton = new JButton("Back to menu");
                backButton.addActionListener(e1 -> {cl.show(screenPanel,"Menu");pack();});
                winPanel.add(winLabel);
                winPanel.add(backButton);
                screenPanel.add(winPanel, "Win");
                cl.show(screenPanel, "Win");
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }



}
