package edu.project2;

import java.util.List;

public interface Solver {
    List<Cell> solve(Maze maze, Coordinate start, Coordinate end);
}
