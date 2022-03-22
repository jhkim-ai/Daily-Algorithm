package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1_BOJ13460_구슬탈출2 {

    public static final int[] dy = {-1, 0, 1, 0};
    public static final int[] dx = {0, 1, 0, -1};

    private static int N, M, ans;
    private static int exitY, exitX;
    private static int startRedY, startRedX;
    private static int startBlueY, startBlueX;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int y = 0; y < N; ++y) {
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < M; ++x) {
                if (map[y][x] == 'O') {
                    exitY = y;
                    exitX = x;
                }

                if (map[y][x] == 'R') {
                    startRedY = y;
                    startRedX = x;
                }

                if (map[y][x] == 'B') {
                    startBlueY = y;
                    startBlueX = x;
                }
            }
        }

        dfs(0, startRedY, startRedX, startBlueY, startBlueX);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int redY, int redX, int blueY, int blueX) {
        if (cnt > 10) {
            ans = -1;
            return;
        }

        if(isIn(blueY, blueX)) return;

        if (isIn(redY, redX)) {
            ans = cnt;
            return;
        }

        for (int d = 0; d < 4; ++d) {
            int ny = redY + dy[d];
            int nx = redX + dx[d];

            if (map[ny][nx] == '#') {
                continue;
            }

            int originRedY = redY;
            int originRedX = redX;
            int originBlueY = blueY;
            int originBlueX = blueX;
            int order = setOrder(d, redY, redX, blueY, blueX);
            move(d, order, redY, redX, blueY, blueX);
            print(d);
            dfs(cnt + 1, redY, redX, blueY, blueX);
            map[redY][redX] = '.';
            map[blueY][blueX] = '.';
            map[exitY][exitX] = 'O';
            redY = originRedY;
            redX = originRedX;
            blueY = originBlueY;
            blueX = originBlueX;
            map[redY][redX] = 'R';
            map[blueY][blueX] = 'B';
            print(d);
        }
    }

    public static int setOrder(int d, int redY, int redX, int blueY, int blueX) {
        int order = 0; // order == 0 : 빨강 먼저, order == 1 : 파랑 먼저

        if ((d == 0 || d == 2) && redX == blueX) {
            if (d == 0) { // 상
                if (redY > blueY) {
                    order = 1;
                } else if (redY < blueY) {
                    order = 0;
                }
            } else if (d == 2) {
                if (redY > blueY) {
                    order = 0;
                } else if (redY < blueY) {
                    order = 1;
                }
            }
        } else if ((d == 1 || d == 3) && redY == blueY) {
            if (d == 1) { // 좌
                if (redX > blueX) {
                    order = 1;
                } else if (redX < blueX) {
                    order = 0;
                }
            } else if (d == 3) { // 우
                if (redX > blueX) {
                    order = 0;
                } else if (redX < blueX) {
                    order = 1;
                }
            }
        }

        return order;
    }

    public static void move(int d, int order, int redY, int redX, int blueY, int blueX) {
        Queue<Character> q = new LinkedList<>();
        if (order == 0) { // order == 0 : 빨강 먼저
            q.offer('R');
            q.offer('B');
        } else {          // order == 1 : 파랑 먼저
            q.offer('B');
            q.offer('R');
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            if (c == 'R') {
                int ny;
                int nx;
                while (true) {
                    ny = redY + dy[d];
                    nx = redX + dx[d];

                    if (map[ny][nx] == '#' || map[ny][nx] == 'B') {
                        break;
                    }

                    if(map[ny][nx] == 'O'){
                        break;
                    }

                    redY = ny;
                    redX = nx;
                }
            } else if (c == 'B') {
                while (true) {
                    int ny = blueY + dy[d];
                    int nx = blueX + dx[d];

                    if (map[ny][nx] == '#' || map[ny][nx] == 'R') {
                        break;
                    }
                    if(map[ny][nx] == 'O'){
                        break;
                    }

                    blueY = ny;
                    blueX = nx;
                }
            }
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
    }

    public static void print(int d) {
        System.out.println(d + "=========================");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("=========================");
    }

    public static boolean isIn(int y, int x){
        return y == exitY && x == exitX;
    }
}
