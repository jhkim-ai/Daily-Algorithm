package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class _21_BOJ21608_상어초등학교 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N;
    private static int[] order;
    private static int[][] map;
    private static Set<Integer>[] hopeToBe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        order = new int[N*N + 1];
        hopeToBe = new Set[N*N + 1];
        map = new int[N][N];

        for(int i = 1; i <= N*N; ++i){
            hopeToBe[i] = new HashSet<>();
        }

        for(int i = 1; i <= N*N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int idx = Integer.parseInt(st.nextToken());
            order[i] = idx;
            for(int n = 0; n < 4; ++n){
                hopeToBe[idx].add(Integer.parseInt(st.nextToken()));
            }
        }

        PriorityQueue<Point> pq = new PriorityQueue<>();
        for(int i = 1; i <= N*N; ++i) {
            pq.clear();
            for (int y = 0; y < N; ++y) {
                for (int x = 0; x < N; ++x) {
                    if(map[y][x] != 0) continue;
                    pq.offer(getPointInfo(order[i], y, x));
                }
            }
            Point selected = pq.poll();
            map[selected.y][selected.x] = order[i];
        }

        System.out.println(getSatisfaction());
    }

    public static Point getPointInfo(int stuNo, int y, int x){
        Point p = new Point();
        int adj = 0;
        int empty = 0;

        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx)) continue;

            if(hopeToBe[stuNo].contains(map[ny][nx])) adj++;
            if(map[ny][nx] == 0) empty++;
        }

        p.y = y;
        p.x = x;
        p.adj = adj;
        p.empty = empty;

        return p;
    }

    public static int getSatisfaction(){
        int sum = 0;

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                int cnt = 0;
                int stuNo = map[y][x];
                for(int d = 0; d < 4; ++d){
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx)) continue;
                    if(hopeToBe[stuNo].contains(map[ny][nx])) ++cnt;
                }

                sum += (int)Math.pow(10, cnt-1);
            }
        }

        return sum;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point implements Comparable<Point>{
        int y;
        int x;
        int adj;
        int empty;

        public Point(){

        }

        public Point(int y, int x, int adj, int empty){
            this.y = y;
            this.x = x;
            this.adj = adj;
            this.empty = empty;
        }

        @Override
        public int compareTo(Point p){
            if(this.adj == p.adj){
                if(this.empty == p.empty){
                    if(this.y == p.y){
                        return Integer.compare(this.x, p.x);
                    }
                    return Integer.compare(this.y, p.y);
                }
                return Integer.compare(p.empty, this.empty);
            }
            return Integer.compare(p.adj, this.adj);
        }
    }
}
