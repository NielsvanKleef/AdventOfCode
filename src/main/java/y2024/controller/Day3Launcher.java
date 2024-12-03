package y2024.controller;

import y2024.service.FileService;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Launcher {
    static String inputString = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

    public static void main(String[] args) {
        List<String> inputLines = FileService.readFileLines("AdventOfCode/src/main/resources/d3p1.txt");

        int sumOfMultiplications = 0;

        for (String inputLine : inputLines) {
             sumOfMultiplications += calculateSumOfLine(inputLine);
        }

        System.out.println(sumOfMultiplications);
    }

    private static int calculateSumOfLine(String line) {
        int sumOfMultiplications = 0;

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            sumOfMultiplications += (Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
        }
        return sumOfMultiplications;
    }
}
