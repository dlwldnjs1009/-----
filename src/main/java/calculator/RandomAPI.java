package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RandomAPI implements RandomUtil {

    @Override
    public int generate(int bound) {
        try {
            // RANDOM.ORG Integer Generator URL 구성
            String urlStr = "https://www.random.org/integers/?" +
                    "num=1" +              // 한 개의 정수 요청
                    "&min=1" +             // 최소값 1
                    "&max=" + bound +      // 최대값: bound
                    "&col=1" +             // 결과 컬럼 1개
                    "&base=10" +           // 10진수
                    "&format=plain" +      // 간결한 plain text 형식
                    "&rnd=new";            // 새로운 무작위 시드 사용
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");

            // HTTP 응답 코드 확인
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("RandomAPI returned non-OK response code: " + responseCode);
                return new RandomNumber().generate(bound);
            }

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String firstLine = reader.readLine();

            // 응답 첫 줄이 "Error:"로 시작하는지 확인
            if (firstLine != null && firstLine.startsWith("Error:")) {
                System.err.println("RandomAPI error: " + firstLine);
                reader.close();
                return new RandomNumber().generate(bound);
            }

            int result = Integer.parseInt(firstLine.trim());
            reader.close();
            return result;
        } catch (Exception e) {
            System.err.println("RandomAPI 호출 실패, fallback 실행: " + e.getMessage());
            return new RandomNumber().generate(bound);
        }
    }
}
