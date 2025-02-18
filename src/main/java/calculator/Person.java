package calculator;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Person {

    public Future<Integer> rollTheDice() {
        Dice dice = Dice.createDice(new RandomNumber());
        return CompletableFuture.supplyAsync(dice::roll);
    }


}
