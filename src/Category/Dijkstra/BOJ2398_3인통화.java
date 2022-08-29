package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2398_3인통화 {

    private static int N, M;
    private static int v1, v2, v3;
    private static int[][] dist, prev;
    private static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N + 1][N + 1];
        prev = new int[N + 1][N + 1];

        graph = new List[N + 1];
        for(int idx = 0; idx <= N; ++idx) {
            graph[idx] = new ArrayList<>();
        }

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }

        st = new StringTokenizer(br.readLine(), " ");

        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());
        v3 = Integer.parseInt(st.nextToken());

        dijkstra(v1);
        dijkstra(v2);
        dijkstra(v3);

        int centerPoint = getCenterPoint();
        String ans = getAnswer(centerPoint);

        System.out.println(ans);
    }

    public static void dijkstra(int start){

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        Arrays.fill(dist[start], Integer.MAX_VALUE);
        dist[start][start] = 0;
        prev[start][start] = 0;
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            int nowVertex = now.to;

            if(visited[nowVertex]) continue;
            visited[nowVertex] = true;

            for(Edge next : graph[nowVertex]) {
                if(dist[start][next.to] > dist[start][nowVertex] + next.cost) {
                    dist[start][next.to] = dist[start][nowVertex] + next.cost;
                    pq.offer(new Edge(next.to, dist[start][next.to]));
                    prev[start][next.to] = nowVertex;
                }
            }
        }

    }

    public static int getCenterPoint() {

        int centerPoint = -1;
        int sum = Integer.MAX_VALUE;

        for(int idx = 1; idx <= N; ++idx) {
            if(sum > dist[v1][idx] + dist[v2][idx] + dist[v3][idx]) {
                sum = dist[v1][idx] + dist[v2][idx] + dist[v3][idx];
                centerPoint = idx;
            }
        }

        return centerPoint;
    }

    public static String getAnswer(int centerPoint) {
        StringBuilder ans = new StringBuilder();
        StringBuilder route = new StringBuilder();
        int cnt = 0;
        int end = centerPoint;
        int sum = dist[v1][centerPoint] + dist[v2][centerPoint] + dist[v3][centerPoint];

        while(prev[v1][end] != 0){
            route.append(end).append(" ").append(prev[v1][end]).append("\n");
            end = prev[v1][end];
            ++cnt;
        }

        end = centerPoint;
        while(prev[v2][end] != 0){
            route.append(end).append(" ").append(prev[v2][end]).append("\n");
            end = prev[v2][end];
            ++cnt;
        }

        end = centerPoint;
        while(prev[v3][end] != 0){
            route.append(end).append(" ").append(prev[v3][end]).append("\n");
            end = prev[v3][end];
            ++cnt;
        }

        ans.append(sum).append(" ").append(cnt).append("\n");
        ans.append(route);
//        System.out.println(route.toString());
//        System.out.println(cnt);

        return ans.toString();
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
