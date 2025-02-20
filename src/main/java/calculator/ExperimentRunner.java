package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ExperimentRunner 클래스는 1초 동안 Executor에 제출된 작업 수를 측정하는 실험을 수행한다.
 * 이 클래스는 Person 객체를 통해 주사위 굴리기 작업을 제출하며, 제출된 작업 수를 측정한다.
 */
public class ExperimentRunner {

    /**
     * 지정된 Executor를 사용하여 1초 동안 주사위 굴리기 작업을 제출하고,
     * 총 제출된 작업 수를 출력한다.
     * 작업이 1초 이후에 완료되더라도 제출 시점에 이미 할당된 작업으로 카운트한다.
     *
     * @param executor 작업 제출에 사용할 ExecutorService 객체
     */
    public static void runExperiment(ExecutorService executor) {
        Person person = new Person(executor);
        AtomicInteger taskCount = new AtomicInteger(0);
        List<CompletableFuture<Integer>> futures = new ArrayList<>();

        // 시작 시각 기록 및 1초 후 마감 시각 설정
        long startTime = System.currentTimeMillis();
        long deadline = startTime + 1000;

        // 1초 동안 작업을 제출한다.
        while (System.currentTimeMillis() < deadline) {
            taskCount.incrementAndGet();
            CompletableFuture<Integer> future = person.rollTheDice();
            futures.add(future);
        }

        // 제출된 모든 작업이 완료될 때까지 대기한다.
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        // 1초 동안 제출된 총 작업 수를 출력한다. (작업 완료 여부와 상관없이)
        System.out.println("1초 동안 스레드에 할당된 총 작업 수: " + taskCount.get());
        person.shutdownExecutor();
    }

}
