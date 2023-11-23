package edu.hw7.task4;

import java.util.ArrayList;
import java.util.List;

public class ReportCreator {

    public static final int TEST1_COUNT_THREADS = 10;
    public static final int TEST1_COUNT_ITERATIONS = 10000000;
    public static final int TEST2_COUNT_ITERATIONS = 20000000;
    public static final int TEST3_COUNT_ITERATIONS = 100000000;
    public static final int TEST4_COUNT_ITERATIONS = 200000000;
    public static final int TEST5_COUNT_ITERATIONS = 1000000000;
    public static final int MYSELF_COUNT_THREADS = 4;
    public static final String ROW = "| %d | %d | %f |\n";
    public static final String HEADER_ROW = "|-|-|-|\n";

    public void testOnDifferentNumbersThreads() {
        MultiThreadMonteCarlo multiThreadMonteCarlo = new MultiThreadMonteCarlo();
        List<ThreadsInfo> infoThreads = new ArrayList<>();
        for (int i = 1; i <= TEST1_COUNT_THREADS; i++) {
            long start = System.nanoTime();
            double pi = multiThreadMonteCarlo.calculatePi(TEST1_COUNT_ITERATIONS, i);
            long finnish = System.nanoTime();
            infoThreads.add(new ThreadsInfo(i, pi, finnish - start));
        }
        List<PiValueInfo> infoPiValue = new ArrayList<>();
        for (int countIteration : List.of(TEST1_COUNT_ITERATIONS, TEST2_COUNT_ITERATIONS,
                TEST3_COUNT_ITERATIONS, TEST4_COUNT_ITERATIONS, TEST5_COUNT_ITERATIONS)) {
            long start = System.nanoTime();
            double pi = multiThreadMonteCarlo.calculatePi(countIteration, MYSELF_COUNT_THREADS);
            long finish = System.nanoTime();
            infoPiValue.add(new PiValueInfo(countIteration, pi, finish - start));
        }
        WriteUtils.writeInMarkdown(
                generateMarkdownTableInfoThreads(infoThreads)
                        + "\n"
                        + generateMarkdownTableInfoPiValue(infoPiValue)
        );
    }

    public String generateMarkdownTableInfoThreads(List<ThreadsInfo> infoThreads) {
        StringBuilder table = new StringBuilder();

        table.append("| Количество потоков | Время выполнения (мс) | Значение |\n");
        table.append(HEADER_ROW);

        for (ThreadsInfo infoThread : infoThreads) {
            table.append(String.format(ROW, infoThread.countThreads(), infoThread.duration(), infoThread.pi()));
        }

        return table.toString();
    }

    public String generateMarkdownTableInfoPiValue(List<PiValueInfo> infoPiValue) {
        StringBuilder table = new StringBuilder();

        table.append("| Количество итераций | Время выполнения (мс) | Значение |\n");
        table.append(HEADER_ROW);

        for (PiValueInfo info : infoPiValue) {
            table.append(String.format(ROW, info.countIteration(), info.duration(), info.pi()));
        }

        return table.toString();
    }
}
