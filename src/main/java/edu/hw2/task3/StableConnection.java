package edu.hw2.task3;

public class StableConnection implements Connection{
    @Override
    public void execute(String command) {
        System.out.println("execute command: " + command);
    }

    @Override
    public void close() throws Exception{
        System.out.println("StableConnection closed");
    }
}
