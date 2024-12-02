package y2024.controller;

import y2024.service.FileService;

import java.util.Arrays;
import java.util.List;

public class Day2Launcher {


    public static void main(String[] args) {
        List<String> inputLines = FileService.readFileLines("AdventOfCode/src/main/resources/d2p1sample.txt");

        long amountOfSafeReports = getAmountOfSafeReports(inputLines);

        System.out.println(amountOfSafeReports);
    }

    private static long getAmountOfSafeReports(List<String> inputLines) {
        long amountOfSafeReports = 0L;

        for (String inputLine : inputLines) {
            String[] stringArray = inputLine.split(" ");
            int[] levels = Arrays.stream(stringArray).mapToInt(Integer::parseInt).toArray();
            boolean increasing = levels[levels.length - 1] - levels[0] > 0;

            boolean isReportSafe = true;

            isReportSafe = isReportSafe(levels, increasing, isReportSafe);

            if (isReportSafe) {
                amountOfSafeReports++;
            }

        }
        return amountOfSafeReports;
    }

    private static boolean isReportSafe(int[] levels, boolean increasing, boolean isReportSafe) {
        for (int index = 0; index < levels.length - 1; index++) {
            int increment;

            if (increasing) {
                increment = levels[index + 1] - levels[index];
            } else {
                increment = levels[index] - levels[index + 1];
            }

            if (increment < 1 || increment > 3) {
                isReportSafe = false;
            }
        }
        return isReportSafe;
    }
}