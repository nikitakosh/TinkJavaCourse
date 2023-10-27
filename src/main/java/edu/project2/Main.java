package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static int rows = 4;
    private static int cols = 4;
    private int counter = 1;

    List<List<Boolean>> rightWalls = new ArrayList<>();
    List<List<Boolean>> bottomWalls = new ArrayList<>();
    List<Integer> sideLine = new ArrayList<>();


    public void build() {
        for(int i = 0; i < rows; i++) {
            rightWalls.add(new ArrayList<>());
            for(int j = 0; j < cols; j++) {
                rightWalls.get(i).add(false);
            }
        }
        for(int i = 0; i < rows; i++) {
            bottomWalls.add(new ArrayList<>());
            for(int j = 0; j < cols; j++) {
                bottomWalls.get(i).add(false);
            }
        }
        for(int i = 0; i < cols; i++) {
            sideLine.add(0);
        }
        assignUniqueSet();
        for(int i = 0; i < rows; i++) {
            addingVerticalWalls(i);
            addingHorizontalWalls(i);
            checkedHorizontalWalls(i);
            preparingNewLine(i);
        }
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if (bottomWalls.get(i).get(j) && rightWalls.get(i).get(j)) {
                    System.out.print("__|");
                } else if (rightWalls.get(i).get(j)) {
                    System.out.print(" |");
                } else if (bottomWalls.get(i).get(j)) {
                    System.out.print("__");
                }
            }
            System.out.println();
        }
    }
    public void assignUniqueSet() {
        for(int i = 0; i < cols; i++){
            if (sideLine.get(i) == 0) {
                sideLine.set(i, counter);
                counter++;
            }
        }
    }
    public void addingVerticalWalls(int row) {
        for(int i = 0; i < cols-1; i++){
            boolean choice = new Random().nextBoolean();
            if (choice || sideLine.get(i).equals(sideLine.get(i + 1))) {
                rightWalls.get(row).set(i, true);
            } else {
                mergeSet(i, sideLine.get(i));
            }
        }
        rightWalls.get(row).set(cols-1, true);
    }

    private void mergeSet(int index, Integer element) {
        int mutableSet = sideLine.get(index + 1);
        for(int i = 0; i < cols; i++){
            if (sideLine.get(i) == mutableSet) {
                sideLine.set(i, element);
            }
        }
    }

    public void addingHorizontalWalls(int row) {
        for(int i = 0; i < cols; i++) {
            boolean choice = new Random().nextBoolean();
            if (calculateUniqueSet(sideLine.get(i)) != 1 && choice) {
                bottomWalls.get(row).set(i, true);
            }
        }
    }

    private int calculateUniqueSet(Integer element) {
        int countUniqueSet = 0;
        for(int i = 0; i < cols; i++) {
            if (sideLine.get(i).equals(element)) {
                countUniqueSet++;
            }
        }
        return countUniqueSet;
    }

    public void checkedHorizontalWalls(int row) {
        for (int i = 0; i < cols; i++) {
            if (calculateHorizontalWalls(sideLine.get(i), row) == 0) {
                bottomWalls.get(row).set(i, false);
            }
        }
    }
    public int calculateHorizontalWalls(Integer element, int row) {
        int countHorizontalWalls = 0;
        for(int i = 0; i < cols; i++) {
            if (sideLine.get(i).equals(element) && !bottomWalls.get(row).get(i)) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }
    public void preparingNewLine(int row) {
        for(int i = 0; i < cols; i++) {
            if (bottomWalls.get(row).get(i)) {
                sideLine.set(i, 0);
            }
        }
    }
}
