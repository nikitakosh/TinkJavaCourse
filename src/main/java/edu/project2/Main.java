package edu.project2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EllerMazeGenerator ellerMazeGenerator = new EllerMazeGenerator();
        Maze maze = new EllerMazeGenerator().generate(10, 10);
        MazeRender ellerMazeRender = new MazeRender();
        System.out.println(ellerMazeRender.render(maze));
        DFSSolver dfsSolver = new DFSSolver();
        BFSSolver bfsSolver = new BFSSolver();
        List<Cell> pathDFS = dfsSolver.solve(maze, new Coordinate(0, 0), new Coordinate(9, 9));
        List<Cell> pathBFS = bfsSolver.solve(maze, new Coordinate(0, 0), new Coordinate(9, 9));
        System.out.println(ellerMazeRender.render(maze, pathDFS));
        System.out.println(ellerMazeRender.render(maze, pathBFS));
        BinaryTreeMazeGenerator binaryTreeMazeGenerator = new BinaryTreeMazeGenerator();
        Maze maze1 = binaryTreeMazeGenerator.generate(7, 20);
        System.out.println(ellerMazeRender.render(maze1));
    }
}
