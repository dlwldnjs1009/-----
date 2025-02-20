package calculator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Person 클래스는 주사위 굴리기 작업을 Executor를 통해 비동기로 실행하는 역할을 한다.
 * 이 클래스는 주사위 객체를 생성하여 작업을 제출하며, 작업 실행 후 Executor를 종료할 수 있다.
 */
public class Person {
    private final ExecutorService executor;

    /**
     * Person 생성자는 지정된 ExecutorService 객체를 사용하여 Person 객체를 초기화한다.
     *
     * @param executor 작업 실행에 사용할 ExecutorService
     */
    public Person(ExecutorService executor) {
        this.executor = executor;
    }

    /**
     * 주사위를 굴리는 작업을 비동기로 실행한다.
     * 이 메서드는 Dice 객체를 생성하고, 이를 사용하여 주사위를 굴린 후 결과를 반환하는
     * CompletableFuture를 생성한다.
     *
     * @return 주사위를 굴린 결과를 포함하는 CompletableFuture
     */
    public CompletableFuture<Integer> rollTheDice() {
        Dice dice = Dice.createDice(new RandomNumber());
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("rollTheDice 실행 중인 스레드: " + Thread.currentThread().getName());
            return dice.roll();
        }, executor);
    }

    /**
     * ExecutorService를 종료한다.
     */
    public void shutdownExecutor() {
        executor.shutdown();
    }
}
