package solution.two;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOC {

  final int MAX_RED = 12;
  final int MAX_BLUE = 14;
  final int MAX_GREEN = 13;

  public AOC() {
    // constructor
  }

  int getId(String input) {
    String[] split = input.split(" ");
    int id = Integer.parseInt(split[1]);
    return id;
  }

  boolean isPossible(String input) {
    String[] splits = input.split(";");

    for (String s : splits) {
      String[] comaSplits = s.split(",");
      for (String cs : comaSplits) {
        String[] spaceSplits = cs.trim().split(" ");
        if (spaceSplits[1].equals("red") && Integer.parseInt(spaceSplits[0]) > MAX_RED) {
          return false;
        } else if (spaceSplits[1].equals("blue") && Integer.parseInt(spaceSplits[0]) > MAX_BLUE) {
          return false;
        } else if (spaceSplits[1].equals("green") && Integer.parseInt((spaceSplits[0])) > MAX_GREEN) {
          return false;
        }
      }
    }
    return true;
  }

  int part1(List<String> lines) throws Exception {
    // Part 1 Solution

    int sum = 0;
    for (String line : lines) {
      String[] colonSplit = line.split(":");

      if (isPossible(colonSplit[1].trim())) {
        int id = getId(colonSplit[0].trim());
        sum += id;
      }
    }

    return sum;
  }

  int findPowerSet(String input) {
    String[] splits = input.split(";");

    int maxRed = 0;
    int maxBlue = 0;
    int maxGreen = 0;
    for (String s : splits) {
      String[] comaSplits = s.split(",");
      for (String cs : comaSplits) {
        String[] spaceSplits = cs.trim().split(" ");
        if (spaceSplits[1].equals("red")) {
          int redCount = Integer.parseInt(spaceSplits[0]);
          if (redCount > maxRed) {
            maxRed = redCount;
          }
        } else if (spaceSplits[1].equals("blue")) {
          int blueCount = Integer.parseInt(spaceSplits[0]);
          if (blueCount > maxBlue) {
            maxBlue = blueCount;
          }
        } else if (spaceSplits[1].equals("green")) {
          int greenCount = Integer.parseInt((spaceSplits[0]));
          if (greenCount > maxGreen) {
            maxGreen = greenCount;
          }
        }
      }
    }
    return (maxRed * maxBlue * maxGreen);
  }

  int part2(List<String> lines) throws Exception {
    // Part 2 Solution
    int sum = 0;
    for (String line : lines) {
      String[] colonSplit = line.split(":");
      int powerSet = findPowerSet(colonSplit[1].trim());
      sum += powerSet;
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
    lines = readFile("src/solution/two/input.txt");

    // calculate port 1
    int result1 = aoc.part1(lines);
    System.out.println("Part 1 result: " + result1);

    // calculate port 2
    int result2 = aoc.part2(lines);
    System.out.println("Part 2 result: " + result2);
  }
}
