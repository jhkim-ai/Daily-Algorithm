package Homework;

import java.io.*;
import java.util.*;

public class BOJ1987_G4_알파벳 {

    static int Y, X;
    static char[][] map;
    static boolean[] visited = new boolean[26];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};    // 상, 하, 좌, 우
    static int ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {

        // -------- 데이터 입력 -------- //

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new char[Y][];
        for (int i = 0; i < Y; i++) {
            map[i] = br.readLine().toCharArray();
        }

        find(0, 0, 1);
        System.out.println(ans);

    }

    static void find(int y, int x, int cnt) {

        ans = Math.max(ans, cnt);
        int idx = map[y][x] - 65;
        visited[idx] = true;

        for (int d = 0; d < 4; d++) {   // 상, 하, 좌, 우
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (isIn(ny, nx) && !visited[map[ny][nx]-65]){
                find(ny, nx, cnt+1);
                visited[map[ny][nx]-65] = false;
            }
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < Y && 0 <= x && x < X;
    }

    static String input = "2 4\n" +
            "CAAB\n" +
            "ADCB";
}
