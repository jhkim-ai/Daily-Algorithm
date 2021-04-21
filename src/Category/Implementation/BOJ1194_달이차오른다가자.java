package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ1194_달이차오른다가자 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M;
    static char[][] map;
    static boolean[][][] visited;
    static Point start;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        // 맵 설정
        for (int y = 0; y < N; ++y) {
            char[] tmp = br.readLine().toCharArray();
            for (int x = 0; x < M; ++x) {
                map[y][x] = tmp[x];
                // 시작점
                if (map[y][x] == '0') {
                    start = new Point(y, x, 0);
                    map[y][x] = '.';
                }
            }
        }

        // Idea. 키가 있는지 없는지
        //       키의 유/무에 따른 방문상태를 체크해야하는 것이 관건.

        // 키 관리 = Bit Masking
        // a : 0000001
        // b : 0000010
        // c : 0000100
        // a 와 c 키가 존재한다면 : a | c (000101) => visited[ny][nx][5] = true;
        // A 문에 도착했다면, key 검사 : if(c & (1 << (C - 'A'))) > 0) => key 가 존재
        //                              c & (0000101)

        // bfs 탐색 후, 이동거리 출력
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        visited = new boolean[N][M][1 << 6];
        visited[start.y][start.x][start.key] = true;
        int count = 0;  // 이동거리

        while (!q.isEmpty()) {

            int size = q.size();
            for (int i = 0; i < size; ++i) {
                Point now = q.poll();
//                System.out.println(now.y + " : " + now.x);
                int key = now.key;

                // 도착 지점
                if (map[now.y][now.x] == '1')
                    return count;

                // 4방 이동
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    // 범위 검사
                    if (!isIn(ny, nx)) continue;
                    // 지나온 곳 검사 (key 를 가진 수 = 방문 체크 요소)
                    if (visited[ny][nx][now.key]) continue;

                    // 다음 장소
                    char next = map[ny][nx];
                    // 벽 검사
                    if(next == '#') continue;

                    // key 라면?
                    if (isKey(ny, nx)) {
                        int nextKey = (1 << (next - 'a')) | key;
                        q.offer(new Point(ny, nx, nextKey));
                        visited[ny][nx][nextKey] = true;
                    }
                    // door 라면?
                    else if (isDoor(ny, nx) && hasKey(ny, nx, key)) {
                        q.offer(new Point(ny, nx, key));
                        visited[ny][nx][key] = true;
                    }
                    else if(next == '1' || next == '.'){
                        q.offer(new Point(ny, nx, key));
                        visited[ny][nx][key] = true;
                    }
                }
            }
            count++;
        }
        return -1;
    }
    static void print(int y, int x, int ny, int nx){

    }
    // '문'에 대한 '키'를 가지고 있을 때
    static boolean hasKey(int ny, int nx, int key) {
        int nextDoor = map[ny][nx] - 'A';

        // '키'를 있다면,
        if ((key & (1 << nextDoor)) > 0) {
            return true;
        }
        // '키'가 없다면
        return false;
    }

    // 다음 방문이 '문'일 경우
    static boolean isDoor(int ny, int nx) {
        char next = map[ny][nx];
        return next >= 'A' && next <= 'F';
    }

    // 다음 방문이 '열쇠'가 놓여진 경우
    static boolean isKey(int ny, int nx) {
        char next = map[ny][nx];
        return next >= 'a' && next <= 'f';
    }

    // 맵의 범위 check
    static boolean isIn(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static class Point {
        int y;
        int x;
        int key;

        public Point(int y, int x, int key) {
            this.y = y;
            this.x = x;
            this.key = key;
        }
    }

    static String input = "7 8\n" +
            "a#c#eF.1\n" +
            ".#.#.#..\n" +
            ".#B#D###\n" +
            "0....F.1\n" +
            "C#E#A###\n" +
            ".#.#.#..\n" +
            "d#f#bF.1";
}
