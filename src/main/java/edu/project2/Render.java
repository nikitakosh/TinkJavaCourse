package edu.project2;

import java.util.List;

public interface Render {
    String render(Maze maze);

    String render(Maze maze, List<Cell> path);
}
