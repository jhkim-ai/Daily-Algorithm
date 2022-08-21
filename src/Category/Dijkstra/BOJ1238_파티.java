package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1238_파티 {

    private static int N, M, X;
    private static int[][] dist;
    private static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int ans = Integer.MIN_VALUE;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];
        graph = new List[N+1];
        for(int idx = 0; idx <= N; ++idx) {
            graph[idx] = new ArrayList<>();
        }

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
        }

        dijkstra();
        for(int startIdx = 1; startIdx <= N; ++startIdx){
            if(startIdx == X) continue;
            ans = Math.max(dist[startIdx][X] + dist[X][startIdx], ans);
        }
        System.out.println(ans);
    }

    public static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N+1][N+1];

        for(int startIdx = 1; startIdx <= N; ++startIdx) {
            Arrays.fill(dist[startIdx], Integer.MAX_VALUE);
            dist[startIdx][startIdx] = 0;

            pq.clear();
            pq.offer(new Edge(startIdx, 0));

            while(!pq.isEmpty()) {
                Edge nowEdge = pq.poll();
                int nowVertex = nowEdge.to;

                if(visited[startIdx][nowVertex]) continue;
                visited[startIdx][nowVertex] = true;

                for(Edge next : graph[nowVertex]) {
                    if(dist[startIdx][next.to] > dist[startIdx][nowVertex] + next.cost) {
                        dist[startIdx][next.to] = dist[startIdx][nowVertex] + next.cost;
                        pq.offer(new Edge(next.to, dist[startIdx][next.to]));
                    }
                }
            }

        }
    }

    public static class Edge implements Comparable<Edge>{
        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.cost, e.cost);
        }
    }
}
