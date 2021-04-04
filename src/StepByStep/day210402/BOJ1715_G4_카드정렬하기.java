package StepByStep.day210402;

import java.util.*;
import java.io.*;

public class BOJ1715_G4_카드정렬하기 {
    public static void main(String[] args) throws Exception {

        // -------- 입력 -------- // 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        // Idea. 오름차순으로 정렬 후, 최솟값을 뽑아 누적합을 진행
        // 주의 1. 합한 결과를 기존의 배열에 넣은 후, 또다시 오름차순 정렬을 해야함
        // -> 잦은 삽입 삭제 = LinkedList -> 최솟값만 뽑기 = PriorityQueue

        int sum = 0;

        // N = 1이면, 비교대상이 없음으로 0
        while(pq.size() > 1){
            int a = pq.poll();
            int b = pq.poll();

            sum += a + b;
            pq.offer(a+b);
        }
        System.out.println(sum);
    }
}
