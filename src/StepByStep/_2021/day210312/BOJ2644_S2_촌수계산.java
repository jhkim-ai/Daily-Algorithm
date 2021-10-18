package StepByStep._2021.day210312;

import java.io.*;
import java.util.*;

public class BOJ2644_S2_촌수계산 {

    static int N, M;
    static int start, end;
    static ArrayList<Integer>[] list;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        // 인접List 사용
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        // 최단 거리를 찾는 것과 비슷하다 생각하여 bfs 구현
        int ans = bfs(start);
        System.out.println(ans);

    }

    static int bfs(int s) {
        int ans = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            // 현재 연결된 노드의 수만큼 진행 후, Count 를 증가하기 위한 for 문
            int t = q.size(); // 주의! q.size()가 계속 변동되기 때문에, 고정값을 정한 후 for 문을 돌려야함
            for (int i = 0; i < t; i++) {
                int n = q.poll();
                // 탐색 완료 시, return
                if (n == end)
                    return ans;
                // 현재 Node 에서 방문하지 않은 Node 를 추가
                for (int j = 0; j < list[n].size(); j++) {
                    int next = list[n].get(j);
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    static String input = "9\n" +
            "7 4\n" +
            "7\n" +
            "1 2\n" +
            "1 3\n" +
            "2 7\n" +
            "2 8\n" +
            "2 9\n" +
            "4 5\n" +
            "4 6";
}
