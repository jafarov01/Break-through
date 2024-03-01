package src;

import javax.swing.*;
import java.awt.event.ActionListener;

import java.awt.*;

public class Game extends JFrame{
    public Game() {
        JLabel gameLabel = new JLabel("BREAK THROUGH");
        gameLabel.setFont(new Font("Sans Serif", Font.BOLD, 25));
        gameLabel.setForeground(Color.red);

        JButton small = new JButton();
        small.setText("6 x 6");

        small.addActionListener(clickedMap(6));

        JButton medium = new JButton();
        medium.setText("8 x 8");

        medium.addActionListener(clickedMap(8));

        JButton large = new JButton();
        large.setText("10 x 10");

        large.addActionListener(clickedMap(10));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        getContentPane().add(gameLabel);
        getContentPane().add(small);
        getContentPane().add(medium);
        getContentPane().add(large);
        this.pack();
    }

    private ActionListener clickedMap(final int size) {
        return e -> {
            Board board = new Board(size, Game.this);
            this.setVisible(false);
            board.setVisible(true);
        };
    }
}
