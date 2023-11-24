package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MazeRenderTest {

    @Test
    @DisplayName("maze render")
    public void mazeRender() {
        List<List<Boolean>> rightWalls = new ArrayList<>(List.of(
                List.of(true, true, true, true),
                List.of(false, false, true, true),
                List.of(false, true, false, true),
                List.of(false, false, true, true)
        ));
        List<List<Boolean>> bottomWalls = new ArrayList<>(List.of(
                List.of(false, false, false, false),
                List.of(true, true, false, false),
                List.of(true, false, false, false),
                List.of(true, true, true, true)
        ));
        Cell[][] graph =  MazeGraphGenerator.generateGraph(4, 4, bottomWalls, rightWalls);
        Maze maze = new Maze(4, 4, graph, rightWalls, bottomWalls);
        MazeRender mazeRender = new MazeRender();
        String prettyMaze = """
                +---+---+---+---+
                |   |   |   |   |
                +   +   +   +   +
                |           |   |
                +---+---+   +   +
                |       |       |
                +---+   +   +   +
                |           |   |
                +---+---+---+---+
                """;
        Assertions.assertEquals(mazeRender.render(maze), prettyMaze);
    }


    @Test
    @DisplayName("maze render with path")
    public void mazeRenderWithPath() {
        List<List<Boolean>> rightWalls = new ArrayList<>(List.of(
                List.of(true, true, true, true),
                List.of(false, false, true, true),
                List.of(false, true, false, true),
                List.of(false, false, true, true)
        ));
        List<List<Boolean>> bottomWalls = new ArrayList<>(List.of(
                List.of(false, false, false, false),
                List.of(true, true, false, false),
                List.of(true, false, false, false),
                List.of(true, true, true, true)
        ));
        Cell[][] graph =  MazeGraphGenerator.generateGraph(4, 4, bottomWalls, rightWalls);
        Maze maze = new Maze(4, 4, graph, rightWalls, bottomWalls);
        List<Cell> path = new DFSSolver().solve(maze, new Coordinate(0, 0), new Coordinate(3, 3));
        MazeRender mazeRender = new MazeRender();
        String prettyMaze = """
                +---+---+---+---+
                | * |   |   |   |
                +   +   +   +   +
                | *   *   * |   |
                +---+---+   +   +
                |       | *   * |
                +---+   +   +   +
                |           | * |
                +---+---+---+---+
                """;
        Assertions.assertEquals(mazeRender.render(maze, path), prettyMaze);
    }
}
