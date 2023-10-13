package edu.hw2.task3;

public class FaultyConnection implements Connection{
    @Override
    public void execute(String command) throws ConnectionException {
        throw  new ConnectionException(new Exception("fault connection"));
    }

    @Override
    public void close()  {
        System.out.println("FaultyConnection closed");
    }
}
