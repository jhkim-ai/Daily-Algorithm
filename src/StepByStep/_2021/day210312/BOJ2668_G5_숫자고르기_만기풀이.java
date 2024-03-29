package StepByStep._2021.day210312;

import java.io.*;
import java.util.*;

public class BOJ2668_G5_숫자고르기_만기풀이 {
    // https://www.acmicpc.net/problem/2668
    // 숫자고르기
    // 조만기 풀이

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N+1];
            for(int i = 1; i<=N; ++i) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            boolean visit[] = new boolean[N+1];	// 방문했는지
            boolean pick[] = new boolean[N+1];  // 고른 수

            // 1부터 N까지 완전탐색
            for(int i = 1; i<=N; ++i) {
                if(pick[i]) continue; 	// 이미 고른 수라면 pass

                Arrays.fill(visit, false);	// 방문체크는 항상 false로 초기화

                int next = arr[i];
                visit[i] = true;

                while(!visit[next]) {
                    visit[next] = true;
                    next = arr[next];
                }

                if(i!=next) continue;
                for(int j = 1; j<=N; ++j) {
                    if(visit[j]) pick[j] = true;
                }
            }

            StringBuilder sb = new StringBuilder();
            int cnt = 0;
            for(int i = 1; i<=N; ++i) {
                if(pick[i]) {
                    ++cnt;
                    sb.append(i).append('\n');
                }
            }
            System.out.println(cnt);
            System.out.println(sb);
        }


}
