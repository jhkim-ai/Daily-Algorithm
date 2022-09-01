package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1854_K번째_최단경로_찾기 {

    private static int N, M, K;
    private static List<Edge>[] graph;
    private static PriorityQueue<Integer>[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        // 1. init
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        dist = new PriorityQueue[N + 1];
        for(int idx = 0; idx <= N; ++idx) {
            graph[idx] = new ArrayList<>();
            dist[idx] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)); // Max Heap
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
        }

        // 2. 알고리즘 시작
        dijkstra();

        // 3. 정답 출력
        for(int idx = 1; idx <= N; ++idx) {
            if(dist[idx].size() < K) sb.append("-1");
            else sb.append(dist[idx].peek());
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    // 원리 - dist 를 1차원 int 배열이 아닌 1차원 pq 배열을 이용
    // 1. 정점을 계속 방문할 수 있음으로, cost 값을 누적하며 경로 무한 탐색
    // 2. 그러나, K 번째 최단 거리를 찾아야 하므로 pq.size()를 K로 고정하며 거리값 update
    //    2-1: pq.size() < K 이면, pq에 삽입
    //    2-2: pq.size() == K 이면, pq.peek()과 지나갈 경로의 Cost 와 비교
    //       2-2-1: pq.peek() > nextCost 면, 교체
    //       2-2-2: pq.peek() <= nextCost 면, pass --> 1.의 무한 탐색 종료 조건)
    public static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        dist[1].add(0);

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowVertex = now.to;
            int cost = now.cost;

            for(Edge next : graph[nowVertex]) {
                int nextCost = cost + next.cost;

                if(dist[next.to].size() < K) {                  // 아직 K 번째 최단거리를 못채움
                    dist[next.to].offer(nextCost);
                    pq.offer(new Edge(next.to, nextCost));
                } else {                                        // K 번째까지의 최단거리를 찾음
                    if(dist[next.to].peek() > nextCost) {
                        dist[next.to].poll();
                        dist[next.to].offer(nextCost);
                        pq.offer(new Edge(next.to, nextCost));
                    }
                }
            }
        }
    }

    public static class Edge implements Comparable<Edge> {
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
}
