package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ5719_거의최단경로 {

    private static final int INF = Integer.MAX_VALUE;

    private static int N, M;
    private static int[] dist;
    private static boolean[] visited;
    private static boolean[][] exRoute;
    private static List<Edge>[] graph;
    private static List<Integer>[] removeList;
    private static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0) break;

            exRoute = new boolean[N][N];
            graph = new List[N];
            removeList = new List[N];

            for(int idx = 0; idx < N; ++idx) {
                removeList[idx] = new ArrayList<>();
                graph[idx] = new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine(), " ");

            int S = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            while(M-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph[u].add(new Edge(v, p));
            }

            dijkstra(S, D);
            backTracking(S, D);
            dijkstra(S, D);

            sb.append(dist[D]).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dijkstra(int start, int end) {
        dist = new int[N];
        visited = new boolean[N];

        Arrays.fill(dist, INF);

        pq.clear();
        pq.offer(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Edge nowEdge = pq.poll();
            int nowVertex = nowEdge.to;

            if(visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for(Edge next : graph[nowVertex]) {

                if(exRoute[nowVertex][next.to]) continue;

                if(dist[next.to] > dist[nowVertex] + next.cost) {
                    dist[next.to] = dist[nowVertex] + next.cost;
                    pq.offer(new Edge(next.to, dist[next.to]));

                    removeList[next.to].clear();
                    removeList[next.to].add(nowVertex);

                } else if(dist[next.to] == dist[nowVertex] + next.cost) {
                    removeList[next.to].add(nowVertex);
                }
            }
        }

        if(dist[end] == INF) dist[end] = -1;
    }

    public static void backTracking(int from, int to) {
        if(from == to) return;

        for(int node : removeList[to]) {
            if(!exRoute[node][to]){
                exRoute[node][to] = true;
                backTracking(from, node);
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
        public int compareTo(Edge e){
            return Integer.compare(this.cost, e.cost);
        }
    }
}
