package Category.Dijkstra;

import java.util.*;
import java.io.*;

public class BOJ1753_G5_최단경로 {

    static final int INF = 987654321;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        // ---------- 데이터입력 ---------- //
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        ArrayList<Node>[] list = new ArrayList[V + 1];  // 인접리스트 사용

        for (int i = 0; i < V + 1; i++) {   // 인접리스트 초기화
            list[i] = new ArrayList<>();
        }

        // 단방향 그래프
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, cost));
        }

        // ---------- 알고리즘 ---------- //
        // Idea. (최단 경로 + weigh 값이 양수 + 그래프)를 나타내므로
        // Dijkstra
        // dijkstra(start, V, list);
        dijkstra2(start, V, list);

        // ---------- 출력 ---------- //
        System.out.println(sb);
    }

    // Priority Queue 를 이용한 dijkstra 알고리즘
    static void dijkstra2(int start, int V, ArrayList<Node>[] list){
        // 상황판
        int[] dist = new int[V+1];
        boolean[] visited = new boolean[V+1];
        Arrays.fill(dist, INF);

        // 출발지 선정
        dist[start] = 0;

        // 우선순위 큐를 이용한 dijkstra
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();   // Priority Queue 에서 뽑으면 그게 최소
            visited[now.vertex] = true; // 방문 처리

            // 자식 인접리스트 받아오기
            ArrayList<Node> curNode = list[now.vertex];

            // 자식 인집러시트로부터 다음 원소들 탐색
            int idx = 0;
            while(idx < curNode.size()){
                Node next = curNode.get(idx);
                if(!visited[next.vertex] && dist[next.vertex] > dist[now.vertex] + next.cost){
                    dist[next.vertex] = dist[now.vertex] + next.cost;
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
                idx++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int d = 1; d < dist.length; d++) {
            if(dist[d] == INF)
                sb.append("INF");
            else
                sb.append(dist[d]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 다익스트라 알고리즘
    static void dijkstra(int start, int V, ArrayList<Node>[] list) {
        
        // 상황판 초기화
        int[] dist = new int[V + 1];
        boolean[] visited = new boolean[V + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // 모든 정점을 돌아보며 상황판을 갱신시킨다.
        for (int v = 1; v < V + 1; v++) {
            int nowVertex = 0;
            int minCost = INF;

            // 1. 새로 시작할 정점을 선택(cost 가 가장 적은 경로를 찾자)
            for (int i = 1; i <= V; i++) {
                if (!visited[i] && dist[i] < minCost) {
                    nowVertex = i;
                    minCost = dist[i];
                }
            }

            // 2. (1)에서 선정된 정점을 방문 표시
            visited[nowVertex] = true;
            // 3. 선정된 정점의 인접리스트를 가져온다.
            ArrayList<Node> curNode = list[nowVertex];

            // 4. 인접리스트의 원소만큼 반복하여, 상황판을 갱신시킨다.
            int idx = 0;
            while (idx < curNode.size()) {
                Node next = curNode.get(idx);
                if (!visited[next.vertex] && dist[next.vertex] > dist[nowVertex] + next.cost) {
                    dist[next.vertex] = dist[nowVertex] + next.cost;
                }
                idx++;
            }
        }
        
        // 출력양식 설정
        for (int d = 1; d < dist.length; d++) {
            if (dist[d] == INF)
                sb.append("INF");
            else
                sb.append(dist[d]);
            sb.append("\n");
        }
    }

    static class Node implements Comparable<Node>{
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return Integer.compare(this.cost, n.cost);
        }
    }

    static String input = "5 6\n" +
            "1\n" +
            "5 1 1\n" +
            "1 2 2\n" +
            "1 3 3\n" +
            "2 3 4\n" +
            "2 4 5\n" +
            "3 4 6";
}
