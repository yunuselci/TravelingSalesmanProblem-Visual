package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<Points> pointsList = new ArrayList<>();
    static List<Points> originalPointsList = new ArrayList<>();
    static int mesafe =0;
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
                originalPointsList.add(points);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static void fileWrite() throws IOException {
        List<Point> list = new ArrayList<>();
        int x,y;
        for (int i = 0; i < route.size()-10; i++) {
            x = originalPointsList.get(route.get(i)).x;
            y = originalPointsList.get(route.get(i)).y;
            Point point = new Point(x,y);
            list.add(point);
        }




        FileWriter fw = new FileWriter("out.tsp");
        for (int i = 0; i < route.size()-10; i++) {
            fw.write(list.get(i).x + " " + list.get(i).y + "\n");
        }
        fw.close();


    }

    static void calculateDistance() {

        double distance;
        double nearest = Integer.MAX_VALUE;
        int city = 0;


        /*
	    dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));

         */
        for (int j = 0; j < pointsList.size(); j++) {
            for (int i = 1; i < pointsList.size(); i++) {
                distance = Math.sqrt((pointsList.get(i).x-pointsList.get(j).x)*(pointsList.get(i).x-pointsList.get(j).x)
                        + (pointsList.get(i).y-pointsList.get(j).y)*(pointsList.get(i).y-pointsList.get(j).y));
                if(distance < nearest) {
                    nearest = distance;
                    city = pointsList.get(i).cityNumber;
                    //route.add(pointsList.get(i).cityNumber);
                    //pointsList.remove(pointsList.get(i));
                    mesafe += nearest;

                }
            }
            route.add(city);
            pointsList.remove(j);
            nearest = Integer.MAX_VALUE;

        }


        //hmap.forEach((key, value) -> System.out.println(key + " " + value));

    }


    public static void main(String[] args) throws IOException {

        route.add(1); //starting city;
        fileRead();
        JFrame jFrame = new JFrame("Screen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        jFrame.add(screen);
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
        calculateDistance();
        for (Integer integer : route) {
            System.out.print(integer + "->");
        }
        System.out.println(" ");
        System.out.println(mesafe);
        fileWrite();

    }
}
