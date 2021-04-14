package Category.Implementation;

import java.io.*;
import java.util.*;

public class BOJ17144_미세먼지안녕_교수 {
    static int R, C, T, pos, map[][], divide[][];
    static int[] dr = { -1, 0, 1, 0 }, dc = { 0, 1, 0, -1 }; // 상, 우, 하, 좌

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(in.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != -1)
                    continue;
                // 공기청정기가 있는 위치(두칸 중 아래칸 저장)
                pos = i;
            }
        }

        while (T-- > 0) {
            spreadDust();
            runCleaner();
        }

        System.out.println(getAmount());

    }

    // 남은 미세먼지 양 세기
    private static int getAmount() {
        int cnt = 2; // 공기청정기의 -1도 더할 예정
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                cnt += map[i][j];
            }
        }
        return cnt;
    }

    // 공기청저기 작동하기
    private static void runCleaner() {
        // 위쪽
        int r = pos - 2, c =0 , d = 0, nr, nc;

        while (map[r][c] != -1) {
            nr = r + dr[d]; // 상, 우, 하, 좌
            nc = c + dc[d];
            if(!isIn(nr,nc) || nr >= pos) {
                d++;
                nr = r + dr[d];
                nc = c + dc[d];
            }

            if(map[nr][nc] != -1) {
                map[r][c] =  map[nr][nc];
            }else {
                map[r][c] = 0;
            }

            r = nr;
            c = nc;
        }

        // 아래쪽
        r = pos + 1;
        c = 0;
        d = 2; // 아래로내려가기
        while (map[r][c] != -1) {
            nr = r + dr[d];
            nc = c + dc[d];
            if(!isIn(nr,nc) || nr < pos) {
                d = (d+3)%4;
                nr = r + dr[d];
                nc = c + dc[d];
            }

            if(map[nr][nc] != -1) {
                map[r][c] =  map[nr][nc];
            }else {
                map[r][c] = 0;
            }

            r = nr;
            c = nc;
        }
    }

    private static void spreadDust() {
        // divide에 나눈값 미리 저장하기
        save();

        // divide가 0보다 큰 값일 때 주변에 나눠주기
        int nr, nc;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (divide[r][c] <= 0)
                    continue;

                for (int d = 0; d < 4; d++) {
                    nr = r + dr[d];
                    nc = c + dc[d];

                    if (!isIn(nr, nc) || map[nr][nc] == -1)
                        continue;

                    map[nr][nc] += divide[r][c];
                    map[r][c] -= divide[r][c];
                }
            }
        }

        // 주변에 나눠주면서 나눠준 값만큼 미세먼지 빼기

    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r < R && c >= 0 & c < C;
    }

    // 나눠줘야 할 미세먼지 양 저장하기
    private static void save() {
        divide = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] < 5) // 5미만은 나눠줄 양 0이므로 pass
                    continue;

                divide[i][j] = map[i][j] / 5;
            }
        }
    }
}
