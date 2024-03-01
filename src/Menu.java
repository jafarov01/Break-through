package src;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Menu extends JFrame{
    public Menu() {
        setTitle("Break Through");
        setSize(1000, 1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                whenExitted();
            }
        });

    }

    protected void whenExitted() {
        this.dispose();
    }
}