package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RandomAPITest {

    @Test
    public void testGenerate() {
        RandomUtil randomAPI = new RandomAPI();
        int bound = 6;
        int result = randomAPI.generate(bound);
        // 생성된 난수가 1 이상 bound 이하인지 확인
        assertTrue(result >= 1 && result <= bound, "난수는 1 이상 " + bound + " 이하여야 합니다.");
    }
}