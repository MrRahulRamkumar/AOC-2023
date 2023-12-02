package template;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AOC {

  public AOC() {
    // constructor
  }

  int part1(List<String> lines) throws Exception {
    // Part 1 Solution
    return 0;
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
    lines = readFile("src/solution/<day>/input.txt");

    // calculate port 1
    int result1 = aoc.part1(lines);
    System.out.println("Part 1 result: " + result1);

    // calculate port 2
    int result2 = aoc.part2(lines);
    System.out.println("Part 2 result: " + result2);
  }
}
