package com.company;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<DoublePoints> pointsList = new ArrayList<>();
    static List<Points> originalPointsList = new ArrayList<>();
    static List<Integer> rota = new ArrayList<>();
    static int mesafe = 0;
    static int cityNumber = 0;
    static DoublePoints MaxPoint = new DoublePoints(0, 0, 0);

    static void fileRead() {
        try {
            Scanner scanner = new Scanner(Main.tspFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(" ");
                int cityNumber = Integer.parseInt(details[0]);
                int x = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(details[1]))));
                double x1 = Double.parseDouble(details[1]);
                int y = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(details[2]))));
                double y1 = Double.parseDouble(details[2]);
                Points points = new Points(cityNumber, x, y);
                DoublePoints doublePoints = new DoublePoints(cityNumber, x1, y1);
                pointsList.add(doublePoints);
                originalPointsList.add(points);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void calculateDistance() {
        double distance;
        double nearest = Integer.MAX_VALUE;


        rota.add(1);
        for (int j = 0; j < pointsList.size() - 1; j++) {
            double x1 = pointsList.get((rota.get(j)) - 1).x;
            double y1 = pointsList.get((rota.get(j)) - 1).y;
            pointsList.remove((rota.get(j)) - 1);
            pointsList.add((rota.get(j) - 1), MaxPoint);
            for (int i = 0; i < pointsList.size(); i++) {
                double x2 = pointsList.get(i).x;
                double y2 = pointsList.get(i).y;
                distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                if (distance < nearest) {
                    nearest = distance;
                    cityNumber = pointsList.get(i).cityNumber;

                }
            }
            mesafe += nearest;
            rota.add(cityNumber);
            nearest = Integer.MAX_VALUE;
        }


    }

    public static void main(String[] args) {
        fileRead();
        JFrame jFrame = new JFrame("Screen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        jFrame.add(screen);
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        calculateDistance();
        System.out.println("Total Length: " + mesafe);
        for (Integer integer : rota) {
            System.out.print(integer + "->");
        }

    }
}