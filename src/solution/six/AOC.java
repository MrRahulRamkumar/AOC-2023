package solution.six;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AOC {

    public AOC() {
        // constructor
    }

    long part1(List<String> lines) throws Exception {
        // Part 1 Solution
        String[] line1 = lines.get(0).split(":");
        String[] line2 = lines.get(1).split(":");

        long[] times = Arrays.stream(line1[1].split(" "))
                .filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .toArray();
        long[] distances = Arrays.stream(line2[1].split(" "))
                .filter(s -> !s.isEmpty())
                .mapToLong(Long::parseLong)
                .toArray();

        long product = 1;
        for (int i = 0; i < times.length; i++) {
            long numberOfWays = findNumberOfWays(times[i], distances[i]);
            product = product * numberOfWays;
        }

        return product;
    }

    long findNumberOfWays(long totalTime, long recordDistance) {
        int numberOfWays = 0;
        for (int timePressed = 1; timePressed < totalTime; timePressed++) {
            long speed = timePressed;
            long distanceTraveled = (totalTime - timePressed) * speed;
            if (distanceTraveled > recordDistance) {
                numberOfWays++;
            }
        }
        return numberOfWays;
    }

    int part2(List<String> lines) throws Exception {
        // Part 2 Solution
        return 0;
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
        lines = readFile("src/solution/six/input.txt");

        // calculate port 1
        long result1 = aoc.part1(lines);
        System.out.println("Part 1 result: " + result1);

        // calculate port 2
        int result2 = aoc.part2(lines);
        System.out.println("Part 2 result: " + result2);
    }
}
