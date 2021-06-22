package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ19238_스타트택시 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static final int WALL = 1;
    private static final int SOMEONE = 2;

    private static int N, M, gas;
    private static int guestIndex;
    private static int[][] map;
    private static Point taxi;
    private static Point[] guests, destinations;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정보 입력
        N = Integer.parseInt(st.nextToken()); // map 사이즈
        M = Integer.parseInt(st.nextToken()); // 손님 수
        gas = Integer.parseInt(st.nextToken()); // 연료 잔량
        map = new int[N + 1][N + 1];
        guests = new Point[M];  // 손님 정보 모음
        destinations = new Point[M]; // 각 손님에 대한 목적지 모음
        guestIndex = -1; // 몇 번째 손님인지 찾기 위함

        // map 정보 입력
        for (int y = 1; y <= N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 택시 처음 위치
        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        // 손님 위치 & 목적지
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int startY = Integer.parseInt(st.nextToken());
            int startX = Integer.parseInt(st.nextToken());
            int endY = Integer.parseInt(st.nextToken());
            int endX = Integer.parseInt(st.nextToken());
            guests[i] = new Point(startY, startX);
            destinations[i] = new Point(endY, endX);

            // 모든 출발지는 서로 다름
            // 각 손님의 출발지와 목적지는 다름
            map[startY][startX] = SOMEONE; // 손님 위치
        }

        boolean isNotPossible = false;
        int cnt = 0;
        while (cnt++ < M) {
            // 각각의 경우에 연료가 없거나 손님이나 목적지를 찾을 수 없는 경우
            if(!findGuest() || !moveToDestination()){
                isNotPossible = true;
                break;
            }
        }

        if(isNotPossible) gas = -1;
        System.out.println(gas);
    }

    // 1. 가장 가까운 손님의 위치 찾고 움직이기
    public static boolean findGuest() {

        // 택시 위치가 손님의 위치에 있는지 check
        for (Point guest : guests) {
            if (!guest.success && guest.y == taxi.y && guest.x == taxi.x) {
                map[guest.y][guest.x] = 0;
                return true;
            }
        }

        Queue<Point> q = new LinkedList<>();
        List<Point> selectedGuests = new ArrayList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.offer(taxi);
        visited[taxi.y][taxi.x] = true;
        int dis = 0;

        // bfs 탐색
        while (!q.isEmpty()) {
            int size = q.size(); // 현재 큐의 크기 = 이동할 좌표들
            dis++;
            for (int s = 0; s < size; ++s) {
                Point now = q.poll();

                // 상, 하, 좌, 우 탐색
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    // 배열 범위를 벗어나거나, 이미 방문했거나, 장애물일 경우 => 탐색 제외
                    if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == WALL) continue;

                    // 손님이 방문 되었다면, 손님의 좌표를 등록한다. (같은 거리의 손님이 존재할 수 있음)
                    if (map[ny][nx] == SOMEONE) selectedGuests.add(new Point(ny, nx));
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
            if (selectedGuests.size() != 0) break;
        }

        // 손님을 찾을 수 없다면 -1
        if (selectedGuests.size() == 0) return false;

        Collections.sort(selectedGuests);
        Point guest = selectedGuests.get(0);

        // 손님의 위치로 dis 만큼 이동
        taxi.y = guest.y;
        taxi.x = guest.x;
        map[taxi.y][taxi.x] = 0; // 손님을 태웠다면 0으로 바꾸자
        gas -= dis;

        // 음수가 나온다면, 가지고 있던 연료안에서 움직일 수 없는 경우임으로 -1 출력
        if (gas < 0) return false;
        return true;
    }

    // 2. 손님을 목적지로 이동시키기
    public static boolean moveToDestination() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.offer(taxi);
        visited[taxi.y][taxi.x] = true;
        int dis = 0;

        // 몇 번째 손님인지 찾기 (도착지를 알기 위함)
        for (int i = 0; i < M; ++i) {
            if (guests[i].y == taxi.y && guests[i].x == taxi.x)
                guestIndex = i;
        }

        // bfs 탐색
        while (!q.isEmpty()) {
            int size = q.size();
            ++dis;
            for (int s = 0; s < size; ++s) {
                Point now = q.poll();
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    // 배열 범위 밖이거나, 방문했거나, 장애물인 경우 => 탐색 제외
                    if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == WALL) continue;

                    // 목적지에 도착한 경우
                    if (ny == destinations[guestIndex].y && nx == destinations[guestIndex].x) {
                        taxi.y = ny; // 목적지로 택시 이동
                        taxi.x = nx;
                        guests[guestIndex].success = true; // 현재 손님은 운행 완료

                        gas -= dis; // 이동거리만큼 연료를 뺀다
                        if (gas < 0) return false; // 만약 gas가 0보다 작다면, 이동할 수 없는 거리임으로 -1 출력
                        gas += dis * 2; // 연료만큼 갈 수 있다면, 조건에 맞게 이동거리의 2배만큼 연료를 채운다.
                        return true;
                    }
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }
        return false; // 도착지를 찾을 수 없는 경우임으로 -1 출력
    }

    // 배열 범위의 유효성 검사
    public static boolean isIn(int y, int x) {
        return y > 0 && x > 0 && y <= N && x <= N;
    }

    static class Point implements Comparable<Point> {
        int y;
        int x;
        boolean success = false;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point p) {
            if (this.y == p.y) return Integer.compare(this.x, p.x);
            else return Integer.compare(this.y, p.y);
        }

        @Override
        public String toString() {
            return this.y + " " + this.x;
        }
    }
}

