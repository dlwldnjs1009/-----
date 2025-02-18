package calculator;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) {
        System.out.println("주사위 결과: " + Person.rollTheDice());
    }
}
