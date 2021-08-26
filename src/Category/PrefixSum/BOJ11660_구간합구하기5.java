package Category.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660_구간합구하기5 {

    private static int N, M;

    private static long[][] originMap;
    private static long[][] prefixSumMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        originMap = new long[N][N];
        prefixSumMap = new long[N][N];

        long before = 0l;
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                int num = Integer.parseInt(st.nextToken());
                originMap[y][x] = num + 0l;
                prefixSumMap[y][x] = before + num + 0l;
                before = prefixSumMap[y][x];
            }
        }

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            sb.append(prefixSum(x1, y1, x2, y2)).append("\n");
        }
        System.out.println(sb);
    }

    // x: 행, y: 열,
    public static long prefixSum(int x1, int y1, int x2, int y2) {
        long ans = 0l;
        boolean isRowChange = false;
        if (x2 == 0 && y2 == 0) {
            return prefixSumMap[y2][x2];
        }
        if(x1 == x2 && y1 == y2){
            if(y1 != 0){
                return prefixSumMap[x2][y2] - prefixSumMap[x2][y2-1];
            } else {
                return prefixSumMap[x2][y2] - prefixSumMap[x2-1][N-1];
            }
        }
        if (x1 == 0 && y1 == 0) {
            ans = prefixSumMap[x1][y2];
            for(int r = x1; r < x2; ++r){
                ans -= prefixSumMap[r][N - 1];
                ans += prefixSumMap[r+1][y2];
            }
            return ans;
        }
        if (y1 == 0) {
            isRowChange = true;
            y1 = N - 1;
        } else {
            --y1;
        }
        for(int r = x1; r <= x2; ++r) {
            ans += prefixSumMap[r][y2];
            int nr = r;
            if (isRowChange) {
                nr--;
            }
            ans -= prefixSumMap[nr][y1];
        }
        return ans;
    }
}
