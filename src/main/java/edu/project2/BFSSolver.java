package edu.project2;

import edu.project2.exceptions.CoordinatesOutOfBoundException;
import edu.project2.exceptions.InvalidCoordinatesException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        coordinatesValidation(maze, start, end);
        Queue<Cell> queue = new ArrayDeque<>();
        queue.add(maze.graph()[start.row()][start.col()]);
        List<Cell> visitedCells = new ArrayList<>();
        List<Cell> path = new ArrayList<>();
        Map<Cell, Cell> hashMap = new HashMap<>();
        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            if (curr.col() == end.col() && curr.row() == end.row()) {
                path.add(curr);
                while (curr.col() != start.col() || curr.row() != start.row()) {
                    curr = hashMap.get(curr);
                    path.add(curr);
                }
                break;
            }
            if (curr.directions().contains(Cell.Direction.TOP)
                    && !visitedCells.contains(maze.graph()[curr.row() - 1][curr.col()])) {
                queue.add(maze.graph()[curr.row() - 1][curr.col()]);
                hashMap.put(maze.graph()[curr.row() - 1][curr.col()], curr);

            }
            if (curr.directions().contains(Cell.Direction.RIGHT)
                    && !visitedCells.contains(maze.graph()[curr.row()][curr.col() + 1])) {
                queue.add(maze.graph()[curr.row()][curr.col() + 1]);
                hashMap.put(maze.graph()[curr.row()][curr.col() + 1], curr);

            }
            if (curr.directions().contains(Cell.Direction.BOTTOM)
                    && !visitedCells.contains(maze.graph()[curr.row() + 1][curr.col()])) {
                queue.add(maze.graph()[curr.row() + 1][curr.col()]);
                hashMap.put(maze.graph()[curr.row() + 1][curr.col()], curr);
            }
            if (curr.directions().contains(Cell.Direction.LEFT)
                    && !visitedCells.contains(maze.graph()[curr.row()][curr.col() - 1])) {
                queue.add(maze.graph()[curr.row()][curr.col() - 1]);
                hashMap.put(maze.graph()[curr.row()][curr.col() - 1], curr);
            }
            visitedCells.add(curr);
        }
        return path;
    }

    private void coordinatesValidation(Maze maze, Coordinate start, Coordinate end) {
        if (start.col() > maze.width() || start.row() > maze.height()
                || end.col() > maze.width() || end.row() > maze.height()) {
            throw new CoordinatesOutOfBoundException("coordinate out bound");
        }
        if (start.col() < 0 || start.row() < 0 || end.col() < 0 || end.row() < 0) {
            throw new InvalidCoordinatesException("invalid coordinate exception");
        }
    }
}
