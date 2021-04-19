package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5607_조합 {

    static final int MOD = 1234567891;

    static long[] fac;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        fac = new long[1000001];
        fac[0] = 1l;
        for (int i = 1; i < fac.length; i++) {
            fac[i] = (fac[i - 1] * i) % MOD;
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long top = fac[N] % MOD;                                     // 분모
            long bottom = (fac[R] % MOD) * (fac[N - R] % MOD) % MOD;     // 분자
            long changeBottom = fermat(bottom, MOD - 2);              // 페르마 소정리 a^(p-2) = 1/a

            sb.append(String.format("#%d %d\n", t, (top * changeBottom) % MOD));
        }
        System.out.println(sb);
    }

    static long fermat(long a, int p) {
        if (p == 0)     // 지수가 0일 경우 a^0 = 1
            return 1;

        // (a^p) % mod
        // = [(a^(p/2)) * (a^(p/2))] % mod
        // = [ (a^(p/2)) % mod ] * [ (a^(p/2)) % mod ]
        long half_A = fermat(a, p / 2);

        // 지수가 짝수 : a^p = a^(p/2) * a^(p/2)
        if (p % 2 == 0) {
            return ((half_A % MOD) * (half_A % MOD)) % MOD;
        }

        // 지수가 홀수 : a^p = a * a^(p/2) * a^(p/2)
        else {
            return (((a * half_A) % MOD) * (half_A % MOD)) % MOD;
        }
    }
}
