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
        PriorityQueue<Bus> pq = new PriorityQueue<>(); // pq 를 이용한 Dijkstra (NlogN)

        // 1. 초기화
        int[] beforeNode = new int[N+1];      // 경로를 역추적하기 위한 저장소
        boolean[] visited = new boolean[N+1]; // 방문 체크
        int[] dist = new int[N+1];            // 시작 Node 로부터 각 Node 까지의 거리 비용
        Arrays.fill(dist, INF);               // INF(경로가 없다는 것을 의미) 로 초기화

        dist[from] = 0;                       // 시작점은 비용이: 0
        pq.offer(new Bus(from, 0));      // 시작점을 pq에 삽입

        // 2. 최단 거리 비용 찾기
        while(!pq.isEmpty()){
            Bus nowBus = pq.poll();            // pq 에 의하여 거리 비용이 가장 작은 것부터 탐색
            int nowNode = nowBus.to;           // 현재 Node

            if(visited[nowNode]) { // 현재 Node 가 이미 방문(거리 계산이 끝난 곳)했다면: pass
                continue;
            }
            visited[nowNode] = true;            // 방문 체크

            for(Bus nextBus : adjList[nowNode]){// 인접 List
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
