package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class _16_BOJ16236_아기상어 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, ans;
    private static Point shark;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ans = 0;

        for(int y = 0; y < N; y++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == 9){
                    shark = new Point(y, x);
                    map[y][x] = 0;
                }
            }
        }

        run();
        System.out.println(ans);
    }

    public static void run(){
        while(true) {
            PriorityQueue<Point> pq = getFeedList();

            if(pq.isEmpty()) break;

            Point feed = pq.poll();
            ans += feed.distance;
            shark.feed++;

            if(shark.feed == shark.size) {
                shark.size++;
                shark.feed = 0;
            }

            shark.y = feed.y;
            shark.x = feed.x;
            map[feed.y][feed.x] = 0;
        }
    }

    public static PriorityQueue<Point> getFeedList(){
        PriorityQueue<Point> feedList = new PriorityQueue<>();
        Queue<Point> q = new LinkedList<>();
        int[][] copyMap = getCopyMap();
        boolean[][] visited = new boolean[N][N];

        visited[shark.y][shark.x] = true;
        q.offer(new Point(shark.y, shark.x));

        while(!q.isEmpty()){
            int qSize = q.size();

            for(int s = 0; s < qSize; ++s){
                Point now = q.poll();
                int nDistance = now.distance;

                for(int d = 0; d < 4; ++d){
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || visited[ny][nx] || copyMap[ny][nx] > shark.size) {
                        continue;
                    }

                    if(copyMap[ny][nx] > 0 && copyMap[ny][nx] < shark.size){
                        feedList.offer(new Point(ny, nx, nDistance + 1));
                    }

                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx, nDistance + 1));
                }
            }
            if(feedList.size() > 0) break;
        }
        return feedList;
    }

    public static int[][] getCopyMap(){
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }
        return copyMap;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point implements Comparable<Point>{
        int y;
        int x;
        int size;
        int feed;
        int distance;

        // 상어
        public Point(int y, int x){
            this.y = y;
            this.x = x;
            this.size = 2;
            this.feed = 0;
        }

        // 먹이
        public Point(int y, int x, int distance){
            this.y = y;
            this.x = x;
            this.distance = distance;
        }

        @Override
        public int compareTo(Point p){
            if(this.y == p.y){
                return Integer.compare(this.x, p.x);
            }
            return Integer.compare(this.y, p.y);
        }
    }
}
