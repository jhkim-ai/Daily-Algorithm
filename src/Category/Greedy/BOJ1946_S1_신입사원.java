package Category.Greedy;

import java.util.*;
import java.io.*;

public class BOJ1946_S1_신입사원 {

    static int T, N, ans;
    static Score[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            arr = new Score[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                arr[i] = new Score(A, B);
            }

            // Idea. 1. 하나의 성적(A)을 기준으로 정렬한다.
            //       2. 또다른 성적(B)는 자신보다 낮은 성적을 찾는다.
            //       2-1. 첫 번째 성적을 기준으로 낮은 성적을 찾으며, 낮은 성적을 찾을 시 기준(base)값으로 갱신한다

            // 1. 정렬
            Arrays.sort(arr);
            int base = Integer.MAX_VALUE;

            // 2. 기준값과 비교하며 기준보다 낮은 성적을 찾는다.
            for (int i = 0; i < N; i++) {
                // 2-1. 기준보다 크다면, Pass
                if (base < arr[i].B) {  
                    continue;
                }
                
                // 2-2. 기준보다 작으면, 기준값을 갱신하고 신입사원의 수를 늘린다
                base = Math.min(arr[i].B, base);
                ans++;
            }
            
            // 3. 정답 출력
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static class Score implements Comparable<Score> {
        int A;  // 서류 성적
        int B;  // 면접 성적

        public Score(int a, int b) {
            A = a;
            B = b;
        }

        // 서류 성적(A)을 기준으로 오름차순 정렬
        @Override
        public int compareTo(Score s) {
            return Integer.compare(this.A, s.A);
        }
    }
}
