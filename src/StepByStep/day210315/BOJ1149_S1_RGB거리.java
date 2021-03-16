package StepByStep.day210315;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ1149_S1_RGB거리 {

    static int N;
    static int[][] color;
    static final int Red = 0;
    static final int Green = 1;
    static final int Blue = 2;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        // ------------ 데이터 입력 ------------ //

        N = Integer.parseInt(br.readLine());
        color = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            color[i][Red] = Integer.parseInt(st.nextToken());
            color[i][Green] = Integer.parseInt(st.nextToken());
            color[i][Blue] = Integer.parseInt(st.nextToken());
        }

        // ------------ 알고리즘 시작 ------------ //

        // Memoization 시작
        // 모든 경우의 수를 마지막 집까지 더해간다.
        for (int i = 1; i < N; i++) {
            // 제약 사항 요약 : 인접한 번호의 집은 서로 다른 색을 칠해야 한다.
            // 1. N번째 집에 Red 를 칠할 경우, N-1번째 집의 Green 과 Blue 중 최소 값을 찾아야한다.
            // 2. N번째 집이 Blue 혹은 Green 을 칠할 경우, (1)번과 같다.
            color[i][Red] += Math.min(color[i-1][Green], color[i-1][Blue]);
            color[i][Green] += Math.min(color[i-1][Red], color[i-1][Blue]);
            color[i][Blue] += Math.min(color[i-1][Red], color[i-1][Green]);
        }

        // ------------ 정답 출력 ------------ //
        int val = Math.min(color[N-1][Red], color[N-1][Blue]);
        System.out.println(Math.min(val, color[N-1][Green]));
    }

    static String input="3\n" +
            "26 40 83\n" +
            "49 60 57\n" +
            "13 89 99";
}
