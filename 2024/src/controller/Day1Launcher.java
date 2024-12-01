package src.controller;

import src.service.FileService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1Launcher {

    public static List<Integer> leftList = new ArrayList<>();
    public static List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> inputLines = FileService.readFileLines("AdventOfCode/2024/resources/day1puzzle1.txt");

        for (String inputLine : inputLines) {
            String string[] = inputLine.split(" ");
            leftList.add(Integer.parseInt(string[0]));
            rightList.add(Integer.parseInt(string[3]));
        }
        System.out.println(computeTotalDistance(leftList, rightList));
    }


    public static Long computeTotalDistance(List<Integer> leftList, List<Integer> rightList) {

        Collections.sort(leftList);
        Collections.sort(rightList);

        long totalDistance = 0L;
        for (int index = 0; index < leftList.size(); index++) {
            totalDistance += Math.abs(rightList.get(index) - leftList.get(index));
        }

        return totalDistance;
    }
}
