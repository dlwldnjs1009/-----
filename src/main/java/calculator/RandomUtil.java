package calculator;

/**
 * RandomUtil 인터페이스는 난수를 생성하는 메서드를 정의한다.
 * 이 인터페이스는 함수형 인터페이스로, 람다 표현식에서 사용할 수 있다.
 */
@FunctionalInterface
public interface RandomUtil {
    /**
     * 주어진 상한값(bound) 미만의 난수를 생성하여 반환한다.
     *
     * @param bound 난수의 상한값
     * @return 생성된 난수
     */
    int generate(int bound);
}
