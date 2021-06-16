package com.company;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(Color.WHITE);
        Graphics2D g = (Graphics2D) graphics;
        g.translate(1920, 1080);
        g.scale(-1.0, -1.0);

        g.setColor(Color.RED);


        if(Main.method==1){
            for (int i = 0; i < Main.originalPointsList.size(); i++) {

                g.fillOval(Main.originalPointsList.get(i).y / 100, (Main.originalPointsList.get(i).x / 100), 5, 5);

            }
        }


        if(Main.method == 2){
            g.setColor(Color.CYAN);
            for (int i = 0; i < Main.visualLeftSide.size(); i++) {

                g.fillOval(Main.visualLeftSide.get(i).y / 100, (Main.visualLeftSide.get(i).x / 100), 5, 5);

            }
            g.setColor(Color.MAGENTA);
            for (int i = 0; i < Main.visualRightSide.size(); i++) {

                g.fillOval(Main.visualRightSide.get(i).y / 100, (Main.visualRightSide.get(i).x / 100), 5, 5);

            }

        }

        g.setColor(Color.BLACK);


        for (int j = 0; j < Main.theTour.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.theTour.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.theTour.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.theTour.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.theTour.get(j + 1) - 1).x) / 100));
        }

        g.scale(-1.0, -1.0);
        g.translate(-1920, -1080);
        g.drawString("Total Length: " + Main.totalLenght, 1000, 100);


    }

}
