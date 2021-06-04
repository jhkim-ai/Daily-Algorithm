package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ13460_구슬탈출2 {

    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, M;                     // N : row size, M: col size
    public static char[][] map;                 // map

    public static Point blueBead, redBead;      // 빨간 or 파란 구슬의 초기 좌표
    public static Point nowBlue, newBlue;       // now : 현재 (파란, 빨간) 구슬의 위치 (값을 보존 하기 위해 선언)
    public static Point nowRed, newRed;         // new : 새로운 (파란, 빨간) 구슬의 위치
    public static boolean isBlueOut, isRedOut;  // 각 구슬이 탈출 했는지 안했는지 check

    public static Queue<Point> rq, bq;          // rq: 빨간 구슬 Queue, bq: 파란 구슬 Queue

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // ========= 입력 ========= //

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int y = 0; y < N; ++y) {
            String str = br.readLine();
            for (int x = 0; x < M; ++x) {
                map[y][x] = str.charAt(x);
                if (map[y][x] == 'B') blueBead = new Point(y, x, 0);
                if (map[y][x] == 'R') redBead = new Point(y, x, 0);
            }
        }

        // ========= 알고리즘 ========= //
        System.out.println(bfs());
    }

    // bfs 탐색 (최단 거리)
    public static int bfs() {
        rq = new LinkedList<>();    // 빨간 구슬 움직일 방향 저장
        bq = new LinkedList<>();    // 파란 구슬 움직일 방향 저장
        rq.offer(redBead);
        bq.offer(blueBead);

        while (!rq.isEmpty()) {
            nowRed = rq.poll();     // 다른 방향으로 움직일 시,
            nowBlue = bq.poll();    // 기준값이 변형 되기 때문에 값 보존용으로 변수를 하나 선언

            if (nowRed.move > 10) return -1; // 빨간 구슬의 이동 횟수가 10번 이상이면, Fail

            for (int d = 0; d < 4; ++d) {
                isBlueOut = false;  // 파란 구슬 탈출 여부
                isRedOut = false;   // 빨간 구슬 탈출 여부
                newBlue = new Point(nowBlue.y, nowBlue.x, nowBlue.move);    // 새로운 방향으로 움직일 구슬
                newRed = new Point(nowRed.y, nowRed.x, nowRed.move);

                // 방향과 구슬의 위치에 따라 움직이는 순서가 결정된다.
                switch (d) {
                    // 상
                    case 0:
                        if (nowRed.y > nowBlue.y) blueFirst(d); // 파란 구슬이 먼저 움직이기
                        else redFirst(d);                       // 빨간 구슬이 먼저 움직이기
                        break;
                    // 하
                    case 1:
                        if (nowRed.y > nowBlue.y) redFirst(d);
                        else blueFirst(d);
                        break;
                    // 좌
                    case 2:
                        if (nowRed.x > nowBlue.x) blueFirst(d);
                        else redFirst(d);
                        break;
                    // 우
                    case 3:
                        if (nowRed.x > nowBlue.x) redFirst(d);
                        else blueFirst(d);
                        break;
                }

                // 빨간 구슬만 들어갔을 때 (성공)
                if (isRedOut && !isBlueOut) {
                    int ans = nowRed.move + 1;
                    if(ans > 10) continue;  // 10번 초과일 경우가 존재하므로 예외처리
                    else return ans;
                }

                if (isRedOut && isBlueOut) continue; // 빨간 구슬 + 파란 구슬이 모두 탈출 될 때 (실패)
                if (isBlueOut) continue; // 파란 구슬이 들어갔을 때 (실패)

                // 움직인 구슬들을 Queue 에 삽입
                rq.offer(new Point(newRed.y, newRed.x, newRed.move + 1));
                bq.offer(new Point(newBlue.y, newBlue.x, newBlue.move + 1));
            }
        }
        return -1;
    }

    // 파란 구슬 먼저 움직이기
    public static void blueFirst(int d) {
        isBlueOut = moveBlue(d);
        isRedOut = moveRed(d);
    }

    // 빨간 구슬 먼저 움직이기
    public static void redFirst(int d) {
        isRedOut = moveRed(d);
        isBlueOut = moveBlue(d);
    }

    // 파란 구슬 움직이기
    public static boolean moveBlue(int d) {
        // 0. 새 좌표
        int ny = newBlue.y + dy[d];
        int nx = newBlue.x + dx[d];

        // 1. 벽이나 빨간 구슬에 부딪힐 때
        if (map[ny][nx] == '#' || (ny == newRed.y && nx == newRed.x)) return false;

        // 위의 조건문을 통과하면 무조건 움직임. 따라서 아래와 같이 진행
        // 2. 출구에 도착할 때 => 파란 공의 좌표를 없애버린다
        if (map[ny][nx] == 'O') {
            newBlue.y = -1;
            newBlue.x = -1;
            return true;
        }
        // 3. 좌표 갱신
        newBlue.y = ny;
        newBlue.x = nx;

        return moveBlue(d); // 4. 다음 칸 이동
    }

    // 빨간 구슬 움직이기
    public static boolean moveRed(int d) {
        // 0. 새 좌표
        int ny = newRed.y + dy[d];
        int nx = newRed.x + dx[d];

        // 1. 벽이나 파란 구슬에 부딪힐 때
        if (map[ny][nx] == '#' || (ny == newBlue.y && nx == newBlue.x)) { return false;}

        // 위의 조건문을 통과하면 무조건 움직임. 따라서 아래와 같이 진행
        // 2. 출구에 도착할 때
        if (map[ny][nx] == 'O') {
            newRed.y = -1;
            newRed.x = -1;
            return true;
        }
        // 3. 좌표 갱신
        newRed.y = ny;
        newRed.x = nx;

        return moveRed(d); // 4. 다음 칸 이동
    }

    // 좌표와 이동 횟수 정보를 관리할 Point Class
    static class Point {
        int y;      // row 위치
        int x;      // col 위치
        int move;   // 이동 횟수

        public Point(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
}
