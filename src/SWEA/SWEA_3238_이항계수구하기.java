package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_3238_이항계수구하기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            long n = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            long[] fac = new long[p + 1];
            fac[0] = 1;
            for (int i = 1; i <= p; i++) {
                fac[i] = (fac[i - 1] * i) % p;
            }

            long ans = 1l;
            while (n > 0 || r>0) {  // n이 p진수로 마칠 때까지 진행
                int x = (int) (n % p);
                int y = (int) (r % p);
                if(x < y){
                    ans = 0;
                    break;
                }

                long top = fac[x] % p;
                long bottom = (fac[x - y] * fac[y]) % p;

                long reBottom = fermat(bottom, p - 2, p) % p;
                ans = (ans * ((top * reBottom) % p)) % p;

                n /= p;
                r /= p;
            }

            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    static long fermat(long a, int b, int p) {
        if (b == 0)
            return 1l;

        long half_A = fermat(a, b / 2, p);

        if (b % 2 == 0)
            return ((half_A % p) * (half_A % p)) % p;
        else
            return (((a * half_A) % p) * (half_A % p)) % p;
    }

}
