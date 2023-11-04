package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EllerMazeGenerator implements Generator {
    private final List<Integer> sideLine = new ArrayList<>();
    private final List<List<Boolean>> rightWalls = new ArrayList<>();
    private final List<List<Boolean>> bottomWalls = new ArrayList<>();
    private int counter = 0;
    private int height;
    private int width;

    @Override
    public Maze generate(int height, int width) {
        this.height = height;
        this.width = width;
        initializeRightWallsAndBottomWalls(height, width);
        fillEmptyValue();
        for (int i = 0; i < height - 1; i++) {
            assignUniqueSet();
            addingVerticalWalls(i);
            addingHorizontalWalls(i);
            checkedHorizontalWalls(i);
            preparingNewLine(i);
        }
        addingEndLine();
        return MazeGraphGenerator.generateGraph(height, width, bottomWalls, rightWalls);
    }

    private void initializeRightWallsAndBottomWalls(int height, int width) {
        for (int i = 0; i < height; i++) {
            rightWalls.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                rightWalls.get(i).add(false);
            }
        }
        for (int i = 0; i < height; i++) {
            bottomWalls.add(new ArrayList<>());
            for (int j = 0; j < width; j++) {
                bottomWalls.get(i).add(false);
            }
        }
    }

    public void fillEmptyValue() {
        for (int i = 0; i < width; i++) {
            sideLine.add(0);
        }
    }

    public void assignUniqueSet() {
        for (int i = 0; i < width; i++) {
            if (sideLine.get(i) == 0) {
                sideLine.set(i, counter);
                counter++;
            }
        }
    }

    public void addingVerticalWalls(int row) {
        for (int i = 0; i < width - 1; i++) {
            boolean choice = new Random().nextBoolean();
            if (choice || Objects.equals(sideLine.get(i), sideLine.get(i + 1))) {
                rightWalls.get(row).set(i, true);
            } else {
                mergeSet(i, sideLine.get(i));
            }
        }
        rightWalls.get(row).set(width - 1, true);
    }

    private void mergeSet(int index, Integer element) {
        int mutableSet = sideLine.get(index + 1);
        for (int j = 0; j < width; j++) {
            if (sideLine.get(j) == mutableSet) {
                sideLine.set(j, element);
            }
        }
    }

    public void addingHorizontalWalls(int row) {
        for (int i = 0; i < width; i++) {
            boolean choice = new Random().nextBoolean();
            if (calculateUniqueSet(sideLine.get(i)) != 1 && choice) {
                bottomWalls.get(row).set(i, true);
            }
        }
    }

    private int calculateUniqueSet(Integer element) {
        int countUniqSet = 0;
        for (int i = 0; i < width; i++) {
            if (Objects.equals(sideLine.get(i), element)) {
                countUniqSet++;
            }
        }
        return countUniqSet;
    }

    public void checkedHorizontalWalls(int row) {
        for (int i = 0; i < width; i++) {
            if (calculateHorizontalWalls(sideLine.get(i), row) == 0) {
                bottomWalls.get(row).set(i, false);
            }
        }
    }

    private int calculateHorizontalWalls(Integer element, int row) {
        int countHorizontalWalls = 0;
        for (int i = 0; i < width; i++) {
            if (Objects.equals(sideLine.get(i), element) && !bottomWalls.get(row).get(i)) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }

    public void preparingNewLine(int row) {
        for (int i = 0; i < width; i++) {
            if (bottomWalls.get(row).get(i)) {
                sideLine.set(i, 0);
            }
        }
    }

    public void addingEndLine() {
        assignUniqueSet();
        addingVerticalWalls(height - 1);
        checkedEndLine();
    }

    private void checkedEndLine() {
        for (int i = 0; i < width - 1; i++) {
            if (!Objects.equals(sideLine.get(i), sideLine.get(i + 1))) {
                rightWalls.get(height - 1).set(i, false);
                mergeSet(i, sideLine.get(i));
            }
            bottomWalls.get(height - 1).set(i, true);
        }
        bottomWalls.get(height - 1).set(width - 1, true);
    }
}
