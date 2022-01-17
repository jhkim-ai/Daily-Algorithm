package StepByStep.day210315;

import java.io.BufferedReader;
import java.io.StringReader;

public class BOJ2579_S3_계단오르기 {

    static int N;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
//    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        // ----------- 데이터 입력 ----------- //

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N+1];
        
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // ----------- 알고리즘 시작 ----------- //

        dp[1] = arr[1]; // 10
        // 주의! 문제의 조건. N <= 300 (N은 자연수)
        // 예외 처리를 안하면, Error
        if(N>=2)
            dp[2] = arr[2] + dp[1]; // N = 2라면, 최댓값은 (1번째 + 2번째) 계단의 합
        
        for (int i = 3; i < N+1; i++) {
            // (1) 2번째 전 계단일 경우
            int twoStep = dp[i-2];
            // (2) 1번째 전 + 3번째 전 계단일 경우
            int oneStep = dp[i-3] + arr[i-1];

            // 두 가지 경우 중, 최댓값을 선택 후, i번째 계단의 점수를 더한다.
            dp[i] = Math.max(twoStep, oneStep) + arr[i];
        }

        // ----------- 출력 ----------- //
        System.out.println(dp[N]);
    }

    static String input = "6\n" +
            "10\n" +
            "20\n" +
            "15\n" +
            "25\n" +
            "10\n" +
            "20";
}
