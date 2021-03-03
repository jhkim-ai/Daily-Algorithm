package Baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon10163_B1_색종이 {

    static int[][] visited;
    static int N;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        visited = new int[101][101];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            cnt++;
            st = new StringTokenizer(br.readLine());

            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            for (int y = startY; y < startY+h; y++) {
                for (int x = startX; x < startX+w; x++) {
                    visited[y][x] = cnt;
                }
            }
        }

        for (int c = 1; c <= cnt; c++) {
            int ans = 0;
            for (int i = 0; i < 101; i++) {
                for (int j = 0; j < 101; j++) {
                    if (visited[i][j] == c)
                        ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static String input = "2\n" +
            "0 0 10 10\n" +
            "2 2 6 6";
}
