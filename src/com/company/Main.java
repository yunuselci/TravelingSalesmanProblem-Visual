package com.company;

import com.sun.javaws.IconUtil;
import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<Points> pointsList = new ArrayList<>();
    static List<Points> originalPointsList = new ArrayList<>();
    static List<Integer> rota = new ArrayList<Integer>();
    static int a = 0;
    static Points MaxPoint = new Points(0, 0, 0);
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
    static void calculateDistance() {
        double distance = 0;
        double nearest = Integer.MAX_VALUE;
        double toplammesafe = 0;
        rota.add(1);
        /*
     dis=Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
         */
        for (int j = 0; j < pointsList.size(); j++) {
            double x1 = pointsList.get((rota.get(j)) - 1).x;/*başlangıç  noktası*/
//            System.out.println("başlangıç noktasının şehir numarası " + rota.get(j));
            double y1 = pointsList.get((rota.get(j)) - 1).y;
//            System.out.println("başlangıç noktasının indexi " + ((rota.get(j)) - 1));
//            System.out.println("silinen şehir" + pointsList.get(rota.get(j) - 1).cityNumber);
            pointsList.remove((rota.get(j)) - 1);
            pointsList.add((rota.get(j) - 1), MaxPoint);
//            System.out.println("point array" + pointsList.get(0));
//            System.out.println("point array" + pointsList.get(1));
            for (int i = 0; i < pointsList.size(); i++) {
                double x2 = pointsList.get(i).x;
                double y2 = pointsList.get(i).y;
                distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                if (distance < nearest) {
                    nearest = distance;
//                    System.out.println("hedef nokta veya yeni başlangıç noktası " + pointsList.get(i).cityNumber);
                    a = pointsList.get(i).cityNumber;
//                    System.out.println("-------------");
                    /*System.out.println("x2 : " + pointsList.get(i).cityNumber + " mesafe: " + nearest);*/
                    toplammesafe = nearest + toplammesafe;
                    /* System.out.println(toplammesafe + " nokta " + rota.get(i));*/
                }
            }
            rota.add(a);
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
    }
}