package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ14719_빗물 {

    private static int H, W;
    private static int[] arr;
    private static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < W; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        rain();
    }
    // 3 4 3 3
    public static void rain(){
        int left = 0;
        int right = 0;
        for(int i = 1; i < W; ++i){
            // 오름차순
            if(arr[left] <= arr[i]){
                for(int j = left; j< i; ++j){
                    count += arr[left] - arr[j];
                }
                left = arr[i];
            }
        }


    }
}
