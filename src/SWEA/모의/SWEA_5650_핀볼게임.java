package SWEA.모의;

import java.util.*;
import java.io.*;

public class SWEA_5650_핀볼게임 {

    public static final int[] dy = {-1, 0, 1, 0}; // 상, 우, 하, 좌 (시계방향)
    public static final int[] dx = {0, 1, 0, -1};

    public static int N, ans;
    public static int dir;
    public static int[][] map, copyMap;
    public static Point start, now;
    public static Point[][] wormHoles;
    public static List<Point> blackHoles;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            blackHoles = new ArrayList<>();
            wormHoles = new Point[11][2];
            ans = Integer.MIN_VALUE;

            // map 입력
            for (int y = 0; y < N; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    int num = map[y][x];
                    if (isBlackHole(num)) blackHoles.add(new Point(y, x)); // 블랙홀 정보 저장
                    if (isWormHole(num)) {                                 // 웜홀 정보 저장
                        if (wormHoles[num][0] != null) wormHoles[num][1] = new Point(y, x);
                        else wormHoles[num][0] = new Point(y, x);
                    }
                }
            }

            // map 전체 중, 시작할 수 있는 모든 공간을 탐색
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (map[y][x] != 0) continue;
                    search(y, x);
                }
            }
            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    public static void search(int y, int x) {
//        System.out.println("search 입니다 " + y + " : " + x);
        start = new Point(y, x); // 시작점 저장 (값 보존 위함)

        for (int d = 0; d < 4; d++) {
            int ny = start.y + dy[d];
            int nx = start.x + dx[d];

            if (!isIn(ny, nx)) continue; // 시작점에서 벽으로 출발할 필요가 없음
            if (!isPossible(ny, nx, d)) continue; // 시작점에서 block 의 수직면 혹은 수평면이면 갈 필요가 없음
            now = new Point(start.y, start.x);   // 핀볼이 움직인 위치를 저장하기 위한 변수
            int hit = play(d);
            ans = Math.max(hit, ans);
        }
    }

    static int index;

    public static int play(int d) {
//        System.out.println("play 합수입니다");
//        copyMap = new int[N][N];
        int hit = 0;
        dir = d; // 초기 시작 방향 설정
        boolean isWall = false;

        while (true) {
//            print();
//            System.out.println("시작점 = " + start.y + " : " + start.x);
//            System.out.println("방향 = " + dir);
//            System.out.println("현재 좌표 = " + now.y + " : " + now.x);
//            index++;
//            if (index > 100) System.exit(0);

            int ny = now.y + dy[dir];
            int nx = now.x + dx[dir];

//            if (isIn(ny, nx))
//                System.out.println("map[ny][nx] = " + map[ny][nx]);

            if (ny == start.y && nx == start.x) break;            // 원래 자리로 돌아온 경우, 종료
            if (isIn(ny, nx) && isBlackHole(map[ny][nx])) break;  // 블랙홀에 닿은 경우, 종료

            // ex : 위로 가는 중 웜홀을 만남 => 다른 웜홀로 이동 후, 바로 위가 벽일 경우
            if(isWall && isWormHole(map[now.y][now.x])) {
                moveWormHole();
                isWall = false;
                continue;
            }

            // ex : 왼쪽으로 가는 중 1번 벽돌을 만나고, 바로 위에 벽이 있을 경우
            if(isWall && isBlock(map[now.y][now.x])){
                changeDir(map[now.y][now.x]);
                isWall = false;
                ++hit;
                continue;
            }

            // 벽에 부딪친다면, 반대 방향으로 전환
            if (!isIn(ny, nx)) {
                dir = (dir + 2) % 4;
                ++hit; // 벽에 부딪쳤기 때문에 점수 증가
                isWall = true; // 벽에 부딪힘
                continue;
            }

            int nextMap = map[ny][nx];

            // 블록에 부딪칠 때
            if (isBlock(nextMap)) {
                if(isWall) isWall = false;
                forwardMove(); // block 으로 먼저 이동
                changeDir(nextMap); // 방향 전환
                ++hit;
                continue;
            }

            // 웜홀에 닿을 때 (방향 보존)
            if (isWormHole(nextMap)) {
                if(isWall) isWall = false;
                forwardMove(); // wormHole 로 먼저 이동
                moveWormHole(); // 다음 wormHole 위치로 이동
                continue;
            }

            // 아무것도 없을 시, 그냥 전진
            forwardMove();
            isWall = false;
        }

        return hit;
    }

    public static void changeDir(int num) {
        // dir : 0-상, 1-우, 2-하, 3-좌
        switch (num) {
            case 1:
                // 경사면에 대한 방향 전환
                if (dir == 2) dir = 1;      // 하 => 우
                else if (dir == 3) dir = 0; // 좌 => 상
                    // 수평면 혹은 수직면이기에 반대 방향으로 전환
                else {
                    dir = (dir + 2) % 4;   // 반대 방향으로 전환
                }
                break;
            case 2:
                // 경사면에 대한 방향 전환
                if (dir == 0) dir = 1;      // 상 => 우
                else if (dir == 3) dir = 2; // 좌 => 하
                    // 수평면 혹은 수직면이기에 반대 방향으로 전환
                else {
                    dir = (dir + 2) % 4;   // 반대 방향으로 전환
                }
                break;
            case 3:
                // 경사면에 대한 방향 전환
                if (dir == 0) dir = 3;      // 상 => 좌
                else if (dir == 1) dir = 2; // 우 => 하
                    // 수평면 혹은 수직면이기에 반대 방향으로 전환
                else {
                    dir = (dir + 2) % 4;   // 반대 방향으로 전환
                }
                break;
            case 4:
                // 경사면에 대한 방향 전환
                if (dir == 1) dir = 0;      // 우 => 상
                else if (dir == 2) dir = 3; // 하 => 좌
                    // 수평면 혹은 수직면이기에 반대 방향으로 전환
                else {
                    dir = (dir + 2) % 4;   // 반대 방향으로 전환
                }
                break;
            case 5:
                dir = (dir + 2) % 4;
                break;
        }
    }

    // 한 칸 앞으로 움직이기
    public static void forwardMove() {
        now.y = now.y + dy[dir];
        now.x = now.x + dx[dir];
    }

    public static void print() {
        copyMap[now.y][now.x] = 1;
        System.out.println("=============");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(copyMap[y][x] + " ");
            }
            System.out.println();
        }
    }

    // 웜홀에 닿을 경우, 다른 웜홀로 빠져 나온다.
    public static void moveWormHole() {
        int y = now.y;
        int x = now.x;

        int num = -1;
        int idx = -1;

        out:
        for (int i = 6; i <= 10; i++) {
            for (int j = 0; j < 2; j++) {
                Point wormHole = wormHoles[i][j];
                if (y == wormHole.y && x == wormHole.x) {
                    num = i;
                    idx = (j + 1) % 2; // 다른 웜홀을 선택
                    break out;
                }
            }
        }

        Point nextWormHole = wormHoles[num][idx];
        now.y = nextWormHole.y;
        now.x = nextWormHole.x;
    }

    // BlackHole 인가?
    public static boolean isBlackHole(int n) {
        return n == -1;
    }

    // Wormhole 인가?
    public static boolean isWormHole(int n) {
        return n >= 6 && n <= 10;
    }

    // block 인가?
    public static boolean isBlock(int n) {
        return n > 0 && n < 6;
    }

    // 시작점에서 block 의 수평면 혹은 수직면으로 시작한다면 굳이 볼 필요 없다.
    public static boolean isPossible(int ny, int nx, int d) {
        int nextMap = map[ny][nx];
        switch (d) {
            case 0:
                if (nextMap == 1 || nextMap == 4) return false;
                break;
            case 1:
                if (nextMap == 1 || nextMap == 2) return false;
                break;
            case 2:
                if (nextMap == 2 || nextMap == 3) return false;
                break;
            case 3:
                if (nextMap == 3 || nextMap == 4) return false;
                break;
        }
        return true;
    }

    // 범위 검사
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
