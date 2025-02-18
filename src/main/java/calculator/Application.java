package calculator;

import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) {
        Person person = new Person();
        // dosometing() 호출하여 추가 주사위 굴리기 작업 5회 실행
        for (int i = 1; i <= 10; i++) {
            performRoll(person, i);
        }
        person.shutdownExecutor();
    }

    private static void performRoll(Person person, int rollNumber) {
        System.out.println("주사위 굴리기 작업 " + rollNumber + " 실행");
        Future<Integer> additionalRoll = person.rollTheDice();
        try {
            int result = additionalRoll.get();
            System.out.println("주사위 눈(주사위 수행횟수" + rollNumber + "): " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
