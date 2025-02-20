package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExperimentRunner {

    public static void runExperiment(ExecutorService executor) {
        Person person = new Person(executor);
        AtomicInteger taskCount = new AtomicInteger(0);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        long deadline = startTime + 1000;

        while (System.currentTimeMillis() < deadline) {
            taskCount.incrementAndGet();
            CompletableFuture<Integer> future = person.rollTheDice();
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        System.out.println("1초 동안 스레드에 할당된 총 작업 수 (작업 완료 여부와 상관없이): " + taskCount.get());
        person.shutdownExecutor();
    }

}
