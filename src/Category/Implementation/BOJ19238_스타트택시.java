package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ19238_스타트택시 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, fuel, useFuel;
    private static int[][] map;
    private static Point taxi;
    private static Point[] guests;
    private static Point[] destinations;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        taxi = new Point();
        guests = new Point[M];
        destinations = new Point[M];

        for (int y = 1; y <= N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi.y = Integer.parseInt(st.nextToken());
        taxi.x = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            guests[i] = new Point(startY, startX);
            destinations[i] = new Point(endY, endX);
            map[startY][startX] = 2; // 시작점
            map[endY][endX] = 3;    // 도착점
        }

        int index = 0;
        while (index++ < M) {
            useFuel = findGuest();
            fuel -= useFuel;

            if (isFuelEmpty() || notFoundRoute()) break;
            useFuel = move();
            fuel -= useFuel;
            if (isFuelEmpty() || notFoundRoute()) break;
            fuel += useFuel * 2;

        }
        if(isFuelEmpty() || notFoundRoute()) fuel = -1;
        System.out.println(fuel);
    }

    // 손님 찾기
    public static int findGuest() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        List<Point> starts = new ArrayList<>();
        int dis = 0;
        q.offer(taxi);
        visited[taxi.y][taxi.x] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            dis++;
            for (int s = 0; s < size; ++s) {
                Point now = q.poll();
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 1) continue;

                    if (map[ny][nx] == 2) {
                        starts.add(new Point(ny, nx));
                    }
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
            if (starts.size() != 0) break;
        }
        Collections.sort(starts);
        if(starts.size() == 0) return 0;
        Point start = starts.get(0);

        // 손님을 찾으면 손님의 위치로 이동
        map[start.y][start.x] = -1;
        taxi.y = start.y;
        taxi.x = start.x;
        return dis;
    }

    // 손님의 위치에서 목적지로 이동
    public static int move() {
        int index = -1;
        int dis = 0;
        for (int i = 0; i < M; ++i) {
            Point guest = guests[i];
            if (guest.y == taxi.y && guest.x == taxi.x) index = i;
        }

        Point destination = destinations[index];
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        visited[taxi.y][taxi.x] = true;
        q.offer(taxi);

        outer:
        while (!q.isEmpty()) {
            int size = q.size();
            dis++;
            for (int i = 0; i < size; ++i) {
                Point now = q.poll();
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 1) continue;

                    if (destination.y == ny && destination.x == nx) {
                        taxi.y = ny;
                        taxi.x = nx;
                        break outer;
                    }
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }
        return dis;
    }

    public static boolean notFoundRoute(){
        return useFuel == 0;
    }

    public static boolean isFuelEmpty() {
        return fuel < 0;
    }

    public static boolean isIn(int y, int x) {
        return y >= 1 && x >= 1 && y <= N && x <= N;
    }

    static class Point implements Comparable<Point> {
        int y;
        int x;

        public Point() {
        }

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point p) {
            if (this.y == p.y) return Integer.compare(this.x, p.x);
            return Integer.compare(this.y, p.y);
        }

        @Override
        public String toString() {
            return "Point : " + y + " " + x;
        }
    }
}
