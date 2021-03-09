package StepByStep.day210312;

import java.io.*;
import java.util.*;

public class BOJ15657_S3_N과M_8 {

    static int N,M;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st= new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 1. 정렬
        Arrays.sort(arr);
        
        // 2. 중복 조합
        comb(M, new int[M], 0);

        // 3. 출력
        System.out.println(sb);
    }
    static void comb(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = startIdx; i < arr.length; i++) {
            selected[selected.length-cnt] = arr[i];
            comb(cnt-1, selected, i);
        }
    }

    static String input = "4 2\n" +
            "9 8 7 1";
}
