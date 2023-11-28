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
    private JPanel gamePanel;

    /**
     *
     * Létrehozza a játék képernyőjét és a főmenüt
     *
     */
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
        scoreboardButton.addActionListener(e -> SokobanScoreboardSelect());
        quiteButton.addActionListener(e -> System.exit(0));


        setResizable(false);
        screenPanel.add(menuPanel, "Menu");
        cl.show(screenPanel,"Menu");
        add(screenPanel);



        setLocationRelativeTo(null);
        pack();
        this.setVisible(true);
    }


    /**
     *
     * Létrehozza a térképválasztó felületet, és megejeleníti azt
     *
     */
    public void SokobanMapSelect () {
        setLayout(new FlowLayout());
        JPanel mapsPanel = new JPanel();
        JPanel buttonPanel = new ButtonTable(4,10, this, true);
        JButton backToMenu = new JButton("Back");
        mapsPanel.add(buttonPanel);
        mapsPanel.add(backToMenu);
        backToMenu.addActionListener(e -> cl.show(screenPanel,"Menu"));
        buttonPanel.setAlignmentY(SwingConstants.CENTER);
        screenPanel.add(mapsPanel, "Table");
        cl.show(screenPanel, "Table");
        pack();

    }


    /**
     * Létrehozza és elindítja magát a játékot
     * @param filename A kiválasztott pálya neve
     */

    public void SokobanGameFrame (String filename) throws Exception {
        fileName = filename;
        map = new GameMap();

        map.loadMap(fileName);
        int rows = map.getRows(), columns = map.getColumns();

        gamePanel =  new JPanel(new GridLayout(rows,columns));
        mapControl = new MapControl(map,gamePanel);
        mapControl.drawMap();


        setFocusable(true);
        addKeyListener(new InputKeyListener());

        screenPanel.add(gamePanel, "Game");
        cl.show(screenPanel, "Game");
        pack();

    }


    /**
     *
     * Létrehozza a ScoreBoard kiválasztó képernyőt
     *
     */
    public void SokobanScoreboardSelect () {
        JPanel scorePanel = new JPanel();
        JPanel buttonPanel = new ButtonTable(4,10, this, false);
        JButton backToMenu = new JButton("Back");
        scorePanel.add(buttonPanel);
        scorePanel.add(backToMenu);
        backToMenu.addActionListener(e -> cl.show(screenPanel,"Menu"));
        buttonPanel.setAlignmentY(SwingConstants.CENTER);
        screenPanel.add(scorePanel, "ScoreBoardSelect");
        cl.show(screenPanel, "ScoreBoardSelect");
        pack();

    }


    /**
     * Megjenelníti a scoreboardot
     * @param fileName A kiválasztott file neve
     */
    public void SokobanScoreboard (String fileName) throws Exception {
        JPanel scorePanel = new JPanel();
        Scoreboard scoreboard = new Scoreboard("src/scoreboards/" + fileName + ".txt");
        JTable scoreTable = new JTable(scoreboard);
        JScrollPane board = new JScrollPane(scoreTable);
        JButton backToMenu = new JButton("Back");
        backToMenu.addActionListener(e -> cl.show(screenPanel,"Menu"));
        scorePanel.add(board);
        scorePanel.add(backToMenu);
        screenPanel.add(scorePanel, "ScorePanel");
        cl.show(screenPanel,"ScorePanel");
        pack();

    }


    /**
     *
     * A térképen való mozgást kezeli
     *
     */
    public class InputKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            int i = 0;

            if (e.getKeyChar() == 27) {
                screenPanel.remove(gamePanel);
                cl.show(screenPanel,"Menu");
                removeKeyListener(this);
            } else {
                try {
                    i = mapControl.moveCharacter(e.getKeyChar());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                if (i == 0) { // Lose lett
                    JPanel losePanel = new JPanel();
                    JLabel loseLabel = new JLabel("Lost!");
                    JButton backButton = new JButton("Back to menu");
                    backButton.addActionListener(e1 -> {
                        screenPanel.remove(gamePanel);
                        cl.show(screenPanel, "Menu");
                        pack();
                    });
                    removeKeyListener(this);
                    losePanel.add(loseLabel);
                    losePanel.add(backButton);
                    screenPanel.add(losePanel, "Lost");
                    cl.show(screenPanel, "Lost");


                } else if (i == 1) { //Win lett
                    JPanel winPanel = new JPanel();
                    JLabel winLabel = new JLabel("Win!");
                    JButton backButton = new JButton("Continue");
                    JLabel getNev = new JLabel("Input the name:");
                    JTextField name = new JTextField(30);
                    Scoreboard scoreboard;
                    try {
                        scoreboard = new Scoreboard("src/scoreboards/"+ map.getFileName() +".txt");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    backButton.addActionListener(e1 -> {
                        if (!name.getText().isEmpty()) {
                            try {
                                scoreboard.add(name.getText(),map.getMoves());
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            screenPanel.remove(gamePanel);
                            cl.show(screenPanel, "Menu");
                            pack();
                        }
                    });
                    removeKeyListener(this);
                    winPanel.add(winLabel);
                    winPanel.add(backButton);
                    winPanel.add(getNev);
                    winPanel.add(name);
                    screenPanel.add(winPanel, "Win");
                    cl.show(screenPanel, "Win");
                }
            }
            pack();
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }



}
