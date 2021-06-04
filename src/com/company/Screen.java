package com.company;

import javax.swing.*;
import java.awt.*;

public class Screen extends JPanel {

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        this.setBackground(Color.WHITE);
        Graphics2D g = (Graphics2D) graphics;
        g.translate(0, 1080);
        g.scale(1.0, -1.0);

        g.setColor(Color.RED);



        for (int i = 0; i < Main.originalPointsList.size(); i++) {

            g.fillOval(Main.originalPointsList.get(i).y /100,(Main.originalPointsList.get(i).x / 100), 5, 5);

        }


        g.setColor(Color.BLACK);


        for (int j = 0; j < Main.rota.size()-2; j++) {
            g.drawLine((Main.originalPointsList.get(Main.rota.get(j)-1).y )/100,
                    ((Main.originalPointsList.get(Main.rota.get(j)-1).x )/100),
                    (Main.originalPointsList.get(Main.rota.get(j+1)-1).y ) /100,
                   ((Main.originalPointsList.get(Main.rota.get(j+1)-1).x )/100));
        }
        g.scale(1.0, -1.0);
        g.translate(0, -1080);
        g.drawString("Total Length: "+ Main.mesafe, 1000,100);


    }

}
