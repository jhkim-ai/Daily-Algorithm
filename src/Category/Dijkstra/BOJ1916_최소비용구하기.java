package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1916_최소비용구하기 {

    private static int N, M;
    private static int start, end;
    private static int[] dist;
    private static boolean[] visited;
    private static List<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        visited = new boolean[N+1];
        adjList = new List[N+1];
        for(int idx = 0; idx <= N; ++idx){
            adjList[idx] = new ArrayList<>();
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, cost));
        }

        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();
        System.out.println(dist[end]);
    }

    public static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge nowEdge = pq.poll();
            int nowVertex = nowEdge.to;

            if(visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for(Edge nextEdge : adjList[nowVertex]){
                if(dist[nextEdge.to] > dist[nowVertex] + nextEdge.cost) {
                    dist[nextEdge.to] = dist[nowVertex] + nextEdge.cost;
                    pq.offer(new Edge(nextEdge.to, dist[nextEdge.to]));
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
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
}
