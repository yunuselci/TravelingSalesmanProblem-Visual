package com.company;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<Points> pointsList = new ArrayList<>();
    static int cityNumber=0;
    static List<Integer> route = new ArrayList<>();

    static void fileRead() {
        try {
            Scanner scanner = new Scanner(Main.tspFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] details = line.split(" ");
                int cityNumber = Integer.parseInt(details[0]);
                int x = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(details[1]))));
                int y = Integer.parseInt(String.valueOf(Math.round(Float.parseFloat(details[2]))));
                Points points = new Points(cityNumber, x, y);
                pointsList.add(points);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void calculateDistance() {

        double distance = 0.0;
        double nearest = Integer.MAX_VALUE;

        /*
	    dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));

         */
        for (int j = 0; j < pointsList.size(); j++) {
            for (int i = 1; i < pointsList.size(); i++) {
                distance = Math.sqrt((pointsList.get(i).x-pointsList.get(j).x)*(pointsList.get(i).x-pointsList.get(j).x)
                        + (pointsList.get(i).y-pointsList.get(j).y)*(pointsList.get(i).y-pointsList.get(j).y));
                if(distance < nearest) {
                    nearest = distance;
                    route.add(pointsList.get(i).cityNumber);
                    pointsList.remove(pointsList.get(i));
                }
            }
            nearest = Integer.MAX_VALUE;
        }


        for (Integer integer : route) {
            System.out.println(integer);
        }
        //hmap.forEach((key, value) -> System.out.println(key + " " + value));

    }


    public static void main(String[] args) {
        fileRead();
        JFrame jFrame = new JFrame("Screen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        jFrame.add(screen);
        jFrame.setSize(1920, 1080);
        //jFrame.setVisible(true);
        calculateDistance();

    }
}
