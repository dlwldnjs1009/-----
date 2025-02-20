package calculator;

import static calculator.ExperimentRunner.runExperiment;

import java.util.concurrent.Executors;

/**
 * Application 클래스는 실험 실행을 위한 메인 클래스이다.
 * 이 클래스는 다양한 Executor를 사용하여 주사위 굴리기 작업을 제출하는 실험을 수행한다.
 */
public class Application {

    /**
     * 메인 메서드로, 다양한 Executor를 사용하여 실험을 실행한다.
     *
     * @param args 명령줄 인자
     */
    public static void main(String[] args) {
        System.out.println("실험: SingleThreadExecutor");
        runExperiment(Executors.newSingleThreadExecutor());

        System.out.println("실험: FixedThreadPool (10 threads)");
        runExperiment(Executors.newFixedThreadPool(10));

        System.out.println("실험: CachedThreadPool");
        runExperiment(Executors.newCachedThreadPool());
    }


}
