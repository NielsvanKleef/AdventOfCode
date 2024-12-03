package y2024.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3Launcher {
    static String inputString = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

    public static void main(String[] args) {
        int sumOfMultiplications = 0;

        Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
        Matcher matcher = pattern.matcher(inputString);
        while (matcher.find()) {
            sumOfMultiplications += (Integer.parseInt(matcher.group(1)) * Integer.parseInt(matcher.group(2)));
        }

        System.out.println(sumOfMultiplications);
    }
}
