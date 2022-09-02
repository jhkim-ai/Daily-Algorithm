package Category.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1944_복제로봇 {

    private static final int MAX_VAL = 300;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, cntKey;
    private static int[] root, rank;
    private static char[][] map;
    private static List<Point> keys;
    private static Map<Integer, Integer> mapKeyToIdx;
    private static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][N];
        keys = new ArrayList<>();
        pq = new PriorityQueue<>();
        mapKeyToIdx = new HashMap<>();

        cntKey = 1;
        for(int y = 0; y < N; ++y) {
            map[y] = br.readLine().toCharArray();
            for(int x = 0; x < N; ++x){
                int arrIdx = y * N + x;
                if(map[y][x] == 'S') {
                    keys.add(new Point(y, x));
                    mapKeyToIdx.put(arrIdx, 1);
                } else if(map[y][x] == 'K') {
                    keys.add(new Point(y, x));
                    mapKeyToIdx.put(arrIdx, ++cntKey);
                }
            }
        }

        for(Point point : keys) {
            bfs(point);
        }

        makeSet();

        int cnt = 0;
        long ans = 0;

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(union(edge.from, edge.to)) {
                ans += edge.cost;
                cnt++;
                if(cnt == cntKey - 1) break;
            }
        }

        if(cnt != cntKey - 1) ans = -1;
        System.out.println(ans);
    }

    public static void bfs(Point point) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        visited[point.y][point.x] = true;
        q.offer(new Point(point.y, point.x));

        int len = 0;
        int startKeyIdx = mapKeyToIdx.get(point.y * N  + point.x);

        while(!q.isEmpty()) {
            int size = q.size();
            ++len;
            for(int s = 0; s < size; ++s) {
                Point now = q.poll();
                for(int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == '1') continue;

                    if(map[ny][nx] == 'S' || map[ny][nx] == 'K') {
                        pq.offer(new Edge(startKeyIdx, mapKeyToIdx.get(ny * N + nx),len));
                    }

                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void makeSet() {
        rank = new int[cntKey + 1];
        root = new int[cntKey + 1];
        for(int idx = 0; idx <= cntKey; ++idx) {
            root[idx] = idx;
        }
    }

    public static int find(int a) {
        if(a == root[a]) return a;
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(rank[a] < rank[b]) {
            root[a] = b;
        } else {
            root[b] = a;
            if(rank[a] == rank[b]){
                rank[a]++;
            }
        }

        return true;
    }

    public static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }

        @Override
        public String toString() {
            return "[" + from + "-" + to + "] " + cost;
        }
    }
}
