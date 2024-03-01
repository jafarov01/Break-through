package src;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

public class Board extends Menu {

    private final int size;
    private final Logic logic;
    private final JLabel label;
    private final Game breakThrough;
    private int current_row;
    private int current_col;
    private int counter;
    private final JButton[][] buttons;

    public Board(int size, Game breakThrough) {
        this.size = size;
        this.breakThrough = breakThrough;
        logic = new Logic(size);
        buttons = new JButton[size][size];

        JPanel top = new JPanel();
        label = new JLabel();
        updateGameInfo();

        JButton newGameButton = new JButton();
        newGameButton.setText("New game");
        newGameButton.setForeground(Color.red);
        newGameButton.addActionListener(e -> startNewGame());

        top.add(label);
        top.add(newGameButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                addButton(mainPanel, i, j, size);
            }
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(top, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
    }

    private void addButton(JPanel panel, final int i, final int j, final int size) {
        final JButton button = new JButton();
        buttons[i][j] = button;
        if (i == 0 || i == 1) {
            button.setBackground(Color.black);
        } else if (i == size - 1 || i == size - 2) {
            button.setBackground(Color.white);
        } else {
            button.setBackground(Color.gray);
        }
        buttons[i][j].setBorder(new LineBorder(Color.BLUE));

        button.addActionListener(e -> {
            if (counter == 1) {
                buttons[current_row][current_col].setBorder(new LineBorder(Color.BLUE));
                logic.move(current_row, current_col, i, j);
                updateGameInfo();
                counter = 0;
                FieldType[][] modelBoard = logic.getBoard();

                // render buttons
                for (int row = 0; row < modelBoard.length; row++) {
                    for (int col = 0; col < modelBoard.length; col++) {
                        FieldType fieldToMove = modelBoard[row][col];
                        JButton buttonToMove = buttons[row][col];

                        if (fieldToMove == FieldType.WHITE) {
                            buttonToMove.setBackground(Color.white);
                        } else if (fieldToMove == FieldType.BLACK) {
                            buttonToMove.setBackground(Color.black);
                        } else {
                            buttonToMove.setBackground(Color.gray);
                        }
                    }
                }
                showGameEndMessage(logic.checkWinner());
            } else {
                FieldType currentField = logic.getFieldType(i, j);
                if ((logic.getCurrentPlayer() == Player.WHITE && currentField == FieldType.WHITE) ||
                        (logic.getCurrentPlayer() == Player.BLACK && currentField == FieldType.BLACK)) {
                    counter++;
                    current_col = j;
                    current_row = i;
                    buttons[i][j].setBorder(new LineBorder(Color.RED));
                }
            }
        });

        panel.add(button);
    }

    void showGameEndMessage(Player winner) {
        if (winner != null) {
            JOptionPane.showMessageDialog(this,
                    winner.name() + " WON THE GAME");
            whenExitted();
            breakThrough.setVisible(true);
        }
    }

    private void startNewGame() {
        Board newWindow = new Board(size, breakThrough);
        newWindow.setVisible(true);
        this.dispose();
    }

    private void updateGameInfo() {
        label.setText("Current player: "
                + logic.getCurrentPlayer().name());
        label.setForeground(Color.red);
    }

    @Override
    protected void whenExitted() {
        super.whenExitted();
    }
}
