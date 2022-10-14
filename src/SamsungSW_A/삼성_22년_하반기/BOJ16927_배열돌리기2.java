package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ16927_배열돌리기2 {

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};

    private static int N, M, R, cnt;
    private static int[][] map;

    private static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        stack = new Stack<>();
        cnt = Math.min(N, M) / 2;

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = cnt - 1; i >= 0; --i) {
            int a = (N - i * 2) * (M - i * 2);
            int b = (N - (i + 1) * 2) * (M - (i + 1) * 2);
            stack.add(a - b);
        }

        rotate();

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                sb.append(map[y][x]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void rotate() {
        for (int i = 0; i < cnt; ++i) {
            int idx = R % stack.pop();
            while (idx-- > 0) {
                int y = i;
                int x = i;
                int d = 0;
                int tmp = map[y][x];

                while (true) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (!isIn(i, ny, nx)) {
                        d = (d + 1) % 4;
                        continue;
                    }

                    if (ny == i && nx == i) {
                        break;
                    }

                    map[y][x] = map[ny][nx];
                    y = ny;
                    x = nx;
                }

                map[i + 1][i] = tmp;
            }
        }
    }

    public static boolean isIn(int i, int y, int x) {
        return y >= i && x >= i && y < N - i && x < M - i;
    }
}


