package com.company;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);
        g.setColor(Color.RED);


        for (int i = 0; i < Main.pointsList.size(); i++) {

            g.fillOval(Main.pointsList.get(i).y / 100, Main.pointsList.get(i).x / 100, 5, 5);
        }


        g.setColor(Color.BLACK);


        for (int j = 0; j < Main.pointsList.size()-1; j++) {


            //g.drawLine(Main.pointsList.get(j).y/100, Main.pointsList.get(j).x/100, Main.pointsList.get(j + 1).y/100, Main.pointsList.get(j + 1).x/100);
        }


    }
}
