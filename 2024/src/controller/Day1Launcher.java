package src.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day1Launcher {



    public static void main(String[] args) {
        System.out.println(computeTotalDistance());
    }

    public static Long computeTotalDistance() {
        List<Integer> leftList = Arrays.asList(3, 4, 2, 1, 3, 3);
        List<Integer> rightList = Arrays.asList(4, 3, 5, 3, 9, 3);

        Collections.sort(leftList);
        Collections.sort(rightList);

        long totalDistance = 0L;
        for (int index = 0; index < leftList.size(); index++) {
            totalDistance += Math.abs(rightList.get(index) - leftList.get(index));
        }

        return totalDistance;
    }
}
