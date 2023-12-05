package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BFSSolverTest {
    @Test
    @DisplayName("search path by bfs solver")
    public void searchPathByDFSSolver() {
        List<List<Boolean>> rightWalls = new ArrayList<>(List.of(
                List.of(true, true, false, true),
                List.of(true, false, true, true),
                List.of(false, true, false, true),
                List.of(false, false, false, true)
        ));
        List<List<Boolean>> bottomWalls = new ArrayList<>(List.of(
                List.of(false, false, false, true),
                List.of(false, false, false, false),
                List.of(true, true, false, true),
                List.of(true, true, true, true)
        ));
        Cell[][] graph =  MazeGraphGenerator.generateGraph(4, 4, bottomWalls, rightWalls);
        Maze maze = new Maze(4, 4, graph, rightWalls, bottomWalls);
        List<Cell> path = List.of(
                new Cell( 3, 3, List.of(Cell.Direction.LEFT)),
                new Cell(3, 2, List.of(Cell.Direction.TOP, Cell.Direction.LEFT, Cell.Direction.RIGHT)),
                new Cell(2, 2, List.of(Cell.Direction.TOP, Cell.Direction.BOTTOM, Cell.Direction.RIGHT)),
                new Cell(1, 2, List.of(Cell.Direction.TOP, Cell.Direction.LEFT, Cell.Direction.BOTTOM)),
                new Cell(1, 1, List.of(Cell.Direction.TOP, Cell.Direction.BOTTOM, Cell.Direction.RIGHT)),
                new Cell(2, 1, List.of(Cell.Direction.TOP, Cell.Direction.LEFT)),
                new Cell(2, 0, List.of(Cell.Direction.TOP, Cell.Direction.RIGHT)),
                new Cell(1, 0, List.of(Cell.Direction.TOP, Cell.Direction.BOTTOM)),
                new Cell(0, 0, List.of(Cell.Direction.BOTTOM))
        );
        Assertions.assertEquals(new BFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(3, 3)), path);
    }
}
