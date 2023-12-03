package edu.task2;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class FixedThreadPool implements ThreadPool {
    private final int countThreads;
    private final BlockingQueue<Runnable> tasksQueue;
    private final List<Thread> threads;

    public FixedThreadPool(int countThreads) {
        this.countThreads = countThreads;
        this.tasksQueue = new LinkedBlockingQueue<>();
        this.threads = new ArrayList<>();
    }

    public static FixedThreadPool create(int countThreads) {
        return new FixedThreadPool(countThreads);
    }

    @Override
    public void start() {
        IntStream.range(0, countThreads).mapToObj(i -> new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Runnable task = tasksQueue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        })).forEach(thread -> {
            thread.start();
            threads.add(thread);
        });
    }

    @Override
    public void execute(Runnable runnable) {
        try {
            tasksQueue.put(runnable);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        threads.forEach(Thread::interrupt);
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
