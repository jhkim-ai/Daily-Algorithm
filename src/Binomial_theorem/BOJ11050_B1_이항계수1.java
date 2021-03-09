package Binomial_theorem;

import java.io.*;
import java.util.*;

public class BOJ11050_B1_이항계수1 {

    static int N, M;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int ans = comb(N, M);
        System.out.println(ans);
    }

    // (1) 이항정리 = Combination 계산
    static int comb(int n, int k) {
        if (k == 0 || n == k)
            return 1;
        if (k > (n / 2))
            k = n - k;

        int a = 1;
        int b = 1;
        for (int i = 1; i <= k; i++) {
            a *= n--;
            b *= i;
        }

        return a/b;
    }

    static String input = "5 2";
}
