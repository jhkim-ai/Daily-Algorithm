package Category.Greedy;

import java.io.*;
import java.util.*;

public class BOJ11047_S2_동전0 {
    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        for (int i = N-1; i >= 0; i--) {
            ans += K/arr[i];
            K %= arr[i];
        }

        System.out.println(ans);
    }

    static String input = "10 4200\n" +
            "1\n" +
            "5\n" +
            "10\n" +
            "50\n" +
            "100\n" +
            "500\n" +
            "1000\n" +
            "5000\n" +
            "10000\n" +
            "50000";
}
