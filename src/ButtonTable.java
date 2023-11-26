import javax.swing.*;
import java.awt.*;

public class ButtonTable extends JPanel {
    private int size;
    public ButtonTable(int r, int c, SokobanMenu menu) {
        setLayout(new GridLayout(c,r));
        int size = r*c;
        for (Integer i=1; i <= size; i++) {
            JButton button = new JButton(i.toString());
            button.addActionListener(e -> {
                try {
                    menu.SokobanGameFrame(button.getText());
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            });
            add(button);
        }
        //fillWithButtons();
    }

    public void fillWithButtons() {
        for (Integer i=1; i <= size; i++) {
            add(new JButton(i.toString()));
        }
    }

    /*public void fillWithImages() {
        for (Integer i=1; i <= size; i++) {
            add(new JButton(i.toString()));
        }
    }*/

}

