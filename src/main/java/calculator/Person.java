package calculator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Person {

    public Future<Integer> rollTheDice() {
        Dice dice = Dice.createDice(new RandomNumber());
        return CompletableFuture.supplyAsync(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("rollTheDice 실행 중인 스레드: " + threadName);
            return dice.roll();
        });
    }


}
