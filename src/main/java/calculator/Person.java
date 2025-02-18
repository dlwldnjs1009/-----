package calculator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Person {
    
    public Future<Integer> rollTheDice() {
        CompletableFuture<Integer> futureDice = new CompletableFuture<>();
        new Thread(() -> {
            Dice dice = Dice.createDice(new RandomNumber()); // 다른 스레드에서 비동기적으로 계산을 수행한다.
            futureDice.complete(dice.roll());
        }).start();
        return futureDice; // 계산 결과가 완료되길 기다리지 않고 Future를 반환.
    }
}
