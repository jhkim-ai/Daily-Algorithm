package StepByStep.day210315;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ1932_S1_정수삼각형 {

    static int N;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        // ---------- 데이터 입력 ---------- //
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < i+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // ---------- 알고리즘 시작 ---------- //
        // Idea. 삼각형의 아래에서 위로 가는 (Bottom-Up) 방식으로 최댓값만 찾아 더해 나가자

        // 1. dp(Memoization)의 N 번째 줄 초기화
        for (int i = 0; i < N; i++) {
            dp[N-1][i] = map[N-1][i];
        }

        // 2. 아래서부터 최댓값만을 더해 위로 올라간다.
        for (int i = N-1; i >0 ; i--) {
            for (int j = 0; j < i; j++) {
                dp[i-1][j] = map[i-1][j]+Math.max(dp[i][j], dp[i][j+1]);

                // 참고! 순간순간 변화는 모습 출력
//                for(int[] print : dp){
//                    System.out.println(Arrays.toString(print));
//                }
//                System.out.println("==============");
            }
        }

        // 3. 출력
        System.out.println(dp[0][0]);
    }

    static String input = "1\n" +
            "0";
}
