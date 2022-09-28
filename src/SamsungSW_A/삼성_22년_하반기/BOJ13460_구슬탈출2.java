package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13460_구슬탈출2 {

    private static final int MAX_MOVE_CNT = 10;
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static char[][] map;
    private static Bead red, blue, exit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        ans = Integer.MAX_VALUE;

        for(int y = 0; y < N; ++y){
            map[y] = br.readLine().toCharArray();
            for(int x = 0; x < M; ++x){
                if(map[y][x] == 'O') exit = new Bead(y, x);
                if(map[y][x] == 'R') red = new Bead(y, x);
                if(map[y][x] == 'B') blue = new Bead(y, x);
            }
        }

        red.isRed = true;
        blue.isRed = false;

        combination(MAX_MOVE_CNT, new int[MAX_MOVE_CNT]);

        if(ans == Integer.MAX_VALUE) ans = -1;
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected) {
        if(cnt == 0) {
            Bead originRed = new Bead(red.y, red.x);
            Bead originBlue = new Bead(blue.y, blue.x);
            boolean flag = false;
            int cntMove = 0;

            for(int d : selected) {
                cntMove++;
                move(d);

                if(blue.exit) break;
                flag = true;

                if(red.exit) break;
                flag = false;
            }

            red.y = originRed.y;
            red.x = originRed.x;
            red.exit = false;

            blue.y = originBlue.y;
            blue.x = originBlue.x;
            blue.exit = false;

            if(flag) ans = Math.min(ans, cntMove);
            return;
        }

        for(int d = 0; d < 4; ++d) {
            selected[selected.length - cnt] = d;
            combination(cnt - 1, selected);
        }
    }

    public static void move(int d) {
        if(d == 0){ // 위로 굴리기
            if(red.x == blue.x){ // 같은 열에 있을 때
                if(red.y < blue.y){ // 빨간 구슬이 위에 있을 때
                    red.move(d);
                    blue.move(d);
                } else {            // 빨간 구슬이 아래에 있을 때
                    blue.move(d);
                    red.move(d);
                }
            } else { // 같은 열이 아닐 때
                blue.move(d);
                red.move(d);
            }
        } else if(d == 1){ // 하
            if(red.x == blue.x){ // 같은 열에 있을 때
                if(red.y > blue.y){ // 빨간 구슬이 아래에 있을 때
                    red.move(d);
                    blue.move(d);
                } else {            // 빨간 구슬이 위에 있을 때
                    blue.move(d);
                    red.move(d);
                }
            } else { // 같은 열이 아닐 때
                blue.move(d);
                red.move(d);
            }
        } else if(d == 2) { // 좌
            if(red.y == blue.y) { // 같은 행에 있을 때
                if(red.x < blue.x) { // 빨간 구슬이 왼쪽에 있을 때
                    red.move(d);
                    blue.move(d);
                } else {
                    blue.move(d);
                    red.move(d);
                }
            } else { // 같은 행이 아닐 때
                blue.move(d);
                red.move(d);
            }
        } else { // 우
            if(red.y == blue.y) { // 같은 행에 있을 때
                if(red.x > blue.x) { // 빨간 구슬이 오른쪽에 있을 때
                    red.move(d);
                    blue.move(d);
                } else {
                    blue.move(d);
                    red.move(d);
                }
            } else { // 같은 행이 아닐 때
                blue.move(d);
                red.move(d);
            }
        }

    }

    public static boolean isWall(int y, int x) {
        return map[y][x] == '#';
    }

    public static boolean isExit(int y, int x) {
        return exit.y == y && exit.x == x;
    }

    public static class Bead {
        int y;
        int x;
        boolean isRed;
        boolean exit = false;

        public Bead(int y, int x){
            this.y = y;
            this.x = x;
        }

        public void move(int d) {
            int nowY = this.y;
            int nowX = this.x;
            boolean exitFlag = false;

            while(true){
                int nextY = nowY + dy[d];
                int nextX = nowX + dx[d];

                if(isWall(nextY, nextX)) break;
                if(this.isRed && nextY == blue.y && nextX == blue.x) break;
                if(!this.isRed && nextY == red.y && nextX == red.x) break;

                nowY = nextY;
                nowX = nextX;

                if(isExit(nowY, nowX)) {
                    exitFlag = true;
                    break;
                }
            }

            if(exitFlag) {
                this.y = -1;
                this.x = -1;
                this.exit = true;
                return;
            }

            this.y = nowY;
            this.x = nowX;
        }
    }
}
