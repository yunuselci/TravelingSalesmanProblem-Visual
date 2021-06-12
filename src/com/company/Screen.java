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

        for (int i = 0; i < Main.list1.size(); i++) {
            if (Main.list1.get(i) == 0) {
                g.setColor(Color.BLUE);
            } else {
                g.fillOval(Main.nearestNeighborPointList.get((Main.list1.get(i)) - 1).y / 100, (Main.nearestNeighborPointList.get((Main.list1.get(i)) - 1).x / 100), 5, 5);
//                g.drawString(String.valueOf(Main.nearestNeighborPointList.get((Main.list1.get(i)) - 1).cityNumber),
//                        (Main.nearestNeighborPointList.get((Main.list1.get(i)) - 1).y / 100),
//                        Main.nearestNeighborPointList.get((Main.list1.get(i)) - 1).x / 100);
//
            }

        }


//        g.setColor(Color.BLUE);
//        for (int i = 0; i < Main.originalPointsList.size(); i++) {
//
//            g.fillOval(Main.originalPointsList.get(i).y /100,(Main.originalPointsList.get(i).x / 100), 5, 5);
//
//        }


//        g.setColor(Color.BLACK);


//        for (int j = 0; j < Main.routeForNearestNeighbor.size()-2; j++) {
//            g.drawLine((Main.originalPointsList.get(Main.routeForNearestNeighbor.get(j)-1).y )/100,
//                    ((Main.originalPointsList.get(Main.routeForNearestNeighbor.get(j)-1).x )/100),
//                    (Main.originalPointsList.get(Main.routeForNearestNeighbor.get(j+1)-1).y ) /100,
//                   ((Main.originalPointsList.get(Main.routeForNearestNeighbor.get(j+1)-1).x )/100));
//        }
        g.scale(-1.0, -1.0);
        g.translate(-1920, -1080);
        g.drawString("Total Length: " + Main.totalLenght, 1000, 100);


    }

}
