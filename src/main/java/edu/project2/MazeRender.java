package edu.project2;

import java.util.List;

public class MazeRender implements Render {

    public static final String BOTTOM_WALL = "+---";
    public static final String TRANSITION_TO_NEW_LINE = "+\n";
    public static final String LEFT_WALL = "|";
    public static final String RIGHT_WALL = "   |";
    public static final String RIGHT_PASSAGE = "    ";
    public static final String BOTTOM_PASSAGE = "+   ";
    public static final String MARK_WITH_RIGHT_WALL = " * |";
    public static final String MARK = " *  ";

    @Override
    public String render(Maze maze) {
        StringBuilder prettyMaze = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            if (row == 0) {
                prettyMaze.append(BOTTOM_WALL.repeat(Math.max(0, maze.width())));
                prettyMaze.append(TRANSITION_TO_NEW_LINE);
            }
            printRightWalls(maze, prettyMaze, row);
            printBottomWalls(maze, prettyMaze, row);
        }
        return String.valueOf(prettyMaze);
    }

    private void printRightWalls(Maze maze, StringBuilder prettyMaze, int row) {
        prettyMaze.append(LEFT_WALL);
        for (int col = 0; col < maze.width(); col++) {
            if (maze.rightWalls().get(row).get(col)) {
                prettyMaze.append(RIGHT_WALL);
            } else {
                prettyMaze.append(RIGHT_PASSAGE);
            }
        }
        prettyMaze.append("\n");
    }

    private void printBottomWalls(Maze maze, StringBuilder prettyMaze, int row) {
        for (int col = 0; col < maze.width(); col++) {
            if (maze.bottomWalls().get(row).get(col)) {
                prettyMaze.append(BOTTOM_WALL);
            } else {
                prettyMaze.append(BOTTOM_PASSAGE);
            }
        }
        prettyMaze.append(TRANSITION_TO_NEW_LINE);
    }

    @Override
    public String render(Maze maze, List<Cell> path) {
        StringBuilder prettyMazeWithPath = new StringBuilder();
        for (int row = 0; row < maze.height(); row++) {
            if (row == 0) {
                prettyMazeWithPath.append(BOTTOM_WALL.repeat(Math.max(0, maze.width())));
                prettyMazeWithPath.append(TRANSITION_TO_NEW_LINE);
            }
            printRightWallsWithPath(maze, path, prettyMazeWithPath, row);
            printBottomWalls(maze, prettyMazeWithPath, row);
        }
        return String.valueOf(prettyMazeWithPath);
    }

    private void printRightWallsWithPath(Maze maze, List<Cell> path, StringBuilder prettyMazeWithPath, int row) {
        prettyMazeWithPath.append(LEFT_WALL);
        for (int col = 0; col < maze.width(); col++) {
            if (maze.rightWalls().get(row).get(col)) {
                if (isPath(path, row, col)) {
                    prettyMazeWithPath.append(MARK_WITH_RIGHT_WALL);
                } else {
                    prettyMazeWithPath.append(RIGHT_WALL);
                }
            } else {
                if (isPath(path, row, col)) {
                    prettyMazeWithPath.append(MARK);
                } else {
                    prettyMazeWithPath.append(RIGHT_PASSAGE);
                }
            }
        }
        prettyMazeWithPath.append("\n");
    }


    private boolean isPath(List<Cell> path, int row, int col) {
        for (Cell cell : path) {
            if (cell.row() == row && cell.col() == col) {
                return true;
            }
        }
        return false;
    }
}
