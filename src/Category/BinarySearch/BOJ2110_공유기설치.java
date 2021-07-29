package Category.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ2110_공유기설치 {

    private static int N, C;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 1;
        int right = arr[arr.length - 1] - arr[0];
        int d = 0;
        int ans = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int start = arr[0];
            int count = 1;
            for (int i = 0; i < N; i++) { //집집마다 검색함
                d = arr[i] - start;
                if (d >= mid) { //만약 첫번째 집과의 거리가 더 크다면 찾았다고 count 올려주고, 내가 찾는집에 이번 집을 넣어준다.
                    count++;
                    start = arr[i];
                }
            }

            if (count >= C) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);
    }
}
