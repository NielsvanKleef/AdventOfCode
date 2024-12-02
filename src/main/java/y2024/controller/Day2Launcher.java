package y2024.controller;

import y2024.service.FileService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2Launcher {


    public static void main(String[] args) {
        List<String> inputLines = FileService.readFileLines("AdventOfCode/src/main/resources/d2p1.txt");

        long amountOfSafeReports = getAmountOfSafeReports(inputLines);

        System.out.println(amountOfSafeReports);
    }

    private static long getAmountOfSafeReports(List<String> inputLines) {
        long amountOfSafeReports = 0L;

        for (String inputLine : inputLines) {
            String[] stringArray = inputLine.split(" ");
            List<Integer> levelList = Arrays.stream(stringArray).mapToInt(Integer::parseInt).boxed().toList();
            int[] levelArray;

            for (int index = 0; index < levelList.size(); index++) {
                List<Integer> alteredLevelList = new ArrayList<>();
                for (int level = 0; level < levelList.size(); level++) {
                    if (level != index) {
                        alteredLevelList.add(levelList.get(level));
                    }
                }

                levelArray = alteredLevelList.stream().mapToInt(Integer::intValue).toArray();
                boolean increasing = levelArray[levelArray.length - 1] - levelArray[0] > 0;

                boolean isReportSafe = isReportSafe(levelArray, increasing);

                if (isReportSafe) {
                    amountOfSafeReports++;
                    break;
                }
            }



        }

        return amountOfSafeReports;
    }

    private static boolean isReportSafe(int[] levels, boolean increasing) {
        for (int index = 0; index < levels.length - 1; index++) {
            int increment;

            if (increasing) {
                increment = levels[index + 1] - levels[index];
            } else {
                increment = levels[index] - levels[index + 1];
            }

            if (increment < 1 || increment > 3) {
                return false;
            }
        }

        return true;
    }
}