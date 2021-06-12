package SamsungSW_A;

import java.io.*;
import java.util.*;

public class BOJ20057_마법사상어와토네이도 {

    public static final int[] dy = {0, 1, 0, -1, -1, -1, 1, 1};
    public static final int[] dx = {-1, 0, 1, 0, -1, 1, 1, -1};

    public static int N, len, dir, idx;
    public static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        len = 1;
        idx = 1;
        dir = 0;

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int ny = N / 2;
        int nx = N / 2;

        map[ny][nx] = idx;
        while (true) {
            for (int i = 0; i < len; i++) {
                if(len==N && i == len-1) break;
                ++idx;
                ny = ny + dy[dir];
                nx = nx + dx[dir];
                map[ny][nx] = idx;
            }

            if (len == N) break;
            dir = (dir + 1) % 4;

            for (int i = 0; i < len; i++) {
                ++idx;
                ny = ny + dy[dir];
                nx = nx + dx[dir];
                map[ny][nx] = idx;
            }

            len++;
            dir = (dir + 1) % 4;
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static void tornado(int dir, int y, int x){
        switch(dir){
            case 0:
                for (int i = 0; i < 8; i++) {
                }
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }


        for (int d = 0; d < 8; d++) {
            if(d < 4 && d == (dir + 2) % 4) continue; // 반대 방향으로는 안감

            int ny, nx;
            // (상,하,좌,우)는 2배씩
            if(d < 4){
                for (int s = 1; s <= 2; s++) {
                    ny = y + dy[d] * s;
                    nx = x + dx[d] * s;
                }
                continue;
            }
            ny = y + dy[d];
            nx = x + dx[d];
        }
    }
}
