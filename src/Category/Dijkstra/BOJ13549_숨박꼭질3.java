package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ13549_숨박꼭질3 {

    private static final int MAX_DIST = 100001;

    private static int N, M;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[MAX_DIST];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[N] = 0;

        setAllDist();

        System.out.println(dist[M]);
    }

    public static void setAllDist() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[MAX_DIST];
        pq.offer(new Node(N, 0));

        while(!pq.isEmpty()) {
            Node nowNode = pq.poll();
            int nowLoc = nowNode.loc;

            if(nowLoc == M) return;

            if(visited[nowLoc]) continue;
            visited[nowLoc] = true;

            int after = nowLoc + 1;
            int before = nowLoc - 1;
            int teleport = nowLoc * 2;

            if(isIn(after) && dist[after] > dist[nowLoc] + 1) {
                dist[after] = dist[nowLoc] + 1;
                pq.offer(new Node(after, dist[after]));
            }

            if(isIn(before) && dist[before] > dist[nowLoc] + 1) {
                dist[before] = dist[nowLoc] + 1;
                pq.offer(new Node(before, dist[before]));
            }

            if(isIn(teleport) && dist[teleport] > dist[nowLoc]) {
                dist[teleport] = dist[nowLoc];
                pq.offer(new Node(teleport, dist[teleport]));
            }
        }
    }

    public static boolean isIn(int loc) {
        return loc >= 0 && loc < MAX_DIST;
    }

    public static class Node implements Comparable<Node> {
        int loc;
        int time;

        public Node(int loc, int time) {
            this.loc = loc;
            this.time = time;
        }

        @Override
        public int compareTo(Node n) {
            return Integer.compare(this.time, n.time);
        }
    }
}
