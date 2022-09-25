package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14501_퇴사 {

    private static int N, ans;
    private static int[] t, p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        t = new int[N];
        p = new int[N];
        ans = 0;

        for(int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; ++i) {
            getMaxProfit(i, 0);
        }

        System.out.println(ans);
    }

    public static void getMaxProfit(int idx, int sum) {
        if(idx  + t[idx] > N) {
            return;
        }

        sum += p[idx];
        ans = Math.max(ans, sum);
        for(int i = idx + t[idx]; i < N; ++i) {
            getMaxProfit(i, sum);
        }
    }
}
