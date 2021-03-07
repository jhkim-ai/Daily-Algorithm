package Baekjoon;

import java.util.*;
import java.io.*;

// 블랙잭
public class BOJ2798_B2_블랙잭 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int ans = Integer.MIN_VALUE;
        all :for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                for (int k = j + 1; k < arr.length; k++) {
                    sum = arr[i] + arr[j] + arr[k];
                    System.out.println(sum);
                    if (sum == M) {
                        ans = sum;
                        break all;
                    } else {
                        if (sum < M) {
                            ans = Math.max(ans, sum);
                        }
                    }
                }
            }
        }
        System.out.println(ans);

    }

    static String input = "5 21\n" +
            "5 6 7 8 9";

}
