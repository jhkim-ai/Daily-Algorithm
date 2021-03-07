package 오답_및_복습;

import java.io.*;
import java.util.*;

public class BOJ2615_오목 {

    static int[][] map = new int[19][19];
    static int[][] deltas = {{1, 0}, {0, 1}, {-1, 1}, {1, 1}};  // 아래, 오른쪽, 오른쪽위, 오른쪽아래
    static boolean[][][] visited = new boolean[19][19][4];
    static int cnt;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < 19; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        boolean check = false;
        all:for (int y = 0; y < 19; y++) {
            for (int x = 0; x < 19; x++) {
                if (map[y][x] != 0) {
                    for (int d = 0; d < 4; d++) {
                        cnt = 1;
                        if (!visited[y][x][d] && find(y, x, d)) {
                            ans.append(map[y][x]).append("\n").append(y + 1).append(" ").append(x + 1);
                            check = true;
                            break all;
                        }
                    }
                }
            }
        }

        if (!check)
            System.out.println(0);
        else
            System.out.println(ans);

    }

    static boolean find(int y, int x, int d) {
        visited[y][x][d] = true;
        boolean result = false;

        if(cnt == 5)
            result = true;

        int ny = y + deltas[d][0];
        int nx = x + deltas[d][1];
        if (isIn(ny, nx) && map[ny][nx] == map[y][x]) {
            cnt++;
            find(ny, nx, d);
        }
        return result;
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < 19 && 0 <= x && x < 19;
    }

    static String input = "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 1 2 0 0 2 2 2 1 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 1 2 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 1 2 2 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 1 1 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 2 1 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0";
}
