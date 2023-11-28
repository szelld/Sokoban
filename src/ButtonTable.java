import javax.swing.*;
import java.awt.*;

public class ButtonTable extends JPanel {
    private int size;


    /**
     * Léterhoz egy gomobok tábáláját
     * @param r sorok száma
     * @param c oszlopok száma
     * @param menu referencia a menühöz
     * @param isMap Megajda hogy a maphoz, kell-e (true) vagy a scoreboardhoz (false)
     */
    public ButtonTable(int r, int c, SokobanMenu menu, boolean isMap) {
        setLayout(new GridLayout(c,r));
        int size = r*c;
        for (Integer i=1; i <= size; i++) {
            JButton button = new JButton(i.toString());
            button.addActionListener(e -> {
                if (isMap) {
                    try {
                        menu.SokobanGameFrame(button.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        menu.SokobanScoreboard(button.getText());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            add(button);
        }
    }
}

