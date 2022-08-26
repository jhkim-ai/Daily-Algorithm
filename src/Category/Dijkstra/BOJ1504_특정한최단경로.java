package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1504_특정한최단경로 {

    private static int N, E;
    private static int v1, v2;
    private static int[][] dist;
    private static boolean[][] visited;
    private static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        graph = new List[N+1];
        for(int i = 0; i <= N; ++i) {
            graph[i] = new ArrayList<>();
        }

        while(E-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(end, cost));
            graph[end].add(new Edge(start, cost));
        }

        st = new StringTokenizer(br.readLine(), " ");
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        int ans = -1;
        int A = getA();
        int B = getB();

        if(A >= 0 && B >= 0) {
            ans = Math.min(A, B);
        } else if(A < 0 && B >= 0) {
            ans = B;
        } else if(A >= 0 && B < 0) {
            ans = A;
        }

        System.out.println(ans);
    }

    public static int dijkstra(int start, int end) {

        if(visited[start][start]) return dist[start][end];

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        Arrays.fill(dist[start], Integer.MAX_VALUE);
        dist[start][start] = 0;

        while(!pq.isEmpty()) {
            Edge nowNode = pq.poll();
            int nowVertex = nowNode.to;

            if(visited[start][nowVertex]) continue;
            visited[start][nowVertex] = true;

            for(Edge next : graph[nowVertex]) {
                if(dist[start][next.to] > dist[start][nowVertex] + next.cost) {
                    dist[start][next.to] = dist[start][nowVertex] + next.cost;
                    pq.offer(new Edge(next.to, dist[start][next.to]));
                }
            }
        }
        return dist[start][end];
    }

    public static int getA() {
        int ans = -1;

        int a = dijkstra(1, v1);
        if(a == Integer.MAX_VALUE) return -1;
        ans = a;

        int b = dijkstra(v1, v2);
        if(b == Integer.MAX_VALUE) return -1;
        ans += b;

        int c = dijkstra(v2, N);
        if(c == Integer.MAX_VALUE) return -1;
        ans += c;

        return ans;
    }

    public static int getB() {
        int ans = -1;

        int a = dijkstra(1, v2);
        if(a == Integer.MAX_VALUE) return -1;
        ans = a;

        int b = dijkstra(v2, v1);
        if(b == Integer.MAX_VALUE) return -1;
        ans += b;

        int c = dijkstra(v1, N);
        if(c == Integer.MAX_VALUE) return -1;
        ans += c;

        return ans;
    }

    private static class Edge implements Comparable<Edge>{
        int to;
        int cost;

        public Edge(int end, int cost) {
            this.to = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.cost, e.cost);
        }
    }
}
