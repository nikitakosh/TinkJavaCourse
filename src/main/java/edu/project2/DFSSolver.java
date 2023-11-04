package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSSolver implements Solver {
    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        Stack<Cell> stack = new Stack<>();
        stack.add(maze.graph()[start.row()][start.col()]);
        List<Cell> visitedCells = new ArrayList<>();
        List<Cell> path = new ArrayList<>();
        while (!stack.isEmpty()) {
            Cell curr = stack.peek();
            if (curr.col() == end.col() && curr.row() == end.row()) {
                while (!stack.isEmpty()) {
                    path.add(stack.pop());
                }
                break;
            }
            if (curr.directions().contains(Cell.Direction.TOP)
                    && !visitedCells.contains(maze.graph()[curr.row() - 1][curr.col()])) {
                stack.push(maze.graph()[curr.row() - 1][curr.col()]);
            } else if (curr.directions().contains(Cell.Direction.RIGHT)
                    && !visitedCells.contains(maze.graph()[curr.row()][curr.col() + 1])) {
                stack.push(maze.graph()[curr.row()][curr.col() + 1]);

            } else if (curr.directions().contains(Cell.Direction.BOTTOM)
                    && !visitedCells.contains(maze.graph()[curr.row() + 1][curr.col()])) {
                stack.push(maze.graph()[curr.row() + 1][curr.col()]);
            } else if (curr.directions().contains(Cell.Direction.LEFT)
                    && !visitedCells.contains(maze.graph()[curr.row()][curr.col() - 1])) {
                stack.push(maze.graph()[curr.row()][curr.col() - 1]);
            } else {
                stack.pop();
            }
            visitedCells.add(curr);
        }
        return path;
    }
}
