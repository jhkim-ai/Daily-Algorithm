package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ15656_N과M7 {

    private static int N, M;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        duplicatedPermutation(M, new int[M], sb);
        System.out.print(sb);
    }

    public static void duplicatedPermutation(int cnt, int[] selected, StringBuilder sb){
        if(cnt == 0){
            for(int num : selected){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 0; i < N; ++i){
            selected[selected.length - cnt] = arr[i];
            duplicatedPermutation(cnt-1, selected, sb);
        }
    }
}
