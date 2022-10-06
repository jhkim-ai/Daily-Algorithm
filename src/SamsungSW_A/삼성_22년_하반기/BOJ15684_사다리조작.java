package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684_사다리조작 {
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private static int N, M, H, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N];
        ans = MAX_VALUE;

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            map[a][b] = 1;
            map[a][b+1] = -1;
        }

        for(int i = 0; i <= 3; ++i){
            combination(i, new int[i], 0);
        }

        if(ans == MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx) {
        if(cnt == 0){

            int len = selected.length;
            if(len > 1){
                for(int i = 0; i < len - 1; ++i) {
                    if(selected[i+1] - selected[i] == 1) return;
                    int y = selected[i] / N;
                    int x = selected[i] % N;
                    if(map[y][x + 1] == 1) return;
                }
            }

            int[][] copyMap = getCopyMap();
            draw(copyMap, selected);

            if(!isRight(copyMap)) return;

            ans = Math.min(ans, len);
            return;
        }

        for(int i = startIdx; i < N * H; ++i) {
            if(i % N == N - 1) continue;
            selected[selected.length - cnt] = i;
            combination(cnt - 1, selected, i + 1);
        }
    }

    public static boolean isRight(int[][] copyMap) {

        for(int x = 0; x < N; ++x) {

            int[][] tmpMap = getCopyMap();
            int ny = 0;
            int nx = x;
            while(true) {

                if(!isIn(ny, nx)) break;

                if(copyMap[ny][nx] != 0) {

                    nx += copyMap[ny][nx];
                    tmpMap[ny][nx] = 3;
                    ny++;
                    continue;
                }

                ++ny;
            }

            if(x != nx) return false;
        }

        return true;
    }

    public static void draw(int[][] copyMap, int[] selected) {
        for(int num : selected) {
            int y = num / N;
            int x = num % N;

            copyMap[y][x] = 1;
            copyMap[y][x+1] = -1;
        }
    }

    public static int[][] getCopyMap() {
        int[][] copyMap = new int[H][];

        for(int y = 0; y < H; ++y) {
            copyMap[y] = map[y].clone();
        }

        return copyMap;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < H && x < N;
    }
}
