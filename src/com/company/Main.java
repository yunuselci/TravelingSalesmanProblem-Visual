package com.company;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Main {
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<Points> pointsList = new ArrayList<>();
    static List<Points> originalPointsList = new ArrayList<>();
    static int mesafe = 0;
    static int cityNumber = 0;
    static Points MaxPoint = new Points(0, 0, 0);

    static List<Points> leftUpper = new ArrayList<>();
    static int leftUpperCounter = 0;
    static List<Points> leftLower = new ArrayList<>();
    static int leftLowerCounter = 0;
    static List<Points> midUpper = new ArrayList<>();
    static int midUpperCounter = 0;
    static List<Points> midLower = new ArrayList<>();
    static int midLowerCounter = 0;
    static List<Points> rightUpper = new ArrayList<>();
    static int rightUpperCounter = 0;
    static List<Points> rightLower = new ArrayList<>();
    static int rightLowerCounter = 0;
    static List<Integer> rotaForLeftUpper = new ArrayList<>();
    static List<Integer> rotaForLeftLower = new ArrayList<>();
    static List<Integer> rotaForMidUpper = new ArrayList<>();
    static List<Integer> rotaForMidLower = new ArrayList<>();
    static List<Integer> rotaForRightUpper = new ArrayList<>();
    static List<Integer> rotaForRightLower = new ArrayList<>();

    static List<Integer> sifirolmayanlar = new ArrayList<>();


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

    static void divideAndConq() {
        int a = 57133, b = 87000, c = 121300;
        for (int i = 0; i < originalPointsList.size(); i++) {
            if (originalPointsList.get(i).x >= a && originalPointsList.get(i).y <= b) {
                rightUpper.add(originalPointsList.get(i));
                rightUpperCounter++;
                rightLower.add(MaxPoint);
                midUpper.add(MaxPoint);
                midLower.add(MaxPoint);
                leftUpper.add(MaxPoint);
                leftLower.add(MaxPoint);
            }
            if (originalPointsList.get(i).x < a && originalPointsList.get(i).y <= b) {
                rightLower.add(originalPointsList.get(i));
                rightLowerCounter++;
                rightUpper.add(MaxPoint);
                midUpper.add(MaxPoint);
                midLower.add(MaxPoint);
                leftUpper.add(MaxPoint);
                leftLower.add(MaxPoint);
            }
            if (originalPointsList.get(i).x >= a && originalPointsList.get(i).y > b && originalPointsList.get(i).y < c) {
                midUpper.add(originalPointsList.get(i));
                midUpperCounter++;
                rightUpper.add(MaxPoint);
                rightLower.add(MaxPoint);
                midLower.add(MaxPoint);
                leftUpper.add(MaxPoint);
                leftLower.add(MaxPoint);
            }
            if (originalPointsList.get(i).x < a && originalPointsList.get(i).y > b && originalPointsList.get(i).y < c) {
                midLower.add(originalPointsList.get(i));
                midLowerCounter++;
                rightUpper.add(MaxPoint);
                rightLower.add(MaxPoint);
                midUpper.add(MaxPoint);
                leftUpper.add(MaxPoint);
                leftLower.add(MaxPoint);
            }
            if (originalPointsList.get(i).x >= a && originalPointsList.get(i).y >= c) {

                leftUpper.add(originalPointsList.get(i));
                leftUpperCounter++;
                rightUpper.add(MaxPoint);
                rightLower.add(MaxPoint);
                midUpper.add(MaxPoint);
                midLower.add(MaxPoint);
                leftLower.add(MaxPoint);
            }
            if (originalPointsList.get(i).x < a && originalPointsList.get(i).y >= c) {
                leftLower.add(originalPointsList.get(i));
                leftLowerCounter++;
                rightUpper.add(MaxPoint);
                rightLower.add(MaxPoint);
                midUpper.add(MaxPoint);
                midLower.add(MaxPoint);
                leftUpper.add(MaxPoint);
            }
        }

    }

    static void nearestNeighbor(List<Points> pList, List<Integer> route, int startingPoint, int cntr) {
        double distance;
        double nearest = Integer.MAX_VALUE;
        double x1, y1, x2, y2;


//        int startingPoint = 0;
//
//        for (Points points : pList) {
//            if (points.x != 0) {
//                sifirolmayanlar.add(points.cityNumber);
//                startingPoint = points.cityNumber;
//            }
//        }

        route.add(startingPoint);

        for (int j = 0; j < cntr - 1; j++) {

            x1 = pList.get((route.get(j)) - 1).x;
            y1 = pList.get((route.get(j)) - 1).y;
            pList.remove((route.get(j)) - 1);
            pList.add((route.get(j) - 1), MaxPoint);
            for (int i = 0; i < pList.size(); i++) {
                if (pList.get(i).x != 0) {
                    x2 = pList.get(i).x;
                    y2 = pList.get(i).y;
                    distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                    if (distance < nearest) {
                        nearest = distance;
                        cityNumber = pList.get(i).cityNumber;
                    }
                }


            }


            mesafe += nearest;

            if (cityNumber != 0) {
                route.add(cityNumber);
            }

            nearest = Integer.MAX_VALUE;


        }


    }

    public static void main(String[] args) {
        List<Integer> bestPoint = new ArrayList<>();

        fileRead();
        divideAndConq();
        nearestNeighbor(leftUpper, rotaForLeftUpper, 4456,  leftUpperCounter);
        nearestNeighbor(leftLower, rotaForLeftLower, 3020 ,  leftLowerCounter);
        nearestNeighbor(rightLower, rotaForRightLower, 507 ,  rightLowerCounter);
        nearestNeighbor(rightUpper, rotaForRightUpper, 4663 , rightUpperCounter);
        nearestNeighbor(midUpper, rotaForMidUpper, 4547, midUpperCounter);
        nearestNeighbor(midLower, rotaForMidLower, 2752, midLowerCounter);



//        for (Points points : midLower) {
//            if (points.x != 0) {
//                sifirolmayanlar.add(points.cityNumber);
//            }
//        }
//        mesafe = 0;
//        midLowerCounter = 0;
//        rotaForMidLower.clear();
//        midLower.clear();
//        originalPointsList.clear();
//        pointsList.clear();
//
//        for (int k = 0; k < sifirolmayanlar.size(); k++) {
//            fileRead();
//            divideAndConq();
//            nearestNeighbor(midLower, rotaForMidLower, sifirolmayanlar.get(k), midLowerCounter);
//            bestPoint.add(mesafe);
//            mesafe = 0;
//            midLowerCounter = 0;
//            rotaForMidLower.clear();
//            midLower.clear();
//            originalPointsList.clear();
//            pointsList.clear();
//        }
//        System.out.println("   en iyi mesafe : " + Collections.min(bestPoint) + " en iyi mesafenin indexi :" + bestPoint.indexOf(Collections.min(bestPoint)));
//        System.out.println(sifirolmayanlar.get(bestPoint.indexOf(Collections.min(bestPoint))));


//        nearestNeighbor(leftLower, rotaForLeftLower,leftLowerCounter);
//        nearestNeighbor(midUpper, rotaForMidUpper,midUpperCounter);
//        nearestNeighbor(midLower, rotaForMidLower,midLowerCounter);
//        nearestNeighbor(rightUpper, rotaForRightUpper,rightUpperCounter);
//        nearestNeighbor(rightLower, rotaForRightLower,rightLowerCounter);

//        for (int i = 0; i < rotaForLeftUpper.size(); i++) {
//            System.out.println(rotaForLeftUpper.get(i));
//        }


        JFrame jFrame = new JFrame("Screen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        jFrame.add(screen);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);
//        System.out.println("Total Length: " + mesafe);
//        for (Integer integer : rota) {
//            System.out.print(integer + "->");
//        }

    }
}