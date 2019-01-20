package hr.fer.ppp.gui;

import java.util.*;

public class PartitionUtil {

  public static int findSideOfDurfeeSquare(List<Integer> partition) {
    int lowestPartOfPartition = partition.get(partition.size()-1);
    int highestPartOfPartition = partition.get(0);

    Map<Integer, Integer> occuranceMapper = new HashMap<>();
    for(int i = 1; i < highestPartOfPartition; i++) {
//      if (occuranceMapper.get(currentPartitionPart) != null) {
//        continue;
//      }

      int numberOfGreaterOrEqualParts = 0;

      for(int part : partition) {
        if (part >= i) {
          numberOfGreaterOrEqualParts++;
        }
      }

      occuranceMapper.put(i, numberOfGreaterOrEqualParts);
    }

    Comparator<Map.Entry<Integer, Integer>> comp = (e1, e2) -> Integer.compare(e2.getKey(), e1.getKey());

    System.out.println(occuranceMapper);
    Map.Entry<Integer, Integer> side = occuranceMapper.entrySet()
            .stream()
            .filter(entry -> entry.getKey() <= entry.getValue())
            .sorted(comp)
            .findFirst()
            .orElseThrow(() -> new RuntimeException());

    return side.getKey();
  }

  public static void main(String[] args) {
    System.out.println(findSideOfDurfeeSquare(Arrays.asList(10, 5, 4, 4, 2, 1)));
  }

}
