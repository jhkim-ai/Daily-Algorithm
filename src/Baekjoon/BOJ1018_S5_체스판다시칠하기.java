package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1018_S5_체스판다시칠하기 {

    static int N, M;
    static int minAnswer;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        minAnswer = 1000;

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

        }

        int checkN = N - 7;
        int checkM = M - 7;

        for (int y = 0; y < checkN; y++) {
            for (int x = 0; x < checkM; x++) {
                check(y, x);
            }
        }
        System.out.println(minAnswer);
    }

    static void check(int y, int x) {
        char flag = map[y][x];
        int cnt = 0;
        for (int dy = 0; dy < 8; dy++) {
            for (int dx = 0; dx < 8; dx++) {
                if (flag != map[y + dy][x + dx]) {
                    cnt++;
                }
                flag = flag == 'W' ? 'B' : 'W';
            }
            flag = flag == 'W' ? 'B' : 'W';
        }

        // 맨 왼쪽 위쪽의 칸이 B(흑), W(백) 일 경우의 최솟값.
        // 총 64개의 Grid 에서 흑으로 시작했을 때, 바뀌어야하는 수가 cnt 라면,
        // 백으로 시작했을 때, 바꿔야하는 수는 "64-cnt" 이다.

        // cnt = Math.min(cnt, 64-cnt);
        minAnswer = Math.min(cnt, minAnswer);
    }
}
