package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _8_BOJ14502_연구소 {

    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = Integer.MIN_VALUE;

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(3, 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, int startIdx) {
        if (cnt == 0) {
            int[][] copyMap = copyMap();
            for(int y = 0; y < N; ++y){
                for(int x = 0; x < M; ++x){
                    if(copyMap[y][x] == 2){
                        spread(copyMap, y, x);
                    }
                }
            }
            ans = Math.max(ans, getCntSafeArea(copyMap));
            return;
        }

        for (int i = startIdx; i < N * M; ++i) {
            int y = i / M;
            int x = i % M;
            if (map[y][x] != 0) {
                continue;
            }
            map[y][x] = 1;
            combination(cnt - 1, i + 1);
            map[y][x] = 0;
        }
    }

    public static int[][] copyMap(){
        int[][] copyMap = new int[N][];

        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }

        return copyMap;
    }

    public static void spread(int[][] copyMap, int y, int x){
        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx) || copyMap[ny][nx] != 0) continue;

            copyMap[ny][nx] = 2;
            spread(copyMap, ny, nx);
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static int getCntSafeArea(int[][] copyMap) {
        int cnt = 0;
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                if (copyMap[y][x] == 0) {
                    ++cnt;
                }
            }
        }
        return cnt;
    }

    public static void print() {
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }
}
