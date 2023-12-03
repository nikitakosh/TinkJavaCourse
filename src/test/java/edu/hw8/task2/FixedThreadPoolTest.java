package edu.hw8.task2;

import edu.task2.FixedThreadPool;
import org.junit.After;
import org.junit.jupiter.api.*;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class FixedThreadPoolTest {
    private FixedThreadPool fixedThreadPool;

    @BeforeEach
    public void setUp() {
        fixedThreadPool = FixedThreadPool.create(4);
        fixedThreadPool.start();
    }

    @AfterEach
    public void close() {
        fixedThreadPool.close();
    }

    @Test
    @DisplayName("execute tasks")
    public void executeTasks() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(4);
        Runnable task = () -> {
            IntStream.range(2, 101).reduce(1, Integer::sum);
            countDownLatch.countDown();
        };
        for(int i = 0; i < 4; i++) {
            fixedThreadPool.execute(task);
        }
        Assertions.assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }

    @Test
    @DisplayName("execute tasks more than threads")
    public void ExecuteTasksMoreThanThreads () throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Runnable task = () -> {
            IntStream.range(2, 101).reduce(1, Integer::sum);
            countDownLatch.countDown();
        };
        for(int i = 0; i < 10; i++) {
            fixedThreadPool.execute(task);
        }
        Assertions.assertTrue(countDownLatch.await(5, TimeUnit.SECONDS));
    }

}
