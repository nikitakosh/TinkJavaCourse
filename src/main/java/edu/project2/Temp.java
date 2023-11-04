package edu.project2;

import java.util.*;

public class Temp {

    final int WIDTH = 10;
    final int HEIGHT = 10;
    List<Integer> sideLine = new ArrayList<>();
    List<List<Boolean>> vWalls = new ArrayList<>();
    List<List<Boolean>> hWalls = new ArrayList<>();
    int counter = 0;
    private final Cell[][] maze = new Cell[HEIGHT][WIDTH];

    public static void main(String[] args) {
        Temp temp = new Temp();
        for (int i = 0; i < temp.HEIGHT; i++) {
            temp.vWalls.add(new ArrayList<>());
            for (int j = 0; j < temp.WIDTH; j++) {
                temp.vWalls.get(i).add(false);
            }
        }
        for (int i = 0; i < temp.HEIGHT; i++) {
            temp.hWalls.add(new ArrayList<>());
            for (int j = 0; j < temp.WIDTH; j++) {
                temp.hWalls.get(i).add(false);
            }
        }
        temp.fillEmptyValue();
        for (int i = 0; i < temp.HEIGHT - 1; i++) {
            temp.assignUniqueSet();
            temp.addingVerticalWalls(i);
            temp.addingHorizontalWalls(i);
            temp.checkedHorizontalWalls(i);
            temp.preparingNewLine(i);
        }
        temp.addingEndLine();
        for (int row = 0; row < temp.HEIGHT; row++) {
            if (row == 0) {
                for (int i = 0; i < temp.WIDTH; i++) {
                    System.out.print("+---");
                }
                System.out.println("+");
            }
            System.out.print("|");
            // Вывод строки с вертикальными стенками и проходами
            for (int col = 0; col < temp.WIDTH; col++) {
                if (temp.vWalls.get(row).get(col)) {
                    System.out.print("   |");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
            for (int col = 0; col < temp.WIDTH; col++) {
                if (temp.hWalls.get(row).get(col)) {
                    System.out.print("+---");
                } else {
                    System.out.print("+   ");
                }
            }
            System.out.println("+");
        }
        for (int i = 0; i < temp.WIDTH; i++) {
            for (int j = 0; j < temp.HEIGHT; j++) {
                List<Cell.Direction> directions = new ArrayList<>();
                if (i != 0 && !temp.hWalls.get(i - 1).get(j)) {
                    directions.add(Cell.Direction.TOP);
                }
                if (j != 0 && !temp.vWalls.get(i).get(j - 1)) {
                    directions.add(Cell.Direction.LEFT);
                }
                if (!temp.hWalls.get(i).get(j)) {
                    directions.add(Cell.Direction.BOTTOM);
                }
                if (!temp.vWalls.get(i).get(j)) {
                    directions.add(Cell.Direction.RIGHT);
                }
                temp.maze[i][j] = new Cell(i, j, directions);
            }
        }
        Stack<Cell> stack = temp.dfsSearch(new Coordinate(0, 0), new Coordinate(9, 9));
        List<Cell> distance = new ArrayList<>();
        while (!stack.isEmpty()) {
            System.out.println(stack.peek());
            distance.add(stack.pop());
        }
        for (int row = 0; row < temp.HEIGHT; row++) {
            if (row == 0) {
                for (int i = 0; i < temp.WIDTH; i++) {
                    System.out.print("+---");
                }
                System.out.println("+");
            }
            System.out.print("|");
            for (int col = 0; col < temp.WIDTH; col++) {
                if (temp.vWalls.get(row).get(col)) {
                    boolean flag = true;
                    for (Cell cell : distance) {
                        if (cell.row() == row && cell.col() == col) {
                            System.out.print(" * |");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) System.out.print("   |");
                } else {
                    boolean flag = true;
                    for (Cell cell : distance) {
                        if (cell.row() == row && cell.col() == col) {
                            System.out.print(" *  ");
                            flag = false;
                            break;
                        }
                    }
                    if (flag) System.out.print("    ");
                }
            }
            System.out.println();
            for (int col = 0; col < temp.WIDTH; col++) {
                if (temp.hWalls.get(row).get(col)) {
                    System.out.print("+---");
                } else {
                    System.out.print("+   ");
                }
            }
            System.out.println("+");
        }
    }

    public Stack<Cell> dfsSearch(Coordinate start, Coordinate finish) {
        Stack<Cell> stack = new Stack<>();
        stack.add(maze[start.row()][start.col()]);
        List<Cell> cells = new ArrayList<>();
        while (!stack.isEmpty()) {
            Cell curr = stack.peek();
            if (curr.col() == finish.col() && curr.row() == finish.row()) {
                return stack;
            }
            if (curr.directions().contains(Cell.Direction.TOP) && !cells.contains(maze[curr.row() - 1][curr.col()])) {
                stack.push(maze[curr.row() - 1][curr.col()]);
            } else if (curr.directions().contains(Cell.Direction.RIGHT) && !cells.contains(maze[curr.row()][curr.col() + 1])) {
                stack.push(maze[curr.row()][curr.col() + 1]);

            } else if (curr.directions().contains(Cell.Direction.BOTTOM) && !cells.contains(maze[curr.row() + 1][curr.col()])) {
                stack.push(maze[curr.row() + 1][curr.col()]);
            } else if (curr.directions().contains(Cell.Direction.LEFT) && !cells.contains(maze[curr.row()][curr.col() - 1])) {
                stack.push(maze[curr.row()][curr.col() - 1]);
            } else {
                stack.pop();
            }
            cells.add(curr);
        }

        return stack;
    }

    public void fillEmptyValue() {
        for (int i = 0; i < WIDTH; i++) {
            sideLine.add(0);
        }
    }

    public void assignUniqueSet() {
        for (int i = 0; i < WIDTH; i++) {
            if (sideLine.get(i) == 0) {
                sideLine.set(i, counter);
                counter++;
            }
        }
    }

    public void addingVerticalWalls(int row) {
        for (int i = 0; i < WIDTH - 1; i++) {
            boolean choice = new Random().nextBoolean();
            if (choice || Objects.equals(sideLine.get(i), sideLine.get(i + 1))) {
                vWalls.get(row).set(i, true);
            } else {
                mergeSet(i, sideLine.get(i));
            }
        }
        vWalls.get(row).set(WIDTH - 1, true);
    }

    private void mergeSet(int index, Integer element) {
        int mutableSet = sideLine.get(index + 1);
        for (int j = 0; j < WIDTH; j++) {
            if (sideLine.get(j) == mutableSet) {
                sideLine.set(j, element);
            }
        }
    }

    public void addingHorizontalWalls(int row) {
        for (int i = 0; i < WIDTH; i++) {
            boolean choice = new Random().nextBoolean();
            if (calculateUniqueSet(sideLine.get(i)) != 1 && choice) {
                hWalls.get(row).set(i, true);
            }
        }
    }

    private int calculateUniqueSet(Integer element) {
        int countUniqSet = 0;
        for (int i = 0; i < WIDTH; i++) {
            if (Objects.equals(sideLine.get(i), element)) {
                countUniqSet++;
            }
        }
        return countUniqSet;
    }

    public void checkedHorizontalWalls(int row) {
        for (int i = 0; i < WIDTH; i++) {
            if (calculateHorizontalWalls(sideLine.get(i), row) == 0) {
                hWalls.get(row).set(i, false);
            }
        }
    }

    private int calculateHorizontalWalls(Integer element, int row) {
        int countHorizontalWalls = 0;
        for (int i = 0; i < WIDTH; i++) {
            if (Objects.equals(sideLine.get(i), element) && !hWalls.get(row).get(i)) {
                countHorizontalWalls++;
            }
        }
        return countHorizontalWalls;
    }

    public void preparingNewLine(int row) {
        for (int i = 0; i < WIDTH; i++) {
            if (hWalls.get(row).get(i)) {
                sideLine.set(i, 0);
            }
        }
    }

    public void addingEndLine() {
        assignUniqueSet();
        addingVerticalWalls(HEIGHT - 1);
        checkedEndLine();
    }

    private void checkedEndLine() {
        for (int i = 0; i < WIDTH - 1; i++) {
            if (!Objects.equals(sideLine.get(i), sideLine.get(i + 1))) {
                vWalls.get(HEIGHT - 1).set(i, false);
                mergeSet(i, sideLine.get(i));
            }
            hWalls.get(HEIGHT - 1).set(i, true);
        }
        hWalls.get(HEIGHT - 1).set(WIDTH - 1, true);
    }
}
