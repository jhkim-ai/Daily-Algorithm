package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2309_B2_일곱난장이 {

    static final int N = 9;
    static int[] arr;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        arr = new int[9];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        /* ---------- 알고리즘 시작 ---------- */
        solution1();    // 재귀 조합식

    }

    static void solution1(){

        // Idea : 조합 문제 (9명 중 7명의 합이 100인 사람을 추출)
        combination(7, new int[7], 0);


        // ---- 조합이 완료된(정답 선택) 후, 실행문들

        // 오름차순 정렬
        Arrays.sort(ans);

        // 정답 출력
        for (int num : ans)
            sb.append(num).append("\n");
        System.out.println(sb);
    }

    static void combination(int cnt, int[] selected, int startIdx){

        // 기저 조건
        if(cnt == 0){

            // 선택된 7명의 키의 합을 구함.
            int sum = 0;
            for (int i = 0; i < selected.length; i++) {
                sum += selected[i];

                // 100 을 초과한다면, 굳이 더 진행할 필요 없음 (time, memory save)
                if(sum > 100)
                    return;
            }
            
            // 7명의 합이 100 이면, 정답으로 선택
            if(sum == 100){
                // 중요!!
                // ans = selected;      // 이 문장은 "얕은 복사" 이기에 정답이 선택되어도 다른 곳에서 for 문이 돌아가기에 값이 변경됩니다.
                ans = selected.clone(); // 이 문장은 "깊은 복사" 이기에 정답이 선택되면, 고유의 값을 가지게 됩니다.
            }

            return;
        }

        // 재귀 조합식
        for (int i = startIdx; i < N; i++) {
            selected[selected.length-cnt] = arr[i];
            combination(cnt-1, selected, i+1);
        }

    }

    static String input = "20\n" +
            "7\n" +
            "23\n" +
            "19\n" +
            "10\n" +
            "15\n" +
            "25\n" +
            "8\n" +
            "13";
}
