package StepByStep._2021.day210220;

import java.io.*;
import java.util.*;

public class BOJ1697_S1_숨바꼭질 {

    static int N, K;
    static int ans;
    static int[] cnt;

    public static void main(String[] args) throws Exception{
//      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;
        cnt = new int[100001];

        // 첫 번째 Idea. dfs
        // dfs(N, 0);

        // 두 번째 Idea. bfs (최단거리를 떠올림)
        if(N==K){
            System.out.println(0);
        }
        else {
            bfs(N);
            System.out.println(ans);
        }
    }

    // bfs 는 최단 거리를 이용
    static void bfs(int cur){
        Queue<Integer> q = new LinkedList<>();
        q.offer(cur);
        
        cnt[cur] = 1;    // 카운트
                         // (처음은 1, 유효성 검사로 인하여 다음 값으로 넘어갈 수 없으니
                         // 이전의 값에 cnt 를 증가시키자)
        
        while(!q.isEmpty()){
            int current = q.poll();

            // 3가지 경우의 수
            for (int d = 0; d < 3; d++) {
                
                // 임시 변수
                int next;

                if(d==0)
                    next = current + 1; // 오른쪽
                else if(d==1)
                    next = current - 1; // 왼쪽
                else
                    next = current * 2; // 오른쪽으로 2배

                // 만약 동생에게 도착했다면, 출력
                if(next == K){
                    ans = cnt[current];
                    return;
                }

                // 유효성 검사 + 방문이 안된 곳을 탐색 cnt[next] == 0
                // 방문이 안된 곳을 탐색한다는 Idea가 가장 어려웠던 것 같다.
                if (0<= next && next <= 100000 && cnt[next] == 0){
                    q.offer(next);
                    cnt[next] = cnt[current] + 1;
                }
            }
        }

    }

    static void dfs(int cur, int cnt){
        if (ans < cnt)
            return;

        if (cur == K){
            ans = Math.min(ans, cnt);
            return;
        }

        if(K < cur)
            dfs(cur-1, cnt+1);
        else {
            dfs(cur * 2, cnt + 1);
            dfs(cur + 1, cnt + 1);
            dfs(cur - 1, cnt + 1);
        }

    }

    static String input = "5 17";
}
