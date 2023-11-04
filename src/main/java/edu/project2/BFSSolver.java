package edu.project2;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.List;


@SuppressWarnings("ImportOrder")
public class BFSSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
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
}
