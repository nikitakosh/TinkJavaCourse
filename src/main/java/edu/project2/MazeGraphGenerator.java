package edu.project2;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class MazeGraphGenerator {

    private MazeGraphGenerator() {}

    @NotNull
    public static Cell[][] generateGraph(int height, int width,
                                         List<List<Boolean>> bottomWalls, List<List<Boolean>> rightWalls) {
        Cell[][] graph = new Cell[height][width];
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
                graph[i][j] = new Cell(i, j, directions);
            }
        }
        return graph;
    }
}
