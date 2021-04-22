package Category.Graph;

import java.io.*;
import java.util.*;

public class BOJ2458_키순서 {

    static int N, M, ans, cnt;
    static ArrayList[] tall;
    static ArrayList[] small;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());      // 학생 수
        M = Integer.parseInt(st.nextToken());      // 비교 횟수
        ans = 0;

        tall = new ArrayList[N + 1];
        small = new ArrayList[N + 1];

        for (int i = 0; i < N + 1; ++i) {
            tall[i] = new ArrayList<Integer>();
            small[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int sMan = Integer.parseInt(st.nextToken());
            int tMan = Integer.parseInt(st.nextToken());

            tall[sMan].add(tMan);   // 나보다 큰 사람 목록
            small[tMan].add(sMan);  // 나보다 작은 사람 목록
        }

        // ---------- 알고리즘 시작 ---------- //

        // Idea. 내가 몇 번째인지를 알려면, 나를 기준으로
        //       (내 위의 명 수 + 내 아래의 명 수) == N-1 이면 된다.

        for (int i = 1; i < N + 1; ++i) {
            cnt = 0;
            dfs(tall, i, new boolean[N + 1]);
            dfs(small, i, new boolean[N + 1]);
            if (cnt == N - 1) ans++;
        }

        System.out.println(ans);
    }

    static void dfs(ArrayList[] list, int num, boolean[] visited) {

        visited[num] = true;

        // 나(num)보다 큰(작은) 사람들의 목록
        List<Integer> men = list[num];

        // 나(num)보다 큰(작은) 사람이 없다면
        if (men.size() == 0) return;

        for (int i = 0; i < men.size(); ++i) {
            int next = men.get(i);
            if (visited[next]) continue;
            cnt++;
            dfs(list, next, visited);
        }
    }
}
