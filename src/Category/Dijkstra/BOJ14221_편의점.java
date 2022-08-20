package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ14221_편의점 {

    private static final int INF = Integer.MAX_VALUE;

    private static int N, M, P, Q;
    private static int[] houses, convenients;
    private static List<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new List[N+1];
        for(int n = 1; n <= N; ++n){
            adjList[n] = new ArrayList<>();
        }

        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(to, cost));
            adjList[to].add(new Edge(from, cost));
        }

        st = new StringTokenizer(br.readLine(), " ");
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        houses = new int[P];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < P; ++i){
            houses[i] = Integer.parseInt(st.nextToken());
        }

        convenients = new int[Q];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < Q; ++i){
            convenients[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(houses);
        int minValFromConvenient = Integer.MAX_VALUE;
        int minIdxFromConvenient = -1;
        for(int house : houses){
            int minDist = dijkstra(house);
            if(minValFromConvenient > minDist){
                minIdxFromConvenient = house;
                minValFromConvenient = minDist;
            }
        }

        System.out.println(minIdxFromConvenient);
    }

    public static int dijkstra(int from){
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.cost, o2.cost);
        });
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];

        Arrays.fill(dist, INF);
        dist[from] = 0;
        pq.offer(new Edge(from, 0));

        while(!pq.isEmpty()){
            Edge nowEdge = pq.poll();
            int nowNode = nowEdge.to;

            if(visited[nowNode]){
                continue;
            }
            visited[nowNode] = true;

            for(Edge nextEdge : adjList[nowNode]){
                if(dist[nextEdge.to] > dist[nowNode] + nextEdge.cost){
                    pq.offer(new Edge(nextEdge.to, dist[nextEdge.to] = dist[nowNode] + nextEdge.cost));
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int convenientNo : convenients){
            ans = Math.min(ans, dist[convenientNo]);
        }
        return ans;
    }

    static class Edge {

        int to;
        int cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public String toString(){
            return "to: " + to + ", cost: " + cost;
        }
    }
}
