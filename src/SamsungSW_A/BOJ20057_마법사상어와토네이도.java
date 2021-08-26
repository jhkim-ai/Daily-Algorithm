package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057_마법사상어와토네이도 {

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {-1, 0, 1, 0};

    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ans = 0;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        run(N / 2, N / 2);
        System.out.println(ans);
    }
    static int idx = 0;
    public static void run(int startY, int startX) {
        int d = 0;
        int len = 1;
        int count = 0;
        int y = startY;
        int x = startX;

        while (true) {
            int ny = 0;
            int nx = 0;
            for (int n = 0; n < len; ++n) {
                // (0, 0) 도착 시, 종료
                if (y == 0 && x == 0) {
                    return;
                }
                ny = y + dy[d];
                nx = x + dx[d];
                switch (d) {
                    case 0: // 좌
                        moveLeft(ny, nx);
                        break;
                    case 1: // 하
                        moveDown(ny, nx);
                        break;
                    case 2: // 우
                        moveRight(ny, nx);
                        break;
                    case 3: // 상
                        moveUp(ny, nx);
                        break;
                }
                map[ny][nx] = 0;
                y = ny;
                x = nx;
            }
            d = (d + 1) % 4;
            ++count;
            if(count == 2){
                ++len;
                count = 0;
            }
        }
    }

    public static void moveLeft(int ny, int nx){
        int sum = 0;
        int sand = map[ny][nx];

        int move = (int)(sand * 0.07);
        if(!isIn(ny-1, nx)){
            ans += move;
        } else {
            map[ny-1][nx] += move;
        }
        if(!isIn(ny+1, nx)){
            ans += move;
        } else {
            map[ny + 1][nx] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.02);
        if(!isIn(ny-2, nx)){
            ans += move;
        } else {
            map[ny - 2][nx] += move;
        }
        if(!isIn(ny+2, nx)){
            ans += move;
        } else {
            map[ny + 2][nx] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.01);
        if(!isIn(ny-1, nx+1)){
            ans += move;
        } else {
            map[ny - 1][nx + 1] += move;
        }
        if(!isIn(ny+1, nx+1)){
            ans += move;
        } else {
            map[ny + 1][nx + 1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.1);
        if(!isIn(ny-1, nx-1)){
            ans += move;
        } else {
            map[ny-1][nx-1] += move;
        }
        if(!isIn(ny+1, nx-1)){
            ans += move;
        } else {
            map[ny + 1][nx - 1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.05);
        if(!isIn(ny, nx-2)){
            ans += move;
        } else {
            map[ny][nx - 2] += move;
        }
        sum += move;

        int rest = map[ny][nx] - sum;;
        if(!isIn(ny, nx-1)){
            ans += rest;
        } else {
            map[ny][nx - 1] += rest;
        }
    }

    public static void moveDown(int ny, int nx){
        int sum = 0;
        int sand = map[ny][nx];

        int move = (int)(sand * 0.07);
        if(!isIn(ny, nx-1)){
            ans += move;
        } else {
            map[ny][nx-1] += move;
        }
        if(!isIn(ny, nx+1)){
            ans += move;
        } else {
            map[ny][nx+1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.02);
        if(!isIn(ny, nx-2)){
            ans += move;
        } else {
            map[ny][nx-2] += move;
        }
        if(!isIn(ny, nx+2)){
            ans += move;
        } else {
            map[ny][nx+2] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.01);
        if(!isIn(ny-1, nx+1)){
            ans += move;
        } else {
            map[ny - 1][nx + 1] += move;
        }
        if(!isIn(ny-1, nx-1)){
            ans += move;
        } else {
            map[ny-1][nx-1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.1);
        if(!isIn(ny+1, nx-1)){
            ans += move;
        } else {
            map[ny+1][nx-1] += move;
        }
        if(!isIn(ny+1, nx+1)){
            ans += move;
        } else {
            map[ny + 1][nx + 1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.05);
        if(!isIn(ny+2, nx)){
            ans += move;
        } else {
            map[ny+2][nx] += move;
        }
        sum += move;

        int rest = map[ny][nx] - sum;;
        if(!isIn(ny+1, nx)){
            ans += rest;
        } else {
            map[ny+1][nx] += rest;
        }
    }

    public static void moveRight(int ny, int nx){
        int sum = 0;
        int sand = map[ny][nx];

        int move = (int)(sand * 0.07);
        if(!isIn(ny-1, nx)){
            ans += move;
        } else {
            map[ny-1][nx] += move;
        }
        if(!isIn(ny+1, nx)){
            ans += move;
        } else {
            map[ny + 1][nx] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.02);
        if(!isIn(ny-2, nx)){
            ans += move;
        } else {
            map[ny - 2][nx] += move;
        }
        if(!isIn(ny+2, nx)){
            ans += move;
        } else {
            map[ny + 2][nx] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.01);
        if(!isIn(ny-1, nx-1)){
            ans += move;
        } else {
            map[ny - 1][nx - 1] += move;
        }
        if(!isIn(ny+1, nx-1)){
            ans += move;
        } else {
            map[ny + 1][nx - 1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.1);
        if(!isIn(ny-1, nx+1)){
            ans += move;
        } else {
            map[ny-1][nx+1] += move;
        }
        if(!isIn(ny+1, nx+1)){
            ans += move;
        } else {
            map[ny + 1][nx + 1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.05);
        if(!isIn(ny, nx+2)){
            ans += move;
        } else {
            map[ny][nx + 2] += move;
        }
        sum += move;

        int rest = map[ny][nx] - sum;;
        if(!isIn(ny, nx+1)){
            ans += rest;
        } else {
            map[ny][nx + 1] += rest;
        }
    }

    public static void moveUp(int ny, int nx){
        int sum = 0;
        int sand = map[ny][nx];

        int move = (int)(sand * 0.07);
        if(!isIn(ny, nx-1)){
            ans += move;
        } else {
            map[ny][nx-1] += move;
        }
        if(!isIn(ny, nx+1)){
            ans += move;
        } else {
            map[ny][nx+1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.02);
        if(!isIn(ny, nx-2)){
            ans += move;
        } else {
            map[ny][nx-2] += move;
        }
        if(!isIn(ny, nx+2)){
            ans += move;
        } else {
            map[ny][nx+2] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.01);
        if(!isIn(ny+1, nx+1)){
            ans += move;
        } else {
            map[ny + 1][nx + 1] += move;
        }
        if(!isIn(ny+1, nx-1)){
            ans += move;
        } else {
            map[ny+1][nx-1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.1);
        if(!isIn(ny-1, nx-1)){
            ans += move;
        } else {
            map[ny-1][nx-1] += move;
        }
        if(!isIn(ny-1, nx+1)){
            ans += move;
        } else {
            map[ny - 1][nx + 1] += move;
        }
        sum += move * 2;

        move = (int)(sand * 0.05);
        if(!isIn(ny-2, nx)){
            ans += move;
        } else {
            map[ny-2][nx] += move;
        }
        sum += move;

        int rest = map[ny][nx] - sum;;
        if(!isIn(ny-1, nx)){
            ans += rest;
        } else {
            map[ny-1][nx] += rest;
        }
    }



    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print() {
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

}
