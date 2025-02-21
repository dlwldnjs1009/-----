package calculator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    @DisplayName("주사위 굴리는 도중 예외 발생 테스트")
    void rollTheDiceExceptionTest() {
        assertThatThrownBy(() -> {
            Dice dice = Dice.createDice(bound -> {
                throw new RuntimeException("예외 발생");
            });
            dice.roll();
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("예외 발생");
    }

    @Test
    public void testRollNDice() {
        Person person = new Person(Executors.newSingleThreadExecutor());
        int diceCount = 5;
        List<Integer> results = person.rollNDice(diceCount).join();
        assertEquals(diceCount, results.size(), "결과 리스트의 크기는 입력한 주사위 개수와 같아야 합니다.");
        for (int result : results) {
            assertTrue(result >= 1 && result <= 6, "주사위 값은 1과 6 사이여야 합니다.");
        }
        person.shutdownExecutor();
    }
}