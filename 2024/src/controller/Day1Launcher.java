package src.controller;

import src.service.FileService;

import java.util.*;

public class Day1Launcher {

    public static List<Integer> leftList = new ArrayList<>();
    public static List<Integer> rightList = new ArrayList<>();

    public static void main(String[] args) {
        List<String> inputLines = FileService.readFileLines("AdventOfCode/2024/resources/day1puzzle1.txt");

        for (String inputLine : inputLines) {
            String[] string = inputLine.split(" ");
            leftList.add(Integer.parseInt(string[0]));
            rightList.add(Integer.parseInt(string[3]));
        }

        System.out.println(computeTotalDistance(leftList, rightList));

        System.out.println(computeSimilarityScore(mapNumberOfOccurrence(leftList, rightList), leftList));

    }

    public static Map<Integer, Long> mapNumberOfOccurrence(List<Integer> leftList, List<Integer> rightList) {
        Set<Integer> numbersInLeftList = new HashSet<>(leftList);
        Map<Integer, Long> numberOfOccurrenceMap = new HashMap<>();
        for (Integer number : numbersInLeftList) {
            Long count = rightList.stream()
                    .filter(n -> n.equals(number))
                    .count();
            numberOfOccurrenceMap.put(number, count);
        }

        return numberOfOccurrenceMap;
    }

    public static long computeSimilarityScore(Map<Integer, Long> map, List<Integer> leftList) {
        long score = 0L;
        for (Integer leftListEntry : leftList) {
            Long entry = map.get(leftListEntry);
            if (entry != null) {
                score += entry * leftListEntry;
            }
        }

        return score;
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
