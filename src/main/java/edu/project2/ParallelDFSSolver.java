package edu.project2;

import edu.project2.exceptions.CoordinatesOutOfBoundException;
import edu.project2.exceptions.InvalidCoordinatesException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ParallelDFSSolver implements Solver {

    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        coordinatesValidation(maze, start, end);
        RecursiveSolver recursiveSolver =
                new RecursiveSolver(maze.graph()[start.row()][start.col()],
                        maze, start, end, new ArrayList<>(List.of()));
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(recursiveSolver);
        }
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

    private static class RecursiveSolver extends RecursiveTask<List<Cell>> {

        private final Cell currCell;
        private final Maze maze;
        private final Coordinate start;
        private final Coordinate end;
        private final List<Cell> visitedCells;

        private RecursiveSolver(Cell currCell, Maze maze, Coordinate start,
                                Coordinate end, List<Cell> visitedCells) {
            this.currCell = currCell;
            this.maze = maze;
            this.start = start;
            this.end = end;
            this.visitedCells = visitedCells;
        }

        @Override
        protected List<Cell> compute() {
            if (currCell.col() == end.col() && currCell.row() == end.row()) {
                return List.of(currCell);
            }
            boolean canDoNextStep = false;
            List<RecursiveSolver> recursiveSolvers = new ArrayList<>();
            visitedCells.add(currCell);
            for (Cell.Direction direction : currCell.directions()) {
                int newRow = currCell.row();
                int newCol = currCell.col();

                switch (direction) {
                    case TOP -> newRow--;
                    case RIGHT -> newCol++;
                    case BOTTOM -> newRow++;
                    case LEFT -> newCol--;
                    default -> {
                        return List.of();
                    }
                }

                if (!visitedCells.contains(maze.graph()[newRow][newCol])) {
                    canDoNextStep = true;
                    RecursiveSolver recursiveSolver =
                            new RecursiveSolver(maze.graph()[newRow][newCol], maze, start, end, visitedCells);
                    recursiveSolver.fork();
                    recursiveSolvers.add(recursiveSolver);
                }
            }
            if (!canDoNextStep) {
                return List.of();
            }
            List<Cell> currPath = new ArrayList<>(recursiveSolvers.stream()
                    .map(ForkJoinTask::join)
                    .flatMap(Collection::stream)
                    .toList());
            if (!currPath.isEmpty()) {
                currPath.add(currCell);
            }
            return currPath;

        }
    }
}
