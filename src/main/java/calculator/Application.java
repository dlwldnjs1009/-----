package calculator;

import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) {
        Person person = new Person();
        long start = System.nanoTime();

        // 첫 번째 주사위 굴리기 작업 시작
        Future<Integer> firstRoll = person.rollTheDice();
        System.out.println("Invocation returned after " + ((System.nanoTime() - start) / 1_000_000) + " msecs");

        // dosometing() 호출하여 추가 주사위 굴리기 작업 5회 실행
        for (int i = 1; i <= 10; i++) {
            performAdditionalRoll(person, i);
        }

        // 첫 번째 작업 결과 출력
        try {
            int result = firstRoll.get();
            System.out.println("첫 번째 주사위 눈: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("주사위 굴리기 완료 후 " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
        person.shutdownExecutor();
    }

    private static void performAdditionalRoll(Person person, int rollNumber) {
        System.out.println("dosometing() 시작: 동시에 주사위 굴리기 작업 " + rollNumber + " 실행");
        Future<Integer> additionalRoll = person.rollTheDice();
        try {
            int result = additionalRoll.get();
            System.out.println("dosometing()에서 주사위 눈(" + rollNumber + "): " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
