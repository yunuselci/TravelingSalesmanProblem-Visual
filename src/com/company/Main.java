package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

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
        int b = 96825;    // x ler ortalaması 49931.48181919371 , y ler ortalaması 90521.19772648503,x e göre orta nokta 62141.66665,y ye göre orta nokta 96825.0
        for (int i = 0; i < originalPointsList.size(); i++) {
            if (originalPointsList.get(i).y <= b) {
                rightSide.add(originalPointsList.get(i));
                rightCounter++;
                visualRightSide.add(originalPointsList.get(i));
                leftSide.add(MaxPoint);
            }
            if (originalPointsList.get(i).y > b) {
                leftSide.add(originalPointsList.get(i));
                leftCounter++;
                visualLeftSide.add(originalPointsList.get(i));
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
        double x1, x2, y1, y2, distance = 0, nearest = Double.MAX_VALUE;
        for (int j = 0; j < 2; j++) {
            x1 = greedyRoute.get(j).x;
            y1 = greedyRoute.get(j).y;
            for (int i = 0; i < rightSide.size(); i++) {
                x2 = rightSide.get(i).x;
                y2 = rightSide.get(i).y;
                distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                if (distance < nearest) {
                    nearest = distance;
                    secondsstartpoint = rightSide.get(i).cityNumber;
                    if (j == 0) {// başlangıç noktaları bir birine en yakınsa
                        genelrota.clear();
                        Collections.reverse(rotaForLeft);
                        genelrota.addAll(rotaForLeft);

                        Collections.reverse(rotaForLeft);
                    } else if (j == 1) {// left side'ın bitiş noktasıyla right side'ın başlangıç noktası
                        genelrota.clear();
                        genelrota.addAll(rotaForLeft);

                    }
                }
            }
            nearest = Integer.MAX_VALUE;
        }
        mesafe += distance;

    }

    static void nearestNeighbor(List<Points> pList, List<Integer> route, int startingPoint, int cntr) {
        double distance;
        double nearest = Integer.MAX_VALUE;
        double x1, y1, x2, y2;
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

    public static void main(String[] args) throws Exception {


        System.out.println("Methods");
        System.out.println("1) Nearest Neighbor For Base Point");
        System.out.println("2) Divide and Conq. + Greedy. + Nearest Neighbor");
        System.out.println("Select Method To Show: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        method = Integer.parseInt(s);
        System.out.println("You selected method: " + method);
        if (method == 1) {
            fileRead();
            nearestNeighbor(pointsList, genelrota, 1700, pointsList.size());
        }
        if (method == 2) {
            fileRead();
            divideAndConq();
            nearestNeighbor(leftSide, rotaForLeft, 2690, leftCounter);
            greedy();
            nearestNeighbor(rightSide, rotaForRight, secondsstartpoint, rightCounter);
            genelrota.addAll(rotaForRight);
            System.out.println(mesafe);
        }

        JFrame jFrame = new JFrame("Screen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        jFrame.add(screen);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);

    }

    //Variables
    static List<Points> visualLeftSide = new ArrayList<>();//visualization
    static List<Points> visualRightSide = new ArrayList<>();
    static int method;
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<Points> pointsList = new ArrayList<>();
    static List<Points> originalPointsList = new ArrayList<>();
    static int mesafe = 0;
    static int cityNumber = 0;
    static int secondsstartpoint = 0;
    static Points MaxPoint = new Points(0, 0, 0);
    static List<Points> leftSide = new ArrayList<>();
    static int leftCounter = 0;
    static List<Points> rightSide = new ArrayList<>();
    static int rightCounter = 0;
    static List<Integer> rotaForLeft = new ArrayList<>();
    static List<Integer> rotaForRight = new ArrayList<>();
    static List<Integer> genelrota = new ArrayList<>();
    static List<Points> greedyRoute = new ArrayList<>();
}

