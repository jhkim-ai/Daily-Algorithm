package StepByStep.day210406;

import java.util.*;
import java.io.*;

public class BOJ6603_로또 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int testCase = Integer.parseInt(st.nextToken());
        while(testCase != 0){
            int[] arr = new int[testCase];
            for (int i = 0; i < testCase; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // ---------- 알고리즘 시작 ---------- //
            // Idea. 조합

            // 1. 정렬 (한 번 정렬을 해놓으면 순서대로 조합이 뽑힘)
            Arrays.sort(arr);

            // 2. 조합
            combination(6, new int[6], 0, arr);
            sb.append("\n");

            // Next TestCase
            st = new StringTokenizer(br.readLine(), " ");
            testCase = Integer.parseInt(st.nextToken());
        }

        // 3. 출력
        System.out.println(sb);
    }

    // 조합 공식
    static void combination(int cnt, int[] selected, int startIdx, int[] arr){
        if(cnt == 0){
            for(int num : selected)
                sb.append(num).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = startIdx; i < arr.length; i++) {
            selected[selected.length - cnt] = arr[i];
            combination(cnt-1, selected, i+1, arr);
        }
    }
}
