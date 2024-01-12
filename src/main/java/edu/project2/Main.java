package edu.project2;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Main {


    public static final int HEIGHT_MAZE_ELLER = 5;
    public static final int WIDTH_MAZE_ELLER = 5;
    public static final int HEIGHT_MAZE_BINARY_TREE = 5;
    public static final int WIDTH_MAZE_BINARY_TREE = 5;
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        BinaryTreeMazeGenerator binaryTreeMazeGenerator = new BinaryTreeMazeGenerator();
        EllerMazeGenerator ellerMazeGenerator = new EllerMazeGenerator();
        Maze mazeEller = ellerMazeGenerator.generate(HEIGHT_MAZE_ELLER, WIDTH_MAZE_ELLER);
        Maze mazeBinaryTree = binaryTreeMazeGenerator.generate(HEIGHT_MAZE_BINARY_TREE, WIDTH_MAZE_BINARY_TREE);
        ParallelDFSSolver dfsSolver = new ParallelDFSSolver();
        BFSSolver bfsSolver = new BFSSolver();
        // find path for mazeEller by DFS
        List<Cell> pathDfsSolverForMazeEller = dfsSolver.solve(
                mazeEller, new Coordinate(0, 0), new Coordinate(HEIGHT_MAZE_ELLER - 1, WIDTH_MAZE_ELLER - 1)
        );
        // find path for mazeEller by BFS
        List<Cell> pathBfsSolverForMazeEller = bfsSolver.solve(
                mazeEller, new Coordinate(0, 0), new Coordinate(HEIGHT_MAZE_ELLER - 1, WIDTH_MAZE_ELLER - 1)
        );
        // find path for mazeBinaryTree by DFS
        List<Cell> pathDfsSolverForMazeBinaryTree = dfsSolver.solve(
                mazeBinaryTree, new Coordinate(0, 0),
                new Coordinate(HEIGHT_MAZE_BINARY_TREE - 1, WIDTH_MAZE_BINARY_TREE - 1));
        // find path for mazeBinaryTree by BFS
        List<Cell> pathBfsSolverForMazeBinaryTree = bfsSolver.solve(
                mazeBinaryTree, new Coordinate(0, 0),
                new Coordinate(HEIGHT_MAZE_BINARY_TREE - 1, WIDTH_MAZE_BINARY_TREE - 1)
        );
        MazeRender mazeRender = new MazeRender();
        LOGGER.info("\n" + mazeRender.render(mazeEller, pathDfsSolverForMazeEller));
        LOGGER.info("\n" + mazeRender.render(mazeEller, pathBfsSolverForMazeEller));
        LOGGER.info("\n" + mazeRender.render(mazeBinaryTree, pathDfsSolverForMazeBinaryTree));
        LOGGER.info("\n" + mazeRender.render(mazeBinaryTree, pathBfsSolverForMazeBinaryTree));
    }

}
