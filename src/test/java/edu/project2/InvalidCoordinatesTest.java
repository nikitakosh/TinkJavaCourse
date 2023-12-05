package edu.project2;

import edu.project2.exceptions.CoordinatesOutOfBoundException;
import edu.project2.exceptions.InvalidCoordinatesException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class InvalidCoordinatesTest {

    @Test
    @DisplayName("invalid coordinates test")
    public void invalidCoordinates() {
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
        Assertions.assertThrows(InvalidCoordinatesException.class, () -> new DFSSolver().solve(maze, new Coordinate(-1, 0), new Coordinate(2, 2)));
        Assertions.assertThrows(CoordinatesOutOfBoundException.class, () -> new DFSSolver().solve(maze, new Coordinate(5, 5), new Coordinate(2, 2)));
    }

}
