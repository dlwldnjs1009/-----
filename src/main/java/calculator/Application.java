package calculator;

import static calculator.ExperimentRunner.runExperiment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("실험: SingleThreadExecutor");
        runExperiment(Executors.newSingleThreadExecutor());

        System.out.println("실험: FixedThreadPool (10 threads)");
        runExperiment(Executors.newFixedThreadPool(10));

        System.out.println("실험: CachedThreadPool");
        runExperiment(Executors.newCachedThreadPool());
    }


}
