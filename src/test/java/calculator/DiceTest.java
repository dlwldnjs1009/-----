package calculator;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DiceTest {

    private RandomUtil randomUtil;

    @BeforeEach
    public void setUp() {
        randomUtil = new RandomNumber();
    }

    @ParameterizedTest
    @DisplayName("주사위 랜덤수 생성 테스트")
    @CsvSource(value = {"1, 1", "2, 2", "3, 3", "4, 4", "5, 5", "6, 6"})
    public void rollDiceTest(int number, int expected) {
        Dice dice = Dice.createDice(bound -> number);
        assertThat(dice.roll()).isEqualTo(expected);
    }


}
