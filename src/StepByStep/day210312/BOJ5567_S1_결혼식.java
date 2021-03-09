package StepByStep.day210312;

import java.io.*;
import java.util.*;

public class BOJ5567_S1_결혼식 {
    static int N, M;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            list[before].add(after);
            list[after].add(before);
        }

        // Idea. 최대 거리가 2인 곳
        // 주변부터 탐색하며 친구(거리:1) + 친구의 치구(거리:2)를 찾는다.
        int ans = bfs(1);
        System.out.println(ans);
    }

    static int bfs(int n) {

        int ans = 0;
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = true;

        while (cnt < 2) {
            int size = q.size();
            // 현재 노드로부터 연결된 노드(친구)들을 탐색
            for (int i = 0; i < size; i++) {
                int now = q.poll();
                for (int j = 0; j < list[now].size(); j++) {
                    int next = list[now].get(j);
                    if (!visited[next]) {
                        q.add(next);
                        visited[next] = true;
                        ans++;
                    }
                }
            }
            cnt++;
        }
        return ans;
    }

    static String input = "6\n" +
            "5\n" +
            "1 2\n" +
            "1 3\n" +
            "3 4\n" +
            "2 3\n" +
            "4 5";
}
