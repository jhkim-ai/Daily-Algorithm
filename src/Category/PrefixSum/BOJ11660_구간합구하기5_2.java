package Category.PrefixSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660_구간합구하기5_2 {

    private static int N, M;
    private static int[] sum1;
    private static int[][] sum2;
    private static int[][] sum3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum1 = new int[(1 << 20) + 1];
        sum2 = new int[N][N];
        sum3 = new int[N+1][N+1];

        // ---- Solution 3
        for(int y = 1; y <= N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 1; x <= N; ++x){
                sum3[y][x] = Integer.parseInt(st.nextToken());
                sum3[y][x] += sum3[y-1][x] + sum3[y][x-1] - sum3[y-1][x-1];
            }
        }

        for(int m = 0; m < M; ++m){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(dp(x1, y1, x2, y2)).append("\n");
        }
//        ---- Solution 2
//        int before = 0;
//        for(int y = 0 ; y < N; ++y){
//            st = new StringTokenizer(br.readLine());
//            for(int x = 0 ; x < N; ++x){
//                sum1[y*N + x%N] = Integer.parseInt(st.nextToken()) + before;
//                before = sum1[y*N + x%N];
//            }
//        }
//        ---- Solution 1
//        for(int y = 0; y < N; ++y){
//            st = new StringTokenizer(br.readLine());
//            sum2[y][0] = Integer.parseInt(st.nextToken());
//            for(int x = 1 ; x < N; ++x){
//                sum2[y][x] = sum2[y][x-1] + Integer.parseInt(st.nextToken());
//            }
//        }

        /*for(int m = 0; m < M; ++m){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            // Solution2
            sb.append(arrayPrefixSum(x1, y1, x2, y2)).append("\n");

            // Solution1
            // sb.append(prefixSum(x1, y1, x2, y2)).append("\n");
        }*/
        System.out.println(sb);
    }

    public static int dp(int x1, int y1, int x2, int y2){
        int ans = sum3[x2][y2] - sum3[x2][y1-1] - sum3[x1-1][y2] + sum3[x1-1][y1-1];
        return ans;
    }

    public static int arrayPrefixSum(int x1, int y1, int x2, int y2){
        int val = 0;
        for(int x = x1; x <= x2; ++x){
            val += sum1[x*N + y2%N];
            if(x*N + y1%N == 0){
                continue;
            }
            val -= sum1[x*N+y1%N - 1];
        }
        return val;
    }

    // x: 행, y: 열
    public static int prefixSum(int x1, int y1, int x2, int y2){
        int val = 0;
        for(int x = x1; x <= x2; ++x){
            if(y1 != 0){
                val -= sum2[x][y1-1];
            }
            val += sum2[x][y2];
        }
        return val;
    }
}
