package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11779_최소비용구하기2 {

    private static final int INF = Integer.MAX_VALUE;

    private static int N, M;
    private static List<Bus>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i){
            adjList[i] = new ArrayList<>();
        }

        for(int m = 0; m < M; ++m){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[from].add(new Bus(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        dijkstra(from, to);
    }

    public static void dijkstra(int from, int to){
        PriorityQueue<Bus> pq = new PriorityQueue<>(); // pq 를 이용한 Dijkstra
        // 경로 역추적을 위한 저장소
        int[] beforeNode = new int[N+1];

        // 초기화
        boolean[] visited = new boolean[N+1];
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);

        dist[from] = 0;
        pq.offer(new Bus(from, 0));

        while(!pq.isEmpty()){
            Bus nowBus = pq.poll();
            int nowNode = nowBus.to;

            if(visited[nowNode]) { // 이미 방문했다면 pass
                continue;
            }
            visited[nowNode] = true;

            for(Bus nextBus : adjList[nowNode]){
                if(dist[nextBus.to] > dist[nowNode] + nextBus.cost){
                    dist[nextBus.to] = dist[nowNode] + nextBus.cost;
                    pq.offer(new Bus(nextBus.to, dist[nextBus.to]));
                    beforeNode[nextBus.to] = nowNode;
                }
            }
        }
        // 올바른 순으로 경로 재배치
        Stack<Integer> s = new Stack<>();
        int end = to;
        s.push(end);
        while(beforeNode[end] != 0){
            s.push(beforeNode[end]);
            end = beforeNode[end];
        }

        // 정답
        StringBuilder sb = new StringBuilder();
        sb.append(dist[to]).append("\n");
        sb.append(s.size()).append("\n");
        while(!s.isEmpty()) {
            sb.append(s.pop()).append(" ");
        }
        System.out.println(sb);
    }

    static class Bus implements Comparable<Bus>{
        int to;
        int cost;

        public Bus(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus b){
            return Integer.compare(this.cost, b.cost);
        }

        @Override
        public String toString() {
            return "Bus{" +
                    "to=" + to +
                    ", cost=" + cost +
                    '}';
        }
    }
}
