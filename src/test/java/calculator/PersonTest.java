package calculator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
}