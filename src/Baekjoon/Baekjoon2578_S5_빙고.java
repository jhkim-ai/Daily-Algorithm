package Baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon2578_S5_빙고 {

    static final int N = 5;
    static int[][] map;
    static int[][] call;
    static boolean[][] visited;
    static int ans;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        map = new int[N][N];
        call = new int[N][N];
        visited = new boolean[N][N];
        ans = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                call[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        all:
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int num = call[y][x];
                ans++;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == num) {
                            visited[i][j] = true;
                            if (isBingo())
                                break all;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    static boolean isBingo() {
        int cnt = 0;
        int line = 0;

        // 1. 가로 라인
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x]) cnt++;
            }
            if (cnt == 5) line++;
            cnt = 0;
        }

        // 2. 세로 라인
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (visited[y][x]) cnt++;
            }
            if (cnt == 5) line++;
            cnt = 0;
        }

        // 3. 오른쪽 하향 대각선
        for (int d = 0; d < 5; d++) {
            if (visited[d][d]) cnt++;
        }
        if (cnt == 5) line++;
        cnt = 0;

        // 4. 왼쪽 하향 대각선
        for (int d = 0; d < 5; d++) {
            if (visited[d][4 - d]) cnt++;
        }
        if (cnt == 5) line++;
        cnt = 0;

        if (line >= 3) return true;
        return false;
    }

    static String input = "11 12 2 24 10\n" +
            "16 1 13 3 25\n" +
            "6 20 5 21 17\n" +
            "19 4 8 14 9\n" +
            "22 15 7 23 18\n" +
            "5 10 7 16 2\n" +
            "4 22 8 17 13\n" +
            "3 18 1 6 25\n" +
            "12 19 23 14 21\n" +
            "11 24 9 20 15";
}