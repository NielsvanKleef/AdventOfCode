package y2024.controller;

import y2024.service.FileService;

import java.util.List;

public class Day6Launcher {
    private static final List<String> DIRECTIONS = List.of("^", ">", "v", "<");
    private static final int X_GUARD = 0;
    private static final int Y_GUARD = 1;

    public static void main(String[] args) {
        List<String> inputLines = FileService.readFileLines("src/main/resources/d6.txt");

        int[] guardCoordinates = {-1, -1};
        String guardDirection = "";

        for (String inputLine : inputLines) {
            for (String direction : DIRECTIONS) {
                if (inputLine.contains(direction)) {
                    guardCoordinates[X_GUARD] = inputLine.indexOf(direction);
                    guardCoordinates[Y_GUARD] = inputLines.indexOf(inputLine);
                    guardDirection = direction;
                    break;
                }
            }
        }
        boolean canTakeAnotherStep = true;
        while (canTakeAnotherStep) {
            switch (guardDirection) {
                case "^":
                    if ((guardCoordinates[Y_GUARD] - 1) < 0) {
                        canTakeAnotherStep = false;
                    } else if ('#' == inputLines.get(guardCoordinates[Y_GUARD] - 1).charAt(guardCoordinates[X_GUARD])) {
                        guardDirection = ">";
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('^', 'X'));
                    } else {
                        // Update current position to X
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('^', 'X'));
                        // Update guard position
                        StringBuilder newLine = new StringBuilder(inputLines.get(guardCoordinates[Y_GUARD] - 1));
                        newLine.setCharAt(guardCoordinates[X_GUARD], '^');
                        inputLines.set(guardCoordinates[Y_GUARD] - 1, newLine.toString());
                        guardCoordinates[Y_GUARD]--;
                    }
                    break;
                case ">":
                    if ((guardCoordinates[X_GUARD]) >= inputLines.get(guardCoordinates[X_GUARD]).length()) {
                        canTakeAnotherStep = false;
                    } else if ('#' == inputLines.get(guardCoordinates[Y_GUARD]).charAt(guardCoordinates[X_GUARD])) {
                        guardDirection = "v";
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('>', 'X'));
                        guardCoordinates[X_GUARD]--;
                    } else {
                        // Update current position to X
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('>', 'X'));
                        // Update guard position
                        StringBuilder newLine = new StringBuilder(inputLines.get(guardCoordinates[Y_GUARD]));
                        newLine.setCharAt(guardCoordinates[X_GUARD], '>');
                        inputLines.set(guardCoordinates[Y_GUARD],
                                newLine.toString());
                        guardCoordinates[X_GUARD]++;
                    }
                    break;
                case "v":
                    if ((guardCoordinates[Y_GUARD] + 1) >= inputLines.size()) {
                        canTakeAnotherStep = false;
                    } else if ('#' == inputLines.get(guardCoordinates[Y_GUARD] + 1).charAt(guardCoordinates[X_GUARD])) {
                        guardDirection = "<";
                    } else {
                        // Update current position to X
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('v', 'X'));
                        // Update guard position
                        StringBuilder newLine = new StringBuilder(inputLines.get(guardCoordinates[Y_GUARD] + 1));
                        newLine.setCharAt(guardCoordinates[X_GUARD], 'v');
                        inputLines.set(guardCoordinates[Y_GUARD] + 1,
                                newLine.toString());
                        guardCoordinates[Y_GUARD]++;
                    }
                    break;
                case "<":
                    if ((guardCoordinates[X_GUARD]) < 0) {
                        canTakeAnotherStep = false;
                    } else if ('#' == inputLines.get(guardCoordinates[Y_GUARD] ).charAt(guardCoordinates[X_GUARD])) {
                        guardDirection = "^";
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('<', 'X'));
                        guardCoordinates[X_GUARD]++;
                    } else {
                        // Update current position to X
                        String currentLine = inputLines.get(guardCoordinates[Y_GUARD]);
                        inputLines.set(guardCoordinates[Y_GUARD],
                                currentLine.replace('<', 'X'));
                        // Update guard position
                        StringBuilder newLine = new StringBuilder(inputLines.get(guardCoordinates[Y_GUARD]));
                        newLine.setCharAt(guardCoordinates[X_GUARD], '<');
                        inputLines.set(guardCoordinates[Y_GUARD], newLine.toString());
                        guardCoordinates[X_GUARD]--;

                    }
                    break;
                default:
                    System.out.println("Guard vanished into thin air.");
            }
        }
        System.out.println("Tijd om te tellen: ");
        int countX = 0;
        for (String inputLine : inputLines) {
            countX += (int) inputLine.chars().filter(c -> c == 'X').count();
        }

        System.out.println(countX + 1); // Guard staat altijd nog net op de kaart
    }
}
