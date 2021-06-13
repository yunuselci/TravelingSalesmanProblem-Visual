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

    static List<Points> leftSide = new ArrayList<>();
    static int leftCounter = 0;
    static List<Points> rightSide = new ArrayList<>();
    static int rightCounter = 0;
    static List<Integer> rotaForLeft = new ArrayList<>();
    static List<Integer> rotaForRight = new ArrayList<>();
    static List<Integer> genelrota = new ArrayList<>();
    static List<Points> greedyRoute = new ArrayList<>();


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
        int b = 87000;
        for (int i = 0; i < originalPointsList.size(); i++) {
            if (originalPointsList.get(i).y <= b) {
                rightSide.add(originalPointsList.get(i));
                rightCounter++;
                leftSide.add(MaxPoint);
            }
            if (originalPointsList.get(i).y > b) {

                leftSide.add(originalPointsList.get(i));
                leftCounter++;
                rightSide.add(MaxPoint);
            }

        }

    }

    static void greedyRouteCreator(List<Integer> integerList, String name) {
        int cityNumber, x = 0, y = 0;
        cityNumber = integerList.get(0);
        for (Points points : originalPointsList) {
            if (points.cityNumber == cityNumber) {
                x = points.x;
                y = points.y;
            }
        }
        Points points = new Points(name, cityNumber, x, y);
        greedyRoute.add(points);
        cityNumber = integerList.get(integerList.size() - 1);
        for (Points p : originalPointsList) {
            if (p.cityNumber == cityNumber) {
                x = p.x;
                y = p.y;
            }
        }
        Points points1 = new Points(name, cityNumber, x, y);
        greedyRoute.add(points1);
    }

    static void greedy() {
        greedyRouteCreator(rotaForLeft, "leftSide");
        greedyRouteCreator(rotaForRight, "rightSide");

        double x1, x2, y1, y2, distance=0, nearest = Double.MAX_VALUE;
        int cityNumber=0,cityNumber1=0;


        for (int j = 0; j < 2; j++) {
            x1 = greedyRoute.get(j).x;
            y1 = greedyRoute.get(j).y;
            for (int i = 2; i < 4; i++) {
                x2 = greedyRoute.get(i).x;
                y2 = greedyRoute.get(i).y;
                distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                if (distance < nearest) {
                    nearest = distance;
                    cityNumber = greedyRoute.get(i).cityNumber;
                    cityNumber1 = greedyRoute.get(j).cityNumber;
                    System.out.println(greedyRoute.get(i).cityNumber);
                    System.out.println(greedyRoute.get(j).cityNumber);
                    if(i == 2 && j ==0){// başlangıç noktaları bir birine en yakınsa
                        genelrota.clear();
                        Collections.reverse(rotaForLeft);
                        genelrota.addAll(rotaForLeft);
                        genelrota.addAll(rotaForRight);
                        Collections.reverse(rotaForLeft);
                    }else if(j==0 && i==3){ // left side'ın başlangıç noktasıyla right side'ın bitiş noktası
                        genelrota.clear();
                        genelrota.addAll(rotaForRight);
                        genelrota.addAll(rotaForLeft);
                    }else if(j==1 && i==2){// left side'ın bitiş noktasıyla right side'ın başlangıç noktası
                        genelrota.clear();
                        genelrota.addAll(rotaForLeft);
                        genelrota.addAll(rotaForRight);
                    }else if(j==1 && i==3){ // ikisinin de bitiş noktası
                        genelrota.clear();
                        genelrota.addAll(rotaForLeft);
                        Collections.reverse(rotaForRight);
                        genelrota.addAll(rotaForRight);
                        Collections.reverse(rotaForRight);

                    }
                }
            }

            nearest = Integer.MAX_VALUE;
        }

        mesafe += distance;
        for (int i = 0; i < greedyRoute.size(); i++) {
            System.out.println(greedyRoute.get(i));
        }



//LEFT
//4456
//4365
//MID
//4547
//4294
//RIGHT
//4663
//4154

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


        fileRead();
        divideAndConq();

        //LEFT
//4456
//4365
//MID
//4547
//4294
//RIGHT
//4663
//4154

        nearestNeighbor(leftSide, rotaForLeft, 4456, leftCounter);
        nearestNeighbor(rightSide, rotaForRight, 4663, rightCounter);
        greedy();



//
//        List<Integer> sifirolmayanlar = new ArrayList<>();
//        List<Integer> bestPoint = new ArrayList<>();
//        for (Points points : leftSide) {
//            if (points.x != 0) {
//                sifirolmayanlar.add(points.cityNumber);
//            }
//        }
//        mesafe = 0;
//        leftCounter = 0;
//        rotaForLeft.clear();
//        leftSide.clear();
//        originalPointsList.clear();
//        pointsList.clear();
//
//        for (int k = 0; k < sifirolmayanlar.size(); k++) {
//            fileRead();
//            divideAndConq();
//            nearestNeighbor(midLower, rotaForMidLower, sifirolmayanlar.get(k), midLowerCounter);
//            bestPoint.add(mesafe);
//            mesafe = 0;
//            leftCounter = 0;
//            rotaForLeft.clear();
//            leftSide.clear();
//            originalPointsList.clear();
//            pointsList.clear();
//        }
//        System.out.println("   en iyi mesafe : " + Collections.min(bestPoint) + " en iyi mesafenin indexi :" + bestPoint.indexOf(Collections.min(bestPoint)));
//        System.out.println(sifirolmayanlar.get(bestPoint.indexOf(Collections.min(bestPoint))));
//
//        for (int i = 0; i < rotaForLeft.size(); i++) {
//            System.out.println(rotaForLeft.get(i));
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