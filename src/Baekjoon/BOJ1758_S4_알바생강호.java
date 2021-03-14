package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1758_S4_알바생강호 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 내림차순 정렬
        Arrays.sort(arr);
        // 주의! long (10,0000 * 10,0000 = 100억)
        long res = 0;

        // 강호가 받을 팁 = 각 손님의 팁 - index
        for (int i = N-1, idx=0; i >= 0; i--) {
            // 음수가 되는 시점부터는 의미가 없다.
            if (arr[i] - idx < 0)
                break;
            res += arr[i] - idx++;
            System.out.println(arr[i] +" : "+idx);
        }
        System.out.println(res);
    }

    static String input = "4\n" +
            "7\n" +
            "19\n" +
            "3\n" +
            "4";
}
