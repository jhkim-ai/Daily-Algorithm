package StepByStep.day211002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ10472_십자뒤집기 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int T, ans;
    private static char[][] map, copyMap;
    private static List<Integer> list;
    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            ans = Integer.MAX_VALUE;
            list = new ArrayList<>();
            map = new char[3][];

            for (int y = 0; y < 3; y++) {
                map[y] = br.readLine().toCharArray();
            }

            for (int i = 1; i < (1 << 9); ++i) {
                list.clear();
                for (int j = 0; j < 9; j++) {
                    if ((i & (1 << j)) > 0) {
                        list.add(j);
                    }
                }
                ans = Math.min(search(), ans);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int search() {
        int cnt = 0;
        copyMap = new char[3][];
        for (int y = 0; y < 3; y++) {
            copyMap[y] = map[y].clone();
        }

        if (isAllWhite()) {
            return cnt;
        }

        for (int idx : list) {
            ++cnt;
            int y = idx / 3;
            int x = idx % 3;

            changeColor(y, x);
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (!isIn(ny, nx)) {
                    continue;
                }
                changeColor(ny, nx);
            }

            if (isAllWhite()) {
                return cnt;
            }
        }

        return 10;
    }

    public static void changeColor(int y, int x) {
        copyMap[y][x] = copyMap[y][x] == '*' ? '.' : '*';
    }

    public static boolean isAllWhite() {
        int cnt = 0;
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (copyMap[y][x] == '.') {
                    ++cnt;
                }
            }
        }
        return cnt == 9;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < 3 && x < 3;
    }

    public static void print(){
        System.out.println("=============");
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

}
