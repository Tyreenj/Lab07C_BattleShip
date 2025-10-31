import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipGUI extends JFrame {
    private JButton[][] buttons = new JButton[10][10];
    private JLabel missLabel, strikeLabel, totalMissLabel, totalHitLabel;
    private BattleshipGame game;
    private Board board;

    public BattleshipGUI(BattleshipGame game, Board board) {
        this.game = game;
        this.board = board;

        setTitle("Battleship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 60, 90));
        JLabel titleLabel = new JLabel("BATTLESHIP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titlePanel.add(titleLabel);

        JPanel gridPanel = new JPanel(new GridLayout(10, 10));
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                JButton btn = new JButton("~");
                btn.setBackground(Color.CYAN);
                btn.setForeground(Color.BLACK);
                int row = r, col = c;
                btn.addActionListener(e -> {
                    if (!board.getGrid()[row][col].isClicked()) {
                        board.getGrid()[row][col].setClicked(true);
                        game.fireAt(row, col);
                        btn.setEnabled(false);
                    }
                });
                buttons[r][c] = btn;
                gridPanel.add(btn);
            }
        }

        JPanel statusPanel = new JPanel(new GridLayout(2, 2));
        missLabel = new JLabel("Misses: 0");
        strikeLabel = new JLabel("Strikes: 0");
        totalMissLabel = new JLabel("Total Misses: 0");
        totalHitLabel = new JLabel("Total Hits: 0");
        statusPanel.add(missLabel);
        statusPanel.add(strikeLabel);
        statusPanel.add(totalMissLabel);
        statusPanel.add(totalHitLabel);

        JPanel controlPanel = new JPanel();
        JButton playAgainBtn = new JButton("Play Again");
        playAgainBtn.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Start new game?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                dispose();
                new BattleshipGame();
            }
        });

        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> System.exit(0));
        controlPanel.add(playAgainBtn);
        controlPanel.add(quitBtn);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusPanel, BorderLayout.NORTH);
        bottomPanel.add(controlPanel, BorderLayout.SOUTH);

        add(titlePanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    public void updateCell(int row, int col, String result) {
        JButton btn = buttons[row][col];
        btn.setFont(new Font("Arial", Font.BOLD, 7));

        if (result.equals("HIT")) {
            btn.setText("<html><font color='white'>X</font></html>");
            btn.setBackground(Color.RED);
        } else {
            btn.setText("<html><font color='white'>M</font></html>");
            btn.setBackground(Color.YELLOW);
        }

        btn.revalidate();
        btn.repaint();
    }

    public void updateCounters(int miss, int strikes, int totalMiss, int totalHit) {
        missLabel.setText("Misses: " + miss);
        strikeLabel.setText("Strikes: " + strikes);
        totalMissLabel.setText("Total Misses: " + totalMiss);
        totalHitLabel.setText("Total Hits: " + totalHit);
    }
}