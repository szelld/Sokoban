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



    public SokobanMenu() {
        JPanel menuPanel = new JPanel();
        JLabel sokobanLabel = new JLabel("Sokoban");
        JButton startButton = new JButton("Start");
        JButton scoreboardButton = new JButton("Scoreboard");
        JButton quiteButton = new JButton("Quit");

        setSize(700,700);
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.Y_AXIS));
        menuPanel.add(sokobanLabel);
        menuPanel.add(startButton);
        menuPanel.add(scoreboardButton);
        menuPanel.add(quiteButton);

        startButton.addActionListener(e -> {
            try { //TODO: Valamit kell kezdeni ezzel a dologga
                SokobanGameFrame();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
        scoreboardButton.addActionListener(e -> SokobanMapSelect());
        quiteButton.addActionListener(e -> System.exit(0));

        //screenPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        screenPanel.add(menuPanel, "Menu");

        cl.show(screenPanel,"Menu");
        add(screenPanel);



        setLocationRelativeTo(null);
        pack();
        this.setVisible(true);
    }

    public void SokobanMapSelect () {
        JPanel mapsPanel = new JPanel();
        JPanel buttonPanel = new ButtonTable(4,10);
        JButton backToMenu = new JButton("Back");
        mapsPanel.add(buttonPanel);
        mapsPanel.add(backToMenu);
        backToMenu.addActionListener(e -> cl.show(screenPanel,"Menu"));
        buttonPanel.setAlignmentY(SwingConstants.CENTER);
        screenPanel.add(mapsPanel, "Table");
        //add(backToMenu, BorderLayout.EAST);
        cl.show(screenPanel, "Table");
        pack();

    }


    public void SokobanGameFrame () throws Exception {
        //JLayeredPane mapPane = new JLayeredPane();
        map = new GameMap();

        map.loadMap("src/test.txt");
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
            mapControl.moveCharacter(e.getKeyChar());
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }



}
