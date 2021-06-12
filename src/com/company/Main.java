package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;

public class Main {
    final static File tspFile = new File("C:\\Users\\YUNUS\\IdeaProjects\\Cmp\\src\\com\\company\\ca4663.tsp");
    static List<Points> nearestNeighborPointList = new ArrayList<>();
    static List<Points> originalPointsList = new ArrayList<>();
    static List<Integer> routeForNearestNeighbor = new ArrayList<>();
    static List<Integer> list1 = new ArrayList<>();
    static HashMap<Integer, Double> hashMap = new HashMap<>();

    static int totalLenght = 0;
    static int cityNumber = 0;
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
                nearestNeighborPointList.add(points);
                originalPointsList.add(points);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void divideAndConq() {
        for (int k = 1; k < 3; k++) {
            double onetoall = 0;
            double x1, x2, y1, y2;
            double distance;
            hashMap.clear();
            for (int j = 0; j < originalPointsList.size(); j++) {
                if (originalPointsList.get(j).x != 0) {
                    x1 = originalPointsList.get(j).x;
                    y1 = originalPointsList.get(j).y;
                    for (int i = 0; i < originalPointsList.size(); i++) {
                        if (originalPointsList.get(i).x != 0) {
                            x2 = originalPointsList.get(i).x;
                            y2 = originalPointsList.get(i).y;
                            distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                            onetoall = onetoall + distance;
                        }
                    }
                    hashMap.put(j, (onetoall / originalPointsList.size()));
                    onetoall = 0;
                }
            }

            Map<Integer, Double> hm1 = sortByValue(hashMap);

            int tempCounter = 0;
            for (Map.Entry<Integer, Double> en : hm1.entrySet()) {//4662
                tempCounter++;
                if (tempCounter <= (originalPointsList.size() / 2*k)) {
                    list1.add((en.getKey() + 1));
                    int temp = en.getKey();
                    originalPointsList.remove(temp);
                    originalPointsList.add(en.getKey(), MaxPoint);
                }
//            System.out.println("Bekayin Sayisi:" + tempCounter +"  Key = " + (en.getKey() + 1) +
//                    ", Value = " + en.getValue());

            }
            hm1.clear();
            list1.add(0);


        }

    }


    //Nearest Neighbor Algorithm
    static void calculateRouteBasedOnNearPoints(List<Points> pList, List<Integer> route, int startingPoint) {
        double distance;
        double nearest = Integer.MAX_VALUE;
        totalLenght = 0;
        cityNumber = 0;
        route.add(startingPoint);
        for (int j = 0; j < pList.size() - 1; j++) {
            double x1 = pList.get((route.get(j)) - 1).x;
            double y1 = pList.get((route.get(j)) - 1).y;
            pList.remove((route.get(j)) - 1);
            pList.add((route.get(j) - 1), MaxPoint);
            for (Points points : pList) {
                double x2 = points.x;
                double y2 = points.y;
                distance = Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
                if (distance < nearest) {
                    nearest = distance;
                    cityNumber = points.cityNumber;

                }
            }
            totalLenght += nearest;
            route.add(cityNumber);
            nearest = Integer.MAX_VALUE;
        }
    }


    public static HashMap<Integer, Double> sortByValue(HashMap<Integer, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Double>> list =
                new LinkedList<>(hm.entrySet());
        // Sort the list
        list.sort(Map.Entry.comparingByValue());
        // put data from sorted list to hashmap
        HashMap<Integer, Double> temp = new LinkedHashMap<>();
        for (Map.Entry<Integer, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

//    public static Set<Integer> findDuplicates(List<Integer> listContainingDuplicates)
//    {
//        final Set<Integer> setToReturn = new HashSet<>();
//        final Set<Integer> set1 = new HashSet<>();
//
//        for (Integer yourInt : listContainingDuplicates)
//        {
//            if (!set1.add(yourInt))
//            {
//                setToReturn.add(yourInt);
//            }
//        }
//        return setToReturn;
//    }
    public static void main(String[] args) {
        fileRead();

        divideAndConq();




// print the sorted hashmap
//        for (Map.Entry<Integer, Double> en : hm1.entrySet()) {
//            System.out.println("Key = " + en.getKey() +
//                    ", Value = " + en.getValue());
//        }

//        hashMap.forEach((key, value) -> System.out.println(key + " " + value));
//        calculateRouteBasedOnNearPoints(nearestNeighborPointList, routeForNearestNeighbor, 1700);
//
//
        JFrame jFrame = new JFrame("Screen");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Screen screen = new Screen();
        jFrame.add(screen);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setSize(1920, 1080);
        jFrame.setVisible(true);

//        System.out.println("Total Length: " + totalLenght);
//        for (Integer integer : routeForNearestNeighbor) {
//            System.out.print(integer + "->");
//        }

    }
}