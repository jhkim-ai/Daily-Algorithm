package 다시풀기;

import java.io.*;
import java.util.*;

public class BOJ1806_부분합 {

    private static int N, S, ans;
    private static int[] arr;

    // 1806 부분합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;

        // S 이상이 되는 최소의 길이
        while (true) {
            if (sum >= S) {
                ans = Math.min(ans, right - left);
                sum -= arr[left++];
            } else if (right == N) {
                break;
            } else {
                sum += arr[right++];
            }
        }

        if (ans > 100000) {
            ans = 0;
        }

        System.out.println(ans);
    }
}


