package calculator;

public class Person {

    public static int rollTheDice() {
        Dice dice = Dice.createDice(new RandomNumber());

        return dice.roll();
    }
}
