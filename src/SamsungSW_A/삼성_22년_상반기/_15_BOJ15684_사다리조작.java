package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _15_BOJ15684_사다리조작 {

    private static int N, M, H;
    private static int size;
    private static int ladderNum;
    private static boolean isOk;

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int ans = -1;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        size = H * N;
        map = new int[H][N];
        isOk = false;
        ladderNum = 1;

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a - 1][b - 1] = ladderNum;
            map[a - 1][b] = ladderNum;
            ladderNum++;
        }

        for (int i = 0; i <= 3; ++i) {
            combination(i, new int[i], 0);
            if(isOk) {
                ans = i;
                break;
            }
        }

        System.out.println(ans);

    }

    public static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            int[][] copyMap = getCopyMap();
            int tmpSize = selected.length;
            int tmpLadderNum = ladderNum;
            if(tmpSize != 0) {
                for(int i = 0; i < tmpSize; ++i) {
                    int y = selected[i] / N;
                    int x = selected[i] % N;

                    if(copyMap[y][x] != 0) return;

                    copyMap[y][x] = tmpLadderNum;
                    copyMap[y][x + 1] = tmpLadderNum;
                    tmpLadderNum++;
                }
            }

            for (int idx = 0; idx < N; ++idx) {
                if (!checkLadderGame(idx, copyMap)) {
                    return;
                }
            }
            isOk = true;
            return;
        }

        for (int i = startIdx; i < size; ++i) {
            int y = i / N;
            int x = i % N;

            if (x == N - 1 || map[y][x] == 1 || map[y][x + 1] == 1) {
                continue;
            }

            selected[selected.length - cnt] = i;
            combination(cnt - 1, selected, i + 1);
        }
    }

    public static boolean checkLadderGame(int idx, int[][] copyMap) {
        int y = -1;
        int x = idx;

        while (true) {
            y++;
            if(y == H) break;
            if (copyMap[y][x] != 0) {
                int nx = x - 1;
                if (isIn(y, x + 1) && copyMap[y][x + 1] == copyMap[y][x]) {
                    nx = x + 1;
                }
                x = nx;
            }
        }

        if(x == idx) return true;
        return false;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < H && x < N;
    }

    public static int[][] getCopyMap() {
        int[][] copyMap = new int[H][];
        for (int y = 0; y < H; ++y) {
            copyMap[y] = map[y].clone();
        }

        return copyMap;
    }
}
