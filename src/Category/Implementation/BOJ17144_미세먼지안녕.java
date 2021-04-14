package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ17144_미세먼지안녕 {

    static final int[] dy = {-1, 0, 1, 0};  // 상, 우, 하, 좌 (배열 돌리기를 위함)
    static final int[] dx = {0, 1, 0, -1};

    static int N, M, T;
    static int pos;         // 공기 청정기 위치
    static int[][] map;     // 원본
    static int[][] divide;  // 분할할 수 있는 미세 먼지 저장 (원본 훼손 방지)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == -1) {
                    pos = y;    // 공기청정기 하단부를 저장
                }
            }
        }

        while(T-- > 0) {
            // 1. 미세먼지 확산
            spreadDust();
            // 2. 먼지 순환
            topOfAirPurifier();
            bottomOfAirPurifier();
        }

        // 3. 먼지의 총 개수
        int ans = getCountOfDust();
        System.out.println(ans);
    }

    // 1. 미세먼지 확산
    static void spreadDust() {
        // (1)-1. 각 먼지당 분할(divide)해야할 먼지 저장
        saveDivide();

        // (1)-2. 먼지 확산
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] <= 0) continue;    // 공기 청정기나 먼지가 없을 때는 제외

                // New 좌표 (4방 탐색)
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    // valid check
                    if (!isIn(ny, nx) || map[ny][nx] == -1) continue;

                    // saveDivide()로부터 분할 가능한 먼지양만큼 더 하고, 기존의 먼지는 빼준다.
                    map[ny][nx] += divide[y][x];
                    map[y][x] -= divide[y][x];
                }
            }
        }
    }

    // (1)-1. 각 먼지당 분할(divide)해야할 먼지 저장
    // why? TestCase 1의 5행 6열을 보면, 10과 43이 붙어있다.
    // 동시에 확산 하는 것이기에 원본이 훼손 되기 전에 먼저 얼마만큼 분해(divide) 가능한지 저장해야한다.
    static void saveDivide() {
        divide = new int[N][M]; // 분할 가능한 먼지의 양 임시 저장소
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] < 5) continue;
                divide[y][x] = map[y][x] / 5;
            }
        }
    }

    // 2. 먼지 순환 (배열 돌리기)
    static void topOfAirPurifier() {
        // (2)-1. 위 (반시계 방향)
        int y = pos - 2;
        int x = 0;
        int d = 0;      // 상, 우, 하, 좌
        int ny, nx;

        // 공기 청정기를 만날 때까지 반복
        while (map[y][x] != -1) {
            // 새 좌표
            ny = y + dy[d];
            nx = x + dx[d];

            // 범위 유효성 검사 or 배열을 돌리는 경계선 검사
            if (!isIn(ny, nx) || ny >= pos) {
                d++;
                ny = y + dy[d];
                nx = x + dx[d];
            }

            // 다음 원소가 공기 청정기가 아닌 배열 돌리기의 원소라면
            if (map[ny][nx] != -1) {
                map[y][x] = map[ny][nx];
            }
            // 다음 원소가 공기 청정기라면
            else {
                map[y][x] = 0;
            }

            // 좌표갱신
            y = ny;
            x = nx;
        }
    }

    // 2. 먼지 순환 (배열 돌리기)
    static void bottomOfAirPurifier() {
        // (2)-2. 아래 (시계 방향)
        int y = pos + 1;
        int x = 0;
        int d = 2;      // "하"부터 시작
        int ny, nx;

        // 공기 청정기가 아닐 때까지 반복
        while (map[y][x] != -1) {
            ny = y + dy[d];
            nx = x + dx[d];

            // 유효성 검사 or 배열 돌리기 경계선 검사
            if (!isIn(ny, nx) || ny < pos) {
                d = (d + 3) % 4;
                ny = y + dy[d];
                nx = x + dx[d];
            }

            // 이동시킬 원소가 공기 청정기가 아니라면 원소 이동
            if (map[ny][nx] != -1) {
                map[y][x] = map[ny][nx];
            }
            // 이동시킬 원소가 공기 청정기라면, 0으로 갱신
            else {
                map[y][x] = 0;
            }

            // 좌표 갱신
            y = ny;
            x = nx;
        }
    }

    static int getCountOfDust(){
        int cnt = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(map[y][x] == -1) continue;
                cnt += map[y][x];
            }
        }
        return cnt;
    }

    static boolean isIn(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }
}