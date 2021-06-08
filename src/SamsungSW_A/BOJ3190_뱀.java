package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ3190_뱀 {

    public static final int[] dy = {0, 1, 0, -1};   //
    public static final int[] dx = {1, 0, -1, 0};

    public static final int APPLE = -10; // 사과의 위치

    public static int N, K, L;           // N : map Size, K : 사과의 개수, L : 뱀의 방향 전환 정보
    public static int time, dir;         // 시간, 방향, 뱀의 길이
    public static Point head, tail;      // 뱀의 머리, 꼬리 위치
    public static char[] command;        // 방향 변환 정보
    public static int[][] map;           // map
    public static boolean isApple;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) + 1;  // 0이 아닌 1부터 시작하기에 (+1)을 한다
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int y = 0; y < N; ++y) {
            Arrays.fill(map[y], -1);
        }
        command = new char[10001];

        // 사과 위치 입력
        for (int i = 0; i < K; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = APPLE;
        }

        // 뱀의 방향 전환 정보 입력
        L = Integer.parseInt(br.readLine());
        for (int c = 0; c < L; ++c) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            char input_dir = st.nextToken().charAt(0);
            command[second] = input_dir;
        }

        head = new Point(1, 1); // 머리 위치
        tail = new Point(1, 1); // 꼬리 위치
        dir = 0;                      // 오른쪽 방향부터 시작
        isApple = false;              // 사과를 먹었는 지 여부

        start(1, 1);
        System.out.println(time);     // 정답 출력
    }

    public static void start(int y, int x) {
        map[y][x] = time;

        while (true) {
            // 방향 전환
            char nextDir = command[time];
            if (nextDir == 'D') dir = (dir + 1) % 4; // 시계 방향
            if (nextDir == 'L') dir = (dir + 3) % 4; // 반시계 방향

            // 이동
            if (!moveHead()) return;    // 1. 머리부터 이동
            if (isApple) {              // 2-1. 머리가 사과를 먹으면, 꼬리 움직이기 X
                isApple = false;        // 2-2. 머리가 사과를 먹지 않으면, 꼬리 움직이기 O
                continue;
            }
            moveTail();                 // 3. 꼬리 움직이기
        }
    }

    // 머리 움직이기
    public static boolean moveHead() {
        ++time; // 시간 증가
        int ny = head.y + dy[dir];
        int nx = head.x + dx[dir];

        // 벽에 닿았을 때 (새 좌표에 대한 유호성 검사)
        if (isWall(ny, nx)) return false;

        if (map[ny][nx] == APPLE) isApple = true; // 다음 위치가 사과일 때
        else if(map[ny][nx] != -1) return false;  // 벽도, 사과도 아닌 자기 자신의 몸일 때

        map[ny][nx] = time; // map 에 등록
        head.y = ny;        // head 의 위치 갱신
        head.x = nx;

        return true;
    }

    // 꼬리 움직이기
    public static void moveTail() {
        // 4방 탐색하여 다음 곳으로 간다.
        for (int d = 0; d < 4; ++d) {
            int ny = tail.y + dy[d];
            int nx = tail.x + dx[d];

            // 다음 이동할 곳이 벽이거나 빈 곳일 때, 방향을 바꿔서 재탐색
            if (isWall(ny, nx) || map[ny][nx] != tail.nextStep) continue;

            // 다음 이동할 곳을 찾았다면,
            map[tail.y][tail.x] = -1;   // 꼬리가 있던 자리 빈곳으로 초기화
            tail.y = ny;                // 꼬리 위치 갱신
            tail.x = nx;
            tail.nextStep++;            // 다음 움직일 곳
            return;
        }

    }

    static class Point {
        int y;
        int x;
        int nextStep = 1;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    // 벽에 닿았을 때,
    public static boolean isWall(int y, int x) {
        return (y == 0 || y == N) || (x == 0 || x == N);
    }

    public static void print() {
        System.out.println("===========");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                System.out.print(map[y][x] + "      ");
            }
            System.out.println();
        }
    }
}
