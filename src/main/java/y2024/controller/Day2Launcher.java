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
            for (int index = 0; index < levels.length - 1; index++) {
                if (increasing) {
                    int increase = levels[index + 1] - levels[index];

                    if (increase < 1 || increase > 3) {
                        isReportSafe = false;
                    }
                } else {
                    int decrease = levels[index] - levels[index + 1];

                    if (decrease < 1 || decrease > 3) {
                        isReportSafe = false;
                    }
                }
            }
            if (isReportSafe) {
                amountOfSafeReports++;
            }
        }
        return amountOfSafeReports;
    }
}