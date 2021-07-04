package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ9663_N_Queen {

    private static int N, ans;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; ++i){
            arr = new int[N];
            arr[0] = i;
            dfs(1);
        }

        // 정답 출력
        System.out.println(ans);
    }

    public static void dfs(int row){
        if(row == N){
            ans++;
            return;
        }

        for(int i = 0; i < N; ++i){
            arr[row] = i;
            if(!isPossible(row)) continue;
            dfs(row+1);
        }
    }

    public static boolean isPossible(int row){
        for(int i = 0; i < row; ++i){
            if(arr[i] == arr[row]) return false; // 같은 열일 경우
            if(Math.abs(i - row) == Math.abs(arr[i] - arr[row])) return false; // 대각선 위치
        }
        return true;
    }
}
