package calculator;

/**
 * Dice 클래스는 주사위를 표현한다.
 * 이 클래스는 주사위의 최소/최대 값을 정의하며, RandomUtil을 사용하여 주사위를 굴린다.
 */
public class Dice {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 6;

    private int number;
    private RandomUtil randomUtil;

    /**
     * Dice 생성자는 RandomUtil 객체를 받아 주사위 객체를 초기화한다.
     *
     * @param randomUtil 난수 생성기
     */
    private Dice(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    /**
     * RandomUtil을 인자로 받아 새로운 Dice 객체를 생성하여 반환한다.
     *
     * @param randomUtil 난수 생성기
     * @return 생성된 Dice 객체
     */
    public static Dice createDice(RandomUtil randomUtil) {
        return new Dice(randomUtil);
    }

    /**
     * 주사위를 굴린다.
     * RandomUtil을 통해 난수를 생성하여 주사위의 값을 설정한 후 반환한다.
     *
     * @return 주사위의 눈 값
     */
    public int roll() {
        number = randomUtil.generate(MAX_NUMBER);
        return number;
    }
}
