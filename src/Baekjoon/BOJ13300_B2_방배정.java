package Baekjoon;

import java.util.*;
import java.io.*;

public class BOJ13300_B2_방배정 {

    static int N, K;
    static int[] cnt;
    static int ans;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cnt = new int[13];
        ans = 0;

        int s = 0;
        int y = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            cnt[(s*6) + y]++;
        }

        for (int i = 1; i < cnt.length; i++) {
            ans += cnt[i]/K;
            if(cnt[i] % K != 0)
                ans++;
        }
        System.out.println(ans);
    }

    static String input = "16 2\n" +
            "1 1\n" +
            "0 1\n" +
            "1 1\n" +
            "0 2\n" +
            "1 2\n" +
            "0 2\n" +
            "0 3\n" +
            "1 3\n" +
            "1 4\n" +
            "1 3\n" +
            "1 3\n" +
            "0 6\n" +
            "1 5\n" +
            "0 5\n" +
            "1 5\n" +
            "1 6";
}
