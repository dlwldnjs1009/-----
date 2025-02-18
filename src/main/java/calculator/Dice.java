package calculator;

public class Dice {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 6;

    private int number;
    private RandomUtil randomUtil;

    private Dice(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    public static Dice createDice(RandomUtil randomUtil) {
        return new Dice(randomUtil);
    }

    // TODO: 랜덤한 수를 다이스에 주입시키자
    public int roll() {
        number = randomUtil.generate(MAX_NUMBER);
        return number;
    }

    public int getNumber() {
        return number;
    }

}
