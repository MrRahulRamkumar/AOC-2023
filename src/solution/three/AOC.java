package solution.three;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AOC {

  public AOC() {
    // constructor
  }

  char[][] getMatrixFromList(List<String> lines) {
    int rows = lines.size();
    char[][] matrix = new char[rows][lines.get(0).length() + 1];

    for (int i = 0; i < rows; i++) {
      String line = lines.get(i);
      for (int j = 0; j <= line.length(); j++) {
        if (j == line.length()) {
          matrix[i][j] = '.';
        } else {
          matrix[i][j] = line.charAt(j);
        }
      }
    }

    return matrix;
  }

  double part1(List<String> lines) throws Exception {
    // Part 1 Solution
    char[][] arr = getMatrixFromList(lines);

    double sum = 0;

    for (int i = 0; i < arr.length; i++) {
      int start = -1;
      int end = -1;
      boolean numberSequenceStarted = false;
      boolean numberSequenceEnded = false;
      for (int j = 0; j < arr[0].length; j++) {
        if (!numberSequenceStarted && Character.isDigit(arr[i][j])) {
          start = j;
          numberSequenceStarted = true;
        }
        if (numberSequenceStarted && !Character.isDigit(arr[i][j])) {
          end = j - 1;
          numberSequenceEnded = true;
        }

        if (numberSequenceStarted && numberSequenceEnded) {
          numberSequenceStarted = false;
          numberSequenceEnded = false;

          if (isNumberSequenceAdjacentToSymbol(i, start, end, arr)) {
            sum = sum + getNumberFromNumberSequence(i, start, end, arr);
          }
        }
      }
    }

    return sum;
  }

  long part2(List<String> lines) throws Exception {
    // Part 1 Solution
    char[][] arr = getMatrixFromList(lines);
    Map<String, List<Integer>> gearRatioMap = new HashMap<>();

    for (int i = 0; i < arr.length; i++) {
      int start = -1;
      int end = -1;
      boolean numberSequenceStarted = false;
      boolean numberSequenceEnded = false;
      for (int j = 0; j < arr[0].length; j++) {
        if (!numberSequenceStarted && Character.isDigit(arr[i][j])) {
          start = j;
          numberSequenceStarted = true;
        }
        if (numberSequenceStarted && !Character.isDigit(arr[i][j])) {
          end = j - 1;
          numberSequenceEnded = true;
        }

        if (numberSequenceStarted && numberSequenceEnded) {
          numberSequenceStarted = false;
          numberSequenceEnded = false;

          makeGearRatioMap(i, start, end, arr, gearRatioMap);
        }
      }
    }

    long sum = 0;
    for (Map.Entry<String, List<Integer>> entry : gearRatioMap.entrySet()) {
      List<Integer> gears = entry.getValue();

      if (gears.size() < 2) {
        continue;
      }

      long product = 1l;
      for (int gear : gears) {
        product = product * gear;
      }

      sum = sum + product;
    }

    return sum;
  }

  double getNumberFromNumberSequence(int i, int start, int end, char[][] arr) {
    double number = 0;
    int count = 0;
    for (int j = start; j <= end; j++) {
      int digit = Character.getNumericValue(arr[i][j]);
      int exponent = end - start - count;
      number = number + (digit * Math.pow(10, exponent));
      count++;
    }

    return number;
  }

  void makeGearRatioMap(int i, int start, int end, char[][] arr, Map<String, List<Integer>> map) {
    for (int j = start; j <= end; j++) {
      int[][] directions = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

      for (int[] direction : directions) {
        int newX = i + direction[0];
        int newY = j + direction[1];

        if (isValidIndex(newX, newY, arr) && arr[newX][newY] == '*') {
          double number = getNumberFromNumberSequence(i, start, end, arr);
          String index = newX + "," + newY;

          List<Integer> list;
          if (map.containsKey(index)) {
            list = map.get(index);
          } else {
            list = new ArrayList<>();
            map.put(index, list);
          }
          if (!list.contains((int) number)) {
            list.add((int) number);
          }
        }
      }
    }
  }

  boolean isNumberSequenceAdjacentToSymbol(int i, int start, int end, char[][] arr) {
    for (int j = start; j <= end; j++) {
      int[][] directions = { { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };

      for (int[] direction : directions) {
        int newX = i + direction[0];
        int newY = j + direction[1];

        if (isValidIndex(newX, newY, arr) &&
            !Character.isDigit(arr[newX][newY]) &&
            arr[newX][newY] != '.') {
          return true;
        }
      }
    }

    return false;
  }

  boolean isValidIndex(int i, int j, char[][] arr) {
    int rows = arr.length;
    int columns = arr[0].length;

    return i < rows && i >= 0 && j < columns && j >= 0;
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
    lines = readFile("src/solution/three/input.txt");

    // calculate port 1
    double result1 = aoc.part1(lines);
    System.out.println("Part 1 result: " + result1);

    // calculate port 2
    long result2 = aoc.part2(lines);
    System.out.println("Part 2 result: " + result2);
  }
}
