package solution.one;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AOC {
  public Map<String, Integer> digitStringMap;

  public AOC() {
    digitStringMap = new HashMap<String, Integer>();

    digitStringMap.put("one", 1);
    digitStringMap.put("two", 2);
    digitStringMap.put("three", 3);
    digitStringMap.put("four", 4);
    digitStringMap.put("five", 5);
    digitStringMap.put("six", 6);
    digitStringMap.put("seven", 7);
    digitStringMap.put("eight", 8);
    digitStringMap.put("nine", 9);
  }

  public int calculate1(List<String> lines) {
    // calculate
    int sum = 0;
    for (String line : lines) {
      int start = 0;
      int end = line.length() - 1;

      boolean startFound = false;
      boolean endFound = false;
      int digit1 = 0;
      int digit2 = 0;

      while (start < line.length() && end >= 0) {
        if (Character.isDigit(line.charAt(start)) && !startFound) {
          digit1 = Character.getNumericValue(line.charAt(start));
          startFound = true;
        } else {
          start++;
        }

        if (Character.isDigit(line.charAt(end)) && !endFound) {
          digit2 = Character.getNumericValue(line.charAt(end));
          endFound = true;
        } else {
          end--;
        }

        if (startFound && endFound) {
          sum += (digit1 * 10) + digit2;
          break;
        }
      }
    }

    return sum;
  }

  public int calculate2(List<String> lines) {

    // calculate
    int sum = 0;
    for (String line : lines) {
      int start = 0;
      int end = line.length() - 1;

      boolean startFound = false;
      boolean endFound = false;
      int digit1 = 0;
      int digit2 = 0;

      while (start < line.length() && end >= 0) {
        if (!startFound) {
          String subString = null;
          if (Character.isDigit(line.charAt(start))) {
            digit1 = Character.getNumericValue(line.charAt(start));
            startFound = true;
          } else if ((subString = isDigitString(start, line)) != null) {
            digit1 = digitStringMap.get(subString);
            startFound = true;
          } else {
            start++;
          }
        }

        if (!endFound) {
          String subString = null;
          if (Character.isDigit(line.charAt(end))) {
            digit2 = Character.getNumericValue(line.charAt(end));
            endFound = true;
          } else if ((subString = isDigitString(end, line)) != null) {
            digit2 = digitStringMap.get(subString);
            endFound = true;
          } else {
            end--;
          }
        }

        if (startFound && endFound) {
          sum += (digit1 * 10) + digit2;
          break;
        }
      }
    }

    return sum;
  }

  public String isDigitString(int index, String line) {
    String[] digitStrings = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

    for (String digitString : digitStrings) {
      int endIndex = index + digitString.length();
      if (endIndex > line.length()) {
        continue;
      }

      String subString = line.substring(index, endIndex);
      if (subString.equals(digitString)) {
        return subString;
      }
    }
    return null;
  }

  public static void main(String[] args) {
    AOC aoc = new AOC();

    // read file
    List<String> lines = new ArrayList<String>();
    try {
      lines = readFile("src/solution/one/input.txt");
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    System.out.println(lines);
    // calculate port 1
    int result1 = aoc.calculate1(lines);
    System.out.println(result1);

    // calculate port 2
    int result2 = aoc.calculate2(lines);
    System.out.println(result2);
  }

  public static List<String> readFile(String filename) throws FileNotFoundException {
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
}
