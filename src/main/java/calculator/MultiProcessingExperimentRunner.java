package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MultiProcessingExperimentRunner {

    private static final int TOTAL_ROLLS = 1_000_000;

    public static void runMultiProcessingExperiments() {
        // Java virtual machine에서 사용할 수 있는 프로세서 수 반환
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("코어 수: " + cores);

        // fastestTime, fastestCase를 기록
        long fastestTime = Long.MAX_VALUE;
        String fastestCase = "";

        // 코어보다 작은 프로세스 수: 1, 2, 3, 4, 5, 6, 7, 8 (단, 실제 코어 수보다 작은 경우에만 실행)
        int[] lowerProcessCounts = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int count : lowerProcessCounts) {
            if (count < cores) {
                System.out.println("프로세스 수 (코어보다 작음): " + count + "개");
                long time = runExperimentForProcessCount(count);
                System.out.println("총 소요 시간: " + time + " ms\n");

                if (time < fastestTime) {
                    fastestTime = time;
                    fastestCase = "프로세스 수가 코어보다 작은 경우 (" + count + "개)";
                }
            }
        }

        // 코어와 동일한 경우 실행
        System.out.println("프로세스 수 (코어와 동일): " + cores + "개");
        long timeEqual = runExperimentForProcessCount(cores);
        System.out.println("총 소요 시간: " + timeEqual + " ms\n");
        if (timeEqual < fastestTime) {
            fastestTime = timeEqual;
            fastestCase = "프로세스 수가 코어와 동일한 경우 (" + cores + "개)";
        }

        // 코어보다 큰 프로세스 수: 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
        for (int count = 11; count <= 20; count++) {
            if (count > cores) {
                System.out.println("프로세스 수 (코어보다 많음): " + count + "개");
                long time = runExperimentForProcessCount(count);
                System.out.println("총 소요 시간: " + time + " ms\n");

                if (time < fastestTime) {
                    fastestTime = time;
                    fastestCase = "프로세스 수가 코어보다 많은 경우 (" + count + "개)";
                }
            }
        }

        // 가장 빠른 케이스 출력
        System.out.println("백만 개 주사위 굴리기에서 가장 빠른 경우: " + fastestCase + " (" + fastestTime + " ms)");
    }

    private static long runExperimentForProcessCount(int processCount) {
        int rollsPerProcess = TOTAL_ROLLS / processCount;
        int remainder = TOTAL_ROLLS % processCount;
        List<Process> processes = new ArrayList<>();

        long startTime = System.currentTimeMillis();
        try {
            for (int i = 0; i < processCount; i++) {
                // 마지막 프로세스에 남은 작업량 할당
                int rolls = (i == processCount - 1) ? rollsPerProcess + remainder : rollsPerProcess;
                ProcessBuilder pb = new ProcessBuilder("java", "-cp", getClassPath(),
                        "calculator.DiceRollWorker", String.valueOf(rolls));
                Process process = pb.start();
                processes.add(process);
            }
            // 각 프로세스의 출력을 읽으며 종료 대기
            for (Process process : processes) {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println("[Worker] " + line);
                }
                process.waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    // 현재 클래스패스를 반환하는 유틸 메서드 (환경에 따라 조정 필요)
    private static String getClassPath() {
        return System.getProperty("java.class.path");
    }

}
