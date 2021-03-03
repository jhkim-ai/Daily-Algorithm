package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class Baekjoon2805 {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);

        long[] arr = new long[N];
        String[] input2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(input2[i]);

        br.close();

        Arrays.sort(arr);

        long maxH = arr[N - 1]; // 최댓값은 항상 arr[size-1]
        long minH = 0;          // 최솟값은 항상 0
        long mid = 0;

        while (minH <= maxH) {
            long sum = 0;
            mid = (maxH + minH) / 2;
            long cutTree = 0;
            for (int i = 0; i < arr.length; i++) {
                cutTree = arr[i] - mid;
                if (cutTree < 0)
                    cutTree = 0;
                sum += cutTree;
            }

            if (sum >= M)
                minH = mid+1;
            else
                maxH = mid-1;
        }
        bw.write(Long.toString(maxH));
        bw.flush();
        bw.close();
    }
}
