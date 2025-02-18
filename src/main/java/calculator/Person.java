package calculator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Person {

    public Future<Integer> rollTheDice() {
        CompletableFuture<Integer> futureDice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Dice dice = Dice.createDice(new RandomNumber()); // 다른 스레드에서 비동기적으로 계산을 수행한다.
                futureDice.complete(dice.roll());
            } catch (Exception ex) {
                futureDice.completeExceptionally(ex); // 도중에 문제가 발생하면 발생한 에러를 포함시켜 Future를 종료.
            }
        }).start();
        return futureDice;
    }
}
