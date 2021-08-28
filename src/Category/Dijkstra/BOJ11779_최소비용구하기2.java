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

        // 입력
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i){
            adjList[i] = new ArrayList<>();
        }

        // 인접 List 에 저장
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

        // dijkstra 탐색
        dijkstra(from, to);
    }

    // dijkstra : 한 정점에서 모든 정점까지의 최단 거리
    public static void dijkstra(int from, int to){
        PriorityQueue<Bus> pq = new PriorityQueue<>(); // pq 를 이용한 Dijkstra (NlogN)

        // 1. 초기화
        int[] prev = new int[N+1];      // 경로를 역추적하기 위한 저장소
        boolean[] visited = new boolean[N+1]; // 방문 체크 (현 Node 까지의 최단거리를 구했는지 체크)
        int[] dist = new int[N+1];            // 시작점부터 각 Node 까지의 거리 비용
        Arrays.fill(dist, INF);               // 초기화: INF(경로가 없다는 것을 의미)

        dist[from] = 0;                       // 시작점 비용 = 0
        pq.offer(new Bus(from, 0));      // 시작점을 pq에 삽입

        // 2. 최단 거리 비용 찾기 (탐색 시작)
        while(!pq.isEmpty()){
            Bus nowBus = pq.poll();            // pq 에 의하여 거리 비용이 가장 작은 것부터 추출
            int nowNode = nowBus.to;           // 현재 Node

            if(visited[nowNode]) { // 현재 Node 가 이미 방문(거리 계산이 끝난 곳)했다면: pass
                continue;
            }
            visited[nowNode] = true;            // 방문 체크

            for(Bus nextBus : adjList[nowNode]){// 인접 List (현재 Node 와 다음 노드 사이에 연결된 Edge 모음)
                // Edge 갱신
                // -- dist 에 저장된 nextNode 까지의 (기존)거리와
                // -- 현재 Node 를 거쳐서 지나간 거리를 비교
                if(dist[nextBus.to] > dist[nowNode] + nextBus.cost){
                    dist[nextBus.to] = dist[nowNode] + nextBus.cost;
                    pq.offer(new Bus(nextBus.to, dist[nextBus.to]));
                    prev[nextBus.to] = nowNode; // 역추적을 위해 이전 Node를 저장
                }
            }
        }

        // ***************** dijkstra 함수 시작 부터 여기까지가 공식 ***************** //

        // 올바른 순으로 경로 재배치
        Stack<Integer> s = new Stack<>();
        int i = to;
        s.push(i);
        while(prev[i] != 0){
            s.push(prev[i]);
            i = prev[i];
        }

        // 정답 출력
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

        // cost 를 오름차순으로 정렬
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
