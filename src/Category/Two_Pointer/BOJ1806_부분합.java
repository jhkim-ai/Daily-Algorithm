package Category.Two_Pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1806_부분합 {

    private static int N, S;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        int sum = 0;
        while(true){
            if(sum >= S){
                ans = Math.min(right - left, ans);
                sum -= arr[left++];
            } else if(right == N){
                break;
            } else {
                sum += arr[right++];
            }
        }

        if(ans == Integer.MAX_VALUE){
            ans = 0;
        }
        System.out.println(ans);
    }
}
