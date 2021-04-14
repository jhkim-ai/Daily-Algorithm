package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ17144_미세먼지안녕 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M, T;
    static int[][] map;
    static Point[] machine;
    static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        T = Integer.parseInt(st.nextToken());   // T초 후
        q = new LinkedList<>();                 // 미세 먼지 위치
        machine = new Point[2];                 // 공기 청저기 위치 (위, 아래)
        map = new int[N][M];

        int index = 0;
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == -1) {                      // 공기 청정기 위치 (위부터 아래)
                    machine[index++] = new Point(y, x);
                } else if (map[y][x] != 0) {                // 미세 먼지 위치
                    q.offer(new Point(y, x));
                }
            }
        }
        for (int t = 0; t < T; t++) {
            // 1. 미세먼지 확산
            spreadDust();
            // 2. 공기 청정기 순환
            airPurifier();
            // 3. 새로운 먼지 위치를 Queue에 저장
            setLocationOfDust();
        }

        // 4. 미세먼지의 양 구하기
        System.out.println(getCountOfDust());
    }

    // 1. 미세먼지 확산
    static void spreadDust() {

        int[][] tempMap = new int[N][M];

        // (1)-1 먼지가 입력과 동시에 Queue에 들어간 상태니, 하나씩 뽑으며 확산을 진행
        int size = q.size();                    // (1)-2. Queue에 저장된 먼지의 개수만큼 진행
        while (size-- > 0) {
            Point now = q.poll();               // (1)-3. 한 먼지 좌표를 Get
            int dustSize = map[now.y][now.x];   // (1)-4. 현 위치의 먼지의 양 Get
            dustSize /= 5;                      // (1)-5. 확산시킬 먼지의 양 (N/5)

            for (int d = 0; d < 4; d++) {       // (1)-6. 새좌표
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (!isIn(ny, nx) || map[ny][nx] == -1) continue;

                tempMap[ny][nx] += dustSize;        // (1)-7. 확산될 다음 새위치에 먼지( (1)-5 )를 임시 Map에 더한다.
                map[now.y][now.x] -= dustSize;      // (1)-8. 확산 후, 현 위치의 먼지의 양을 줄인다.
            }
        }

        // 임시저장했던 학산된 먼지를 원래 Map으로 갱신한다.
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                map[y][x] += tempMap[y][x];
            }
        }
    }

    // 2. 공기 청정기 순환
    static void airPurifier() {
        // (2)-1. 반시계  (행: 0~2, 열: 0~M-1)
        Point top = machine[0];
        for (int i = top.y - 1; i >= 1; i--) {    // 왼쪽면
            map[i][0] = map[i - 1][0];
        }
        for (int i = 1; i < M; i++) {       // 윗면
            map[0][i - 1] = map[0][i];
        }
        for (int i = 1; i <= top.y; i++) {  // 오른쪽면
            map[i - 1][M - 1] = map[i][M - 1];
        }
        for (int i = M - 1; i >= 2; i--) {    // 아랫면
            map[top.y][i] = map[top.y][i - 1];
        }
        map[top.y][1] = 0;

        // (2)-2. 시계
        Point down = machine[1];
        for (int i = down.y + 1; i < N - 1; i++) {    // 왼쪽면
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < M - 1; i++) {       // 아랫면
            map[N - 1][i] = map[N - 1][i + 1];
        }
        for (int i = N - 1; i > down.y; i--) {   // 오른쪽면
            map[i][M - 1] = map[i - 1][M - 1];
        }
        for (int i = M - 1; i > 1; i--) {         // 윗면
            map[down.y][i] = map[down.y][i - 1];
        }
        map[down.y][1] = 0;
    }

    // 3. 새로운 먼지 위치를 Queue에 저장
    static void setLocationOfDust() {
        q.clear();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == -1) continue;
                if (map[y][x] != 0) {
                    q.offer(new Point(y, x));
                }
            }
        }
    }

    // 4. 미세먼지의 양 구하기
    static int getCountOfDust() {
        int cnt = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == -1) continue;
                if (map[y][x] != 0) {
                    cnt+=map[y][x];
                }
            }
        }
        return cnt;
    }

    static boolean isIn(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void print() {
        System.out.println("===============");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
