package classes;

import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.*;

public class Scoreboard extends AbstractTableModel implements Serializable {
    private List<ScoreEntry> board;
    
    private String fileName;


    /**
     * Betölti a pálya scoreboardját
     * @param fileName A scoreboard file neve
     */
    public Scoreboard (String fileName) throws Exception {
        this.fileName = fileName;
        board = new ArrayList<>();
        FileInputStream f = new FileInputStream(fileName);
        ObjectInputStream fIn = new ObjectInputStream(f);
        try {
            board = (List<ScoreEntry>) fIn.readObject();
        } catch (Exception e) {}
        fIn.close();
    }


    /**
     * Hozzáad egy bejegyzést a scoreboardhoz
     * @param name A játékos neve
     * @param move A játékos lépésszáma
     */
    public void add (String name, int move) throws Exception {
        board.add(new ScoreEntry(name,move));
        board.sort((s0,s1) -> s0.getMove().compareTo(s1.getMove()));

        ObjectOutputStream fout = new ObjectOutputStream(new FileOutputStream(fileName));
        fout.writeObject(board);
        fout.close();
    }


    @Override
    public int getRowCount() {
        return board.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ScoreEntry entry = board.get(rowIndex);
        switch (columnIndex) {
            case 0: return entry.getName();
            default: return entry.getMove();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Név";
            default: return "Lépésszám";
        }
    }
}
