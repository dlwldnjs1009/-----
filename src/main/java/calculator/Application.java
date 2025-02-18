package calculator;

import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) {
        Person person = new Person();
        long start = System.nanoTime();
        Future<Integer> futurePips = person.rollTheDice();
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        dosometing(person); // 주사위 굴리는 동안 다른 작업을 수행

        try {
            int result = futurePips.get();
            System.out.println("주사위 눈: " + result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("주사위 굴리기 완료 후 " + retrievalTime + " msecs");
    }

    private static void dosometing(Person person) {
        System.out.println("dosometing() 시작: 다른 작업 수행 중, 동시에 두 번째 주사위 굴리기...");
        // 두 번째 비동기 주사위 굴리기
        Future<Integer> futureDice2 = person.rollTheDice();

        // dosometing()에서 다른 작업을 수행할 수 있음
        // (예: 간단한 지연 또는 로깅 작업 등)
        try {
            // 두 번째 주사위 결과를 받아서 출력
            int dice2 = futureDice2.get();
            System.out.println("dosometing()에서 두 번째 주사위 눈: " + dice2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
