package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5604_구간합 {
    static int T;
    static long A, B, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());

            ans = 0;
            long po = 1;    // 자릿수
            while (A <= B) {
                // 10 ~ 19 를 한 층으로 보자
                // B를 줄여 9자리로 맞추자
                while (B % 10 != 9 && A <= B) {
                    cal(B, po); // 자릿수 계산
                    B--;
                }
                // System.out.println(ans);
                if (A > B) break;

                // A를 늘리고 0자리로 맞추자
                while (A % 10 != 0 && A <= B) {
                    cal(A, po);
                    A++;
                }

                // System.out.println(ans);
                A /= 10;
                B /= 10;

                long m = (B - A + 1)*po;
                for (int i = 0; i < 10; i++)
                    ans += m*i;

                po *= 10;
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    // 자릿수 계산
    static void cal(long a, long b) {
        while (a > 0) {
            ans += (a % 10) * b;
            a /= 10;
        }
    }
}
