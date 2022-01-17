package StepByStep.day210315;

import java.io.BufferedReader;
import java.io.StringReader;

// BOJ_2579 계단 오르기와 비슷한 문제
public class BOJ2156_S1_포도주시식 {

    static int N, ans;
    static int[] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        // --------- 데이터 입력 --------- //

        N = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        arr = new int[N+1];
        for (int i = 1; i <N+1 ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // --------- 알고리즘 시작 --------- //

        dp = new int[N+1];
        dp[1] = arr[1];
        if(N >=2)
            dp[2] = arr[2] + dp[1];

        for (int i = 3; i < N+1 ; i++) {
            // (1). oxo : 2번째 전의 포도주
            int second_ago = dp[i-2];
            // (2). oxoo : 1번째 전의 포도주
            int first_ago = arr[i-1] + dp[i-3];
            // (3). oox : 현재 포도주를 제외한 첫 번째 전과 두 번째 전
            int zero = dp[i-1];
            // (1)번과 (2)번 중 최댓값을 선택 후, dp[i]에 저장
            dp[i] = Math.max(Math.max(first_ago, second_ago) + arr[i], zero);
        }

        // --------- 출력 --------- //
        System.out.println(dp[N]);
    }

    static String input = "6\n" +
            "6\n" +
            "10\n" +
            "13\n" +
            "9\n" +
            "8\n" +
            "1";

}
