package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ19238_스타트택시 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, fuel;
    private static int[][] map;
    private static int[][] guestMap;
    private static Set<Integer>[][] destinationMap;

    private static Point taxi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        guestMap = new int[N][N];
        destinationMap = new Set[N][N];

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                destinationMap[y][x] = new HashSet<>();
            }
        }

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        for(int i = 1; i <= M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int guestY = Integer.parseInt(st.nextToken()) - 1;
            int guestX = Integer.parseInt(st.nextToken()) - 1;
            int destinationY = Integer.parseInt(st.nextToken()) - 1;
            int destinationX = Integer.parseInt(st.nextToken()) - 1;

            int idx = i + 1;
            guestMap[guestY][guestX] = idx;
            destinationMap[destinationY][destinationX].add(idx);
        }

        while(true) {
            if(!bfsSearchGuest()) break;
            if(!bfsSearchDestination()) break;

            M--;
            if(M == 0) break;
        }

        if(M > 0) fuel = -1;
        System.out.println(fuel);
    }

    public static boolean bfsSearchDestination() {
        int len = 0;
        int destination = taxi.guestIdx;
        boolean isFind = false;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();

        if(destinationMap[taxi.y][taxi.x].contains(destination)) {
            taxi.guestIdx = 0;
            destinationMap[taxi.y][taxi.x].remove(destination);
            return true;
        }

        visited[taxi.y][taxi.x] = true;
        q.offer(new Point(taxi.y, taxi.x));

        outer:
        while(!q.isEmpty()) {
            ++len;
            int size = q.size();
            for(int s = 0; s < size; ++s) {
                Point now = q.poll();
                for(int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || isWall(ny, nx) || visited[ny][nx]) continue;

                    if(destinationMap[ny][nx].contains(destination)) {
                        taxi.y = ny;
                        taxi.x = nx;
                        destinationMap[ny][nx].remove(destination);
                        taxi.guestIdx = 0;
                        isFind = true;
                        break outer;
                    }

                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }

        if(!isFind) return false;
        if(len > fuel) return false;
        fuel += len;

        return true;
    }

    public static boolean bfsSearchGuest() {
        int len = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();

        if(guestMap[taxi.y][taxi.x] > 1) {
            taxi.guestIdx = guestMap[taxi.y][taxi.x];
            guestMap[taxi.y][taxi.x] = 0;
            return true;
        }

        visited[taxi.y][taxi.x] = true;
        q.offer(new Point(taxi.y, taxi.x));

        while(!q.isEmpty()) {
            ++len;
            int size = q.size();
            for(int s = 0; s < size; ++s) {
                Point now = q.poll();
                for(int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || isWall(ny, nx) || visited[ny][nx]) continue;

                    if(guestMap[ny][nx] > 1) pq.offer(new Point(ny, nx));

                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }

            if(!pq.isEmpty()) break;
        }

        if(pq.isEmpty()) return false;
        if(len > fuel) return false;
        fuel -= len;

        Point guest = pq.peek();
        taxi.guestIdx = guestMap[guest.y][guest.x];
        taxi.y = guest.y;
        taxi.x = guest.x;
        guestMap[guest.y][guest.x] = 0;

        return true;
    }

    public static boolean isWall(int y, int x) {
        return map[y][x] == 1;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point implements Comparable<Point> {
        int y;
        int x;
        int guestIdx;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point p) {
            if(this.y == p.y) return Integer.compare(this.x, p.x);
            return Integer.compare(this.y, p.y);
        }
    }
}

