package edu.project2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
public class MazeGraphGenerator {

    private MazeGraphGenerator() {}

    @NotNull
    static Maze generateGraph(int height, int width, List<List<Boolean>> bottomWalls, List<List<Boolean>> rightWalls) {
        Cell[][] maze = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                List<Cell.Direction> directions = new ArrayList<>();
                if (i != 0 && !bottomWalls.get(i - 1).get(j)) {
                    directions.add(Cell.Direction.TOP);
                }
                if (j != 0 && !rightWalls.get(i).get(j - 1)) {
                    directions.add(Cell.Direction.LEFT);
                }
                if (!bottomWalls.get(i).get(j)) {
                    directions.add(Cell.Direction.BOTTOM);
                }
                if (!rightWalls.get(i).get(j)) {
                    directions.add(Cell.Direction.RIGHT);
                }
                maze[i][j] = new Cell(i, j, directions);
            }
        }
        return new Maze(height, width, maze, rightWalls, bottomWalls);
    }
}
