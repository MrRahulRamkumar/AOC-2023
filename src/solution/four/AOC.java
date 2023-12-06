package solution.four;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AOC {

    public AOC() {
        // constructor
    }

    int part1(List<String> lines) throws Exception {
        // Part 1 Solution
        int sum = 0;

        for (String line : lines) {
            String[] splits = line.split(":");
            String[] cards = splits[1].trim().split("\\|");
            String[] winningCards = cards[0].trim().split(" ");
            String[] myCards = cards[1].trim().split(" ");

            int score = findScore(winningCards, myCards);
            sum = sum + score;
        }
        return sum;
    }

    int findScore(String[] winningCards, String[] myCards) {
        Set<String> winningSet = new HashSet<>();
        for (String card : winningCards) {
            if (card.length() > 0) {
                winningSet.add(card);
            }
        }

        int score = 0;

        for (String card : myCards) {
            if (card.length() == 0) {
                continue;
            }

            if (winningSet.contains(card)) {
                if (score == 0) {
                    score = 1;
                } else {
                    score = score * 2;
                }
            }
        }
        return score;
    }

    int part2(List<String> lines) throws Exception {
        int[] result = new int[lines.size() + 1];
        for (int i = 1; i < result.length; i++) {
            result[i] = 1;
        }

        int cardIndex = 1;
        for (String line : lines) {
            String[] splits = line.split(":");
            String[] cards = splits[1].trim().split("\\|");
            String[] winningCards = cards[0].trim().split(" ");
            String[] myCards = cards[1].trim().split(" ");

            Set<String> winningSet = new HashSet<>();
            for (String card : winningCards) {
                if (card.length() > 0) {
                    winningSet.add(card);
                }
            }

            int numberOfMatches = 0;
            for (String card : myCards) {
                if (card.length() == 0) {
                    continue;
                }

                if (winningSet.contains(card)) {
                    numberOfMatches++;
                }
            }

            for (int i = 1; i <= numberOfMatches; i++) {
                result[cardIndex + i] = result[cardIndex + i] + result[cardIndex];
            }
            cardIndex++;
        }

        int sum = 0;
        for (int count : result) {
            sum = sum + count;
        }
        return sum;
    }

    static List<String> readFile(String filename) throws Exception {
        // read file
        File file = new File(filename);
        Scanner sc = new Scanner(file);
        List<String> lines = new ArrayList<String>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        sc.close();
        return lines;
    }

    public static void main(String[] args) throws Exception {
        AOC aoc = new AOC();

        // read file
        List<String> lines = new ArrayList<String>();
        lines = readFile("src/solution/four/input.txt");

        // calculate port 1
        int result1 = aoc.part1(lines);
        System.out.println("Part 1 result: " + result1);

        // calculate port 2
        int result2 = aoc.part2(lines);
        System.out.println("Part 2 result: " + result2);
    }
}
