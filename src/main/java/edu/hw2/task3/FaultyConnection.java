package edu.hw2.task3;

public class FaultyConnection implements Connection{
    @Override
    public void execute(String command) throws ConnectionException {
        throw  new ConnectionException("fault connection");
    }

    @Override
    public void close() throws Exception  {
        System.out.println("FaultyConnection closed");
    }
}
