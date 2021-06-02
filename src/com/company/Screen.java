package com.company;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setColor(Color.RED);


        for (int i = 0; i < Main.originalPointsList.size(); i++) {

            g.fillOval(Main.originalPointsList.get(i).x / 100, Main.originalPointsList.get(i).y / 100, 5, 5);

        }


        g.setColor(Color.BLACK);


        for (int j = 0; j < Main.route.size()-2; j++) {
            g.drawLine((Main.originalPointsList.get(Main.route.get(j)).x )/100,
                    (Main.originalPointsList.get(Main.route.get(j)).y )/100,
                    (Main.originalPointsList.get(Main.route.get(j+1)).x ) /100,
                    (Main.originalPointsList.get(Main.route.get(j+1)).y) /100);
        }

    }
}
