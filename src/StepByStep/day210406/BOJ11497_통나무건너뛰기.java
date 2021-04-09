package StepByStep.day210406;

import java.util.*;
import java.io.*;

public class BOJ11497_통나무건너뛰기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            int[] ans = new int[N];

            for (int idx = 0; idx < N; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }

            // Idea. 정렬 후, 가운데부터 오른쪽, 왼쪽으로 배열에 넣는다.
            // 1. 정렬
            Arrays.sort(arr);

            // 2. 정답 입력
            int left = (N-1) / 2;
            int right = N / 2;
            int idx = N-1;

            if(N%2 != 0){
                ans[left--] = arr[idx];
                ans[right++] = arr[idx];
                idx--;
            }

            while(left >= 0 && right < N ){
                ans[right++] = arr[idx--];
                ans[left--] = arr[idx--];
            }
            // System.out.println(Arrays.toString(ans));
            int rank = Integer.MIN_VALUE;
            for (int i = 1; i < N; i++) {
                rank = Math.max(Math.abs(ans[i]-ans[i-1]), rank);
            }
            sb.append(rank).append("\n");
        }
        System.out.println(sb);
    }
}
