package Formula.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Knapsack {

    private static int N, K;

    private static int[] W;
    private static int[] V;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        W = new int[N + 1];
        V = new int[N + 1];
        dp = new int[K + 1];

        for(int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            W[i] = w;
            V[i] = v;
        }

        for(int i = 1; i <= N; ++i) {
            for(int k = K; k >= 0; --k) {
                if(k - W[i] < 0) break;
                dp[k] = Math.max(dp[k], dp[k - W[i]] + V[i]);
            }
        }

        System.out.println(dp[K]);
    }

    private static String input = "4 7\n" +
            "6 13\n" +
            "4 8\n" +
            "3 6\n" +
            "5 12";

}
