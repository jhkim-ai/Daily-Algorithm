package Baekjoon;

import java.io.*;
import java.util.*;

// ATM (Silver-3) - 누적합
public class BOJ11399_S3_ATM {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int tmp = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            tmp += arr[i];
            ans += tmp;
        }

        System.out.println(ans);

    }

    static String input = "5\n" +
            "3 1 4 3 2";

}
