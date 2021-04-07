package StepByStep.day210402;

import java.util.*;
import java.io.*;

public class BOJ2531_S1_회전초밥 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());   // 초밥 접시의 수
        int d= Integer.parseInt(st.nextToken());    // 초밥의 총 가짓 수
        int k = Integer.parseInt(st.nextToken());   // 연속해서 먹을 수 있는 수
        int c = Integer.parseInt(st.nextToken());   // 쿠폰 번호
        
        // 초밥 벨트에 있는 초밥을 모두 Queue에 저장
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.offer(Integer.parseInt(br.readLine()));
        }

        // 경우의 수 찾기
        
    }
}
