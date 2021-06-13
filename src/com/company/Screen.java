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


//        for (int i = 0; i < Main.rightUpper.size(); i++) {
//
//            g.fillOval(Main.rightUpper.get(i).y /100,(Main.rightUpper.get(i).x / 100), 5, 5);
//
//        }
//        g.setColor(Color.BLUE);
//
//
//        for (int i = 0; i < Main.rightLower.size(); i++) {
//
//            g.fillOval(Main.rightLower.get(i).y /100,(Main.rightLower.get(i).x / 100), 5, 5);
//
//        }
//
//        g.setColor(Color.CYAN);
//
//
//        for (int i = 0; i < Main.midUpper.size(); i++) {
//
//            g.fillOval(Main.midUpper.get(i).y /100,(Main.midUpper.get(i).x / 100), 5, 5);
//
//        }
//        g.setColor(Color.YELLOW);
//
//
//        for (int i = 0; i < Main.midLower.size(); i++) {
//
//            g.fillOval(Main.midLower.get(i).y /100,(Main.midLower.get(i).x / 100), 5, 5);
//
//        }
//
//        g.setColor(Color.BLACK);
//
//
//        for (int i = 0; i < Main.leftUpper.size(); i++) {
//
//            g.fillOval(Main.leftUpper.get(i).y /100,(Main.leftUpper.get(i).x / 100), 5, 5);
//
//        }
//        g.setColor(Color.MAGENTA);
//
//
//        for (int i = 0; i < Main.leftLower.size(); i++) {
//
//            g.fillOval(Main.leftLower.get(i).y /100,(Main.leftLower.get(i).x / 100), 5, 5);
//
//        }


        for (int i = 0; i < Main.originalPointsList.size(); i++) {

            g.fillOval(Main.originalPointsList.get(i).y / 100, (Main.originalPointsList.get(i).x / 100), 5, 5);

        }


        g.setColor(Color.BLACK);


        for (int j = 0; j < Main.rotaForLeftUpper.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rotaForLeftUpper.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForLeftUpper.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.rotaForLeftUpper.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForLeftUpper.get(j + 1) - 1).x) / 100));
        }

        for (int j = 0; j < Main.rotaForLeftLower.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rotaForLeftLower.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForLeftLower.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.rotaForLeftLower.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForLeftLower.get(j + 1) - 1).x) / 100));
        }
        for (int j = 0; j < Main.rotaForRightUpper.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rotaForRightUpper.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForRightUpper.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.rotaForRightUpper.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForRightUpper.get(j + 1) - 1).x) / 100));
        }
        for (int j = 0; j < Main.rotaForRightLower.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rotaForRightLower.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForRightLower.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.rotaForRightLower.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForRightLower.get(j + 1) - 1).x) / 100));
        }
        for (int j = 0; j < Main.rotaForMidUpper.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rotaForMidUpper.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForMidUpper.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.rotaForMidUpper.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForMidUpper.get(j + 1) - 1).x) / 100));
        }
        for (int j = 0; j < Main.rotaForMidLower.size() - 1; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rotaForMidLower.get(j) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForMidLower.get(j) - 1).x) / 100),
                    (Main.originalPointsList.get(Main.rotaForMidLower.get(j + 1) - 1).y) / 100,
                    ((Main.originalPointsList.get(Main.rotaForMidLower.get(j + 1) - 1).x) / 100));
        }


        g.scale(-1.0, -1.0);
        g.translate(-1920, -1080);
        g.drawString("Total Length: " + Main.mesafe, 1000, 100);


    }

}
