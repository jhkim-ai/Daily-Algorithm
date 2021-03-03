package SWEA;

import java.io.*;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1868 {

    static int T, N, cnt;
    static char[][] map;
    static int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new StringReader(src));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            cnt = 0;
            boolean flag = false;
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] == '.') {

                        if (findMine(y, x, flag))
                            cnt++;
                        for (char[] a : map) {
                            System.out.println(Arrays.toString(a));
                        }
                        System.out.println("-------------");
                    }
                }
            }

//            for (int y = 0; y < N; y++) {
//                for (int x = 0; x < N; x++) {
//                    if (map[y][x] == '.') {
//                        cnt++;
//                    }
//                }
//            }

            sb.append("#").append(t).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    static boolean findMine(int y, int x, boolean flag) {
        Queue<Position> q = new LinkedList<>();

        for (int d = 0; d < deltas.length; d++) {
            int ny = y + deltas[d][0];
            int nx = x + deltas[d][1];
            if (isIn(ny, nx)) {
                if (map[ny][nx] == '*' && !flag) {
                    map[y][x] = 'C';
                    cnt++;
                    return false;
                } else if (map[ny][nx] == '*' && flag) {
                    map[y][x] = 'V';
                    return false;
                }

                q.offer(new Position(ny, nx));
            }
        }

        while (!q.isEmpty()) {
            Position tmp = q.poll();
            findMine(tmp.y, tmp.x, true); // (4,3) 과 (4,4)에서 무한루프
        }
        return true;
    }

    static class Position {
        int y;
        int x;

        public Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static String src = "1\n" +
            "5\n" +
            "..*..\n" +
            "..*..\n" +
            ".*..*\n" +
            ".*...\n" +
            ".*...";
}
