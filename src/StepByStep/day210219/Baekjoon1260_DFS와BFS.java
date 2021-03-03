package StepByStep.day210219;

import java.io.*;
import java.util.*;

public class Baekjoon1260_DFS와BFS {

    static int N, M, V;
    static List<Integer>[] list;    // 인접 List 이용
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        // -------- 데이터 입력 -------- //

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(str));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        int idx = 0;
        int next_node = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            idx = Integer.parseInt(st.nextToken());
            next_node = Integer.parseInt(st.nextToken());
            list[idx].add(next_node);
            list[next_node].add(idx);
        }

        for (int i = 0; i < list.length; i++) {
            Collections.sort(list[i]);
        }

        // -------- 알고리즘 시작(함수로 구현) -------- //
        dfs(V, new boolean[N + 1]);

        // 개행 추가
        sb.append("\n");

        bfs(new boolean[N + 1]);

        // 결과 출력
        System.out.println(sb);
    }

    // Recursive 를 이용한 dfs 이용
    static void dfs(int v, boolean[] visited) {
        visited[v] = true;
        sb.append(v).append(" ");
        for (int i = 0; i < list[v].size(); i++) {
            if (!visited[list[v].get(i)]) {
                dfs(list[v].get(i), visited);
            }
        }
    }

    // Queue 를 이용한 bfs 구현
    static Queue<Integer> q = new LinkedList<>();

    static void bfs(boolean[] visited) {
        // 첫 시작인 V를 Queue에 넣고 시작
        visited[V] = true;
        q.offer(V);

        // Queue 에서 하나씩 노드를 빼가며 
        // Node 와 연결된 다른 Node 를 차례대로 Queue 에 삽입
        while (!q.isEmpty()) {
            int node = q.poll();
            sb.append(node).append(" ");

            for (int i = 0; i < list[node].size(); i++) {
                if (!visited[list[node].get(i)]) {
                    visited[list[node].get(i)] = true;
                    q.offer(list[node].get(i));
                }
            }
        }
    }

    static String str = "1000 1 1000\n" +
            "999 1000";
}
