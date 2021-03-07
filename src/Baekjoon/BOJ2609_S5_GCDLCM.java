package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 실버5 최대공약수와 최소공배수
// cf) 유클리드 호제법
public class BOJ2609_S5_GCDLCM {

    static int N, M;

    public static void main(String[] args) throws Exception {
        earlyDay();

    }

    static void earlyDay() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N < M) {
            int tmp = N;
            N = M;
            M = tmp;
        }

        sb.append(GCD(N, M)).append("\n");
        sb.append(LCM(N, M));
        System.out.println(sb);
    }

    static int GCD(int n, int m) {
        if (n % m == 0)
            return m;

        return GCD(m, n % m);
    }

    static int LCM(int n, int m) {
        int lcm = (n / GCD(n, m)) * (m / GCD(n, m)) * GCD(n, m);
        return lcm;
    }
}
