package StepByStep.day210402;

import java.util.*;
import java.io.*;

public class BOJ2531_S1_회전초밥 {

    static int N, D, K, C;
    static int[] arr;
    static Set<Integer> s;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 진열된 접시의 수
        D = Integer.parseInt(st.nextToken());   // 초밥의 가짓 수
        K = Integer.parseInt(st.nextToken());   // 연속해서 먹는 접시 수
        C = Integer.parseInt(st.nextToken());   // 쿠폰 번호

        // Idea. [ Cycle(순환)을 직선화 하기 ]
        // 1. 투포인터를 이용하여, 연속된 초밥을 Set에 넣는다.
        // 2. Set에 쿠폰 사용 가능 여부를 확인하며, 먹을 수 있는 가짓 수를 구한다.
        arr = new int[N+K];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = N; i < N+K-1; i++) {
            arr[i] = arr[i-N];
        }

        int left = 0;
        int right = left+K-1;
        int ans = Integer.MIN_VALUE;

        s = new HashSet<>();
        for (int i = 0; i < N; i++) {
            ans = Math.max(getSushi(left, right), ans);
            left++;
            right++;
        }

        System.out.println(ans);
    }

    static int getSushi(int left, int right){
        s.clear();
        boolean isPossible = true;
        for (int idx = left; idx <= right; idx++) {
            s.add(arr[idx]);
            // 내가 먹은 초밥에 쿠폰 번호가 있다면, 쿠폰을 사용할 수 없음
            if(arr[idx] == C)
                isPossible = false;
        }

        // 쿠폰 사용 불가
        if(!isPossible){
            return s.size();
        }

        // 쿠폰 사용 가능
        return s.size()+1;
    }
}
