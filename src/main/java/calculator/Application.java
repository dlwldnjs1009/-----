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
//        System.out.println("실험: SingleThreadExecutor");
//        runExperiment(Executors.newSingleThreadExecutor());
//
//        System.out.println("실험: FixedThreadPool (10 threads)");
//        runExperiment(Executors.newFixedThreadPool(10));
//
//        System.out.println("실험: CachedThreadPool");
//        runExperiment(Executors.newCachedThreadPool());

        System.out.println("\n===== 멀티프로세싱 실험 =====");
        System.out.println("가설: 프로세스 수가 코어 수와 동일할 때, 백만 개의 주사위 굴리기를 가장 빠르게 수행할 것이다.");
        MultiProcessingExperimentRunner.runMultiProcessingExperiments();
    }


}
