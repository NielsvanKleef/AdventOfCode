package y2024.controller;

import y2024.service.FileService;

import java.util.*;

public class Day4Launcher {
    public static Map<Integer, List<String>> wordSearchMap = new HashMap<>();

    public static void main(String[] args) {
        fillWordSearchMap();
        int xmasOccurences = 0;

        for (int row = 0; row < wordSearchMap.size(); row++) {
            for (int col = 0; col < wordSearchMap.get(0).size(); col++) {
                //todo: markeren als een letter niet meedoet met een xmas?
                if (wordSearchMap.get(row).get(col).equals("X")) {
                    if (leftToRight(row, col)) {
                        xmasOccurences++;
                    }

                    if (rightToLeft(row, col)) {
                        xmasOccurences++;
                    }

                    if (topToBottom(row, col)) {
                        xmasOccurences++;
                    }

                    if (bottomToTop(row, col)) {
                        xmasOccurences++;
                    }

                    if (diagonalNE(row, col)) {
                        xmasOccurences++;
                    }

                    if (diagonalSE(row, col)) {
                        xmasOccurences++;
                    }

                    if (diagonalSW(row, col)) {
                        xmasOccurences++;
                    }

                    if (diagonalNW(row, col)) {
                        xmasOccurences++;
                    }
                }
            }
        }
        System.out.println(xmasOccurences);
    }

    private static String getPuzzleLetter(int row, int col) {
        return wordSearchMap.get(row).get(col);
    }

    private static boolean leftToRight(int row, int col) {
        if (col + 3 > wordSearchMap.get(row).size()) {
            return false;
        }
        return checkMAS(row, col + 1, row, col + 2, row, col + 3);
    }

    private static boolean rightToLeft(int row, int col) {
        if (col - 3 < 0) {
            return false;
        }
        return checkMAS(row, col - 1, row, col - 2, row, col - 3);
    }

    private static boolean topToBottom(int row, int col) {
        if (row + 3 >= wordSearchMap.size()) {
            return false;
        }
        return checkMAS(row + 1, col, row + 2, col, row + 3, col);
    }

    private static boolean bottomToTop(int row, int col) {
        if (row - 3 < 0) {
            return false;
        }
        return checkMAS(row - 1, col, row - 2, col, row - 3, col);
    }

    private static boolean diagonalNE(int row, int col) {
        if (row - 3 < 0 || col + 3 >= wordSearchMap.get(row).size()) {
            return false;
        }
        return checkMAS(row - 1, col + 1, row - 2, col + 2, row - 3, col + 3);
    }

    private static boolean diagonalSE(int row, int col) {
        if (row + 3 >= wordSearchMap.size() || col + 3 >= wordSearchMap.get(row).size()) {
            return false;
        }
        return checkMAS(row + 1, col + 1, row + 2, col + 2, row + 3, col + 3);
    }

    private static boolean diagonalSW(int row, int col) {
        if (row + 3 >= wordSearchMap.size() || col - 3 < 0) {
            return false;
        }
        return checkMAS(row + 1, col - 1, row + 2, col - 2, row + 3, col - 3);
    }

    private static boolean diagonalNW(int row, int col) {
        if (row - 3 < 0 || col - 3 < 0) {
            return false;
        }
        return checkMAS(row - 1, col - 1, row - 2, col - 2, row - 3, col - 3);
    }

    private static boolean checkMAS(int rowM, int colM, int rowA, int colA, int rowS, int colS) {
        return getPuzzleLetter(rowM, colM).equals("M")
                && getPuzzleLetter(rowA, colA).equals("A")
                && getPuzzleLetter(rowS, colS).equals("S");
    }

    private static void fillWordSearchMap() {
        List<String> inputLines = FileService.readFileLines("AdventOfCode/src/main/resources/d4p1.txt");

        for (int inputLine = 0; inputLine < inputLines.size(); inputLine++) {
            List<String> horizontalLine = new ArrayList<>(Arrays.asList(inputLines.get(inputLine).split("")));
            wordSearchMap.put(inputLine, horizontalLine);
        }
    }

}
