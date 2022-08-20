package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17396_백도어 {

    private static final long INF = Long.MAX_VALUE;

    private static int N, M;
    private static boolean[] vision;
    private static List<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        vision = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; ++i) {
            int isVision = Integer.parseInt(st.nextToken());
            if (isVision == 1) {
                vision[i] = true;
            }
        }
        vision[N - 1] = false;

        adjList = new ArrayList[N];
        for (int i = 0; i < N; ++i) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (vision[from] || vision[to]) {
                continue;
            }

            adjList[from].add(new Edge(to, cost));
            adjList[to].add(new Edge(from, cost));
        }

        long ans = dijkstra(0, N - 1);
        if(ans == INF){
            ans = -1;
        }
        System.out.println(ans);
    }

    public static long dijkstra(int from, int to) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return Long.compare(o1.cost, o2.cost);
        });
        long[] dist = new long[N];
        boolean[] visited = new boolean[N];

        Arrays.fill(dist, INF);
        dist[from] = 0;
        pq.offer(new Edge(from, 0));

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowNode = now.to;

            if (visited[nowNode]) {
                continue;
            }
            visited[nowNode] = true;
            for (Edge nextNode : adjList[nowNode]) {
                if (dist[nextNode.to] > dist[nowNode] + nextNode.cost) {
                    pq.offer(
                        new Edge(nextNode.to, dist[nextNode.to] = dist[nowNode] + nextNode.cost));
                }
            }

        }
        return dist[to];
    }

    static class Edge {

        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                "to=" + to +
                ", cost=" + cost +
                '}';
        }
    }

}
