package calculator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class Person {
    private final ExecutorService executor;

    public Person(ExecutorService executor) {
        this.executor = executor;
    }

    public CompletableFuture<Integer> rollTheDice() {
        Dice dice = Dice.createDice(new RandomNumber());
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("rollTheDice 실행 중인 스레드: " + Thread.currentThread().getName());
            return dice.roll();
        }, executor);
    }

    public void shutdownExecutor() {
        executor.shutdown();
    }
}
