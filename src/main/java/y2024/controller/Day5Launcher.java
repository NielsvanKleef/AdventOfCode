package y2024.controller;

import y2024.service.FileService;

import java.util.*;

public class Day5Launcher {
    static Map<Integer, List<Integer>> orderingRulesMap = new HashMap<>();
    static Map<Integer, List<Integer>> updatesMap = new HashMap<>();

    public static void main(String[] args) {
        readInputAndFillMaps();
        long answer = 0L;
        long answer2 = 0L;

        Comparator<Integer> comperator = getOrderingRuleComparator();

        for (List<Integer> update  : updatesMap.values()) {
            if (update.stream().allMatch(nr -> isNumberOrderedCorrectly(nr, update))) {
                answer += update.get(update.size() / 2);
            } else {
                Collections.sort(update, comperator);
                answer2 += update.get(update.size() / 2);
            }
        }

        System.out.println(answer);
        System.out.println(answer2);
    }

    private static Comparator<Integer> getOrderingRuleComparator() {
        return (i, j) -> {
            for (List<Integer> rule : orderingRulesMap.values()) {
                if (rule.get(0) == j && rule.get(1) == i) {
                    return -1; //not sorted according to rule!
                }
            }

            return 0;
        };
    }


    private static boolean isNumberOrderedCorrectly(Integer number, List<Integer> update) {
        int index = update.indexOf(number);
        List<Integer> filteredUpdate = update.stream().filter(i -> i > update.get(index)).toList();


        for (List<Integer> rule : orderingRulesMap.values()) {
            if (rule.get(1).equals(number)) {
                if (update.stream().filter(nr -> update.indexOf(nr) > index).anyMatch(nr -> nr.equals(rule.get(0)))) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void readInputAndFillMaps() {
        List<String> inputLines = FileService.readFileLines("src/main/resources/d5.txt");
        int indexUpdates = 0;

        for (int index = 0; index < inputLines.size(); index++) {
            if (inputLines.get(index).contains("|")) {
                List<Integer> newOrderRule = new ArrayList<>();
                for (String numString : inputLines.get(index).split("\\|")) {
                    newOrderRule.add(Integer.parseInt(numString));
                }
                orderingRulesMap.put(index, newOrderRule);
            } else if (inputLines.get(index).isEmpty()) {
                indexUpdates = index;
            } else if (inputLines.get(index).contains(",")) {
                List<Integer> newUpdate = new ArrayList<>();
                for (String numString : inputLines.get(index).split(",")) {
                    newUpdate.add(Integer.parseInt(numString));
                }
                updatesMap.put(index - indexUpdates , newUpdate);
            }
        }
    }
}
