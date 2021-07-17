package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ14719_빗물 {

    private static int H, W;
    private static int[] arr;
    private static int count;
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        visited = new boolean[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        rain();
        System.out.println(count);
    }
    // 3 4 3 3
    public static void rain(){
        int left = 0;
        int right = W-1;

        for(int i = 0; i < W; ++i){
            // 오름차순
            if(arr[left] <= arr[i]){
                for(int j = left; j< i; ++j){
                    count += arr[left] - arr[j];
                    visited[j] = true;
                }
                left = i;
            }
        }

        for(int i = W-2; i >= 0; --i){
            // 내림차순
            if(arr[right] <= arr[i]){
                for(int j = right; j > i; --j){
                    if(visited[j]) continue;
                    count += arr[right] - arr[j];
                }
                right = i;
            }
        }
    }
}
