package calculator;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) {

        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        IntStream.range(0, 12).forEach(n -> executorService.execute(() ->
        {
            String threadName = Thread.currentThread().getName();
            int pips = Person.rollTheDice(); // pips는 주사위눈을 뜻한다.
            System.out.println("던지는 사람(스레드):" + threadName + ", 주사위 눈: " + pips);

//            System.out.println("스레드 풀 사이즈: " + executorService.getPoolSize());
//            System.out.println("Queue 사이즈: " + executorService.getQueue().size());

        }));
        executorService.shutdown();
    }
}
