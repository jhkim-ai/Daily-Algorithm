package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

// S5 요세푸스 문제
// LinkedList + Queue
public class BOJ1158_S5_요세푸스문제 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; ++i) {
            list.offer(i);
        }

        sb.append("<");
        while (list.size() != 1) {
            for (int i = 0; i < K-1; ++i) {
                list.offer(list.peek());
                list.poll();
            }
            sb.append(list.peek()).append(", ");
            list.poll();
        }
        sb.append(list.peek()).append(">");
        System.out.println(sb);
    }
}
