package hr.fer.ppp.gui;

import java.util.*;

public class PartitionUtil {

  private static final Comparator<Map.Entry<Integer, Integer>> comp = (e1, e2) -> Integer.compare(e2.getKey(), e1.getKey());

  public static int findSideOfDurfeeSquare(List<Integer> partition) {
    int highestPartOfPartition = partition.get(0);

    Map<Integer, Integer> occuranceMapper = new HashMap<>();
    for(int i = 1; i <= highestPartOfPartition; i++) {
      int numberOfGreaterOrEqualParts = 0;

      for(int part : partition) {
        if (part >= i) {
          numberOfGreaterOrEqualParts++;
        }
      }

      occuranceMapper.put(i, numberOfGreaterOrEqualParts);
    }

//    System.out.println(occuranceMapper);
    Map.Entry<Integer, Integer> side = occuranceMapper.entrySet()
            .stream()
            .filter(entry -> entry.getKey() <= entry.getValue())
            .sorted(comp)
            .findFirst()
            .orElseThrow(() -> new RuntimeException());

    return side.getKey();
  }

//  za testiranje
  public static void main(String[] args) {
    System.out.println(findSideOfDurfeeSquare(Arrays.asList(2, 2, 2, 2, 2, 2)));
  }

}
