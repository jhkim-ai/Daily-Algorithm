package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon1654 {

    static int K, N;
    static long[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());


        arr = new long[K];
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 알고리즘 시작

        Arrays.sort(arr);   // 이분 탐색을 위한 정렬

        long low = 0;       // MAX_VALUE + 1은 OverFlow될 수 있음을 하자!
        long high = Long.MAX_VALUE;
        long mid = 1;

        while (low <= high) {

            long cnt = 0;
            mid = (low + high) / 2;

            for (int i = 0; i < K; i++) {
                cnt += arr[i] / mid;
            }

            // 최댓값을 찾기 위함이기에 중간에 K개와 같아 해서 Cut 하면 안된다.
            // ex) 4 11
            //     804 743 457 539 가 있을 때, 최댓값은 201 이다.
            System.out.println("mid, cnt -" + mid +" : " + cnt);
            if (cnt >= N)
                low = mid + 1;
            else
                high = mid - 1;
            System.out.println("low, high -" + low +" : " + high);
        }

        System.out.println(high);
    }
}
