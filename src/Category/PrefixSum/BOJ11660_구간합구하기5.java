package Category.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11660_구간합구하기5 {

    private static int N, M;
    private static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new long[N][N];

        long before = 0;
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken()) + before + 0l;
                before = map[y][x];
            }
            System.out.println(Arrays.toString(map[y]));
        }

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            System.out.println(x1 + ", " + y1 + ", " + x2 + ", " + y2);

            long ans = 0;
            if (x1 == 0 && y1 == 0) {
                ans = map[x2][y2];
            } else if (x1 == x2 && y1 == y2) {
                if(y1==0) {
                    --x1;
                    y1 = N - 1;
                }
                ans = map[x2][y2] - map[x1][y1];
            } else if (x1 == N - 1 && y1 == 0) {
                ans = map[x2][y2] - map[--x1][N - 1];
            } else {
                if(y1 == 0){
                    y1 = N-1;
                } else {
                    --y1;
                }
                ans = map[x2][y2];
                ans -= map[x2][y1];
                ans += map[x1][y2];
                ans -= map[x1][y1];
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
