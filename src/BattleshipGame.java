import java.util.*;
import javax.swing.JOptionPane;

public class BattleshipGame {
    private Board board;
    private BattleshipGUI gui;

    private int missCounter;
    private int strikeCounter;
    private int totalMisses;
    private int totalHits;

    public static final int MAX_MISSES = 5;
    public static final int MAX_STRIKES = 3;
    public static final int TOTAL_SHIP_CELLS = 17;

    public BattleshipGame() {
        startNewGame();
    }

    public void startNewGame() {
        board = new Board();
        missCounter = 0;
        strikeCounter = 0;
        totalMisses = 0;
        totalHits = 0;
        board.placeShips();
        gui = new BattleshipGUI(this, board);
    }

    public void fireAt(int row, int col) {
        if (board.isHit(row, col)) {
            totalHits++;
            missCounter = 0;
            gui.updateCell(row, col, "HIT");

            Ship hitShip = board.getShipAt(row, col);
            if (hitShip != null && hitShip.isSunk()) {
                JOptionPane.showMessageDialog(gui, "You sunk a ship!");
            }

            if (board.allShipsSunk()) {
                JOptionPane.showMessageDialog(gui, "You win! All ships sunk!");
                askPlayAgain();
                return;
            }
        } else {
            missCounter++;
            totalMisses++;
            gui.updateCell(row, col, "MISS");

            if (missCounter == MAX_MISSES) {
                strikeCounter++;
                missCounter = 0;
            }

            if (strikeCounter == MAX_STRIKES) {
                JOptionPane.showMessageDialog(gui, "Game over! You lost!");
                askPlayAgain();
                return;
            }
        }

        gui.updateCounters(missCounter, strikeCounter, totalMisses, totalHits);
    }

    private void askPlayAgain() {
        int response = JOptionPane.showConfirmDialog(gui, "Play again?", "Battleship", JOptionPane.YES_NO_OPTION);

        gui.dispose();

        if (response == JOptionPane.YES_OPTION) {
            startNewGame();
        } else {
            System.exit(0);
        }
    }
}
