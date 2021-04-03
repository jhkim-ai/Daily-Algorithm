package Category.Two_Pointer;

import java.util.*;
import java.io.*;

public class BOJ10025_S4_게으른백곰 {

    static int N, K;
    static int[] arr = new int[1000001];
    static int start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int gram = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            arr[idx] = gram;
            start = Math.min(start, idx);
            end = Math.max(end, idx);
        }

        // ------- 알고리즘 시작 ------- //

        if(start < K)
            start += K;

        int ans = Integer.MIN_VALUE;
        while(start + K <= end){
            int sum = 0;
            for(int i = start-K; i<=start+K; i++){
                sum += arr[i];
            }
            ans = Math.max(ans, sum);
            start++;
        }

        System.out.println(ans);
    }
}
