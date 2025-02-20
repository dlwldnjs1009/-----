package calculator;

import java.util.Random;

/**
 * RandomNumber 클래스는 RandomUtil 인터페이스를 구현한다.
 * 이 클래스는 java.util.Random을 사용하여 1부터 주어진 bound까지의 난수를 생성한다.
 */
public class RandomNumber implements RandomUtil {
    private static final Random random = new Random();

    /**
     * 주어진 bound 미만의 정수 난수를 생성한 후, 1을 더하여 1부터 bound까지의 난수를 반환한다.
     *
     * @param bound 난수의 상한값
     * @return 1부터 bound까지의 난수
     */
    @Override
    public int generate(int bound) {
        return random.nextInt(bound) + 1;
    }

}
