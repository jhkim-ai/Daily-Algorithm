package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23288_주사위굴리기2 {

        private static final int[] dy = {0, 1, 0, -1};
        private static final int[] dx = {1, 0, -1, 0};

        private static int N, M, K, dir, ans;
        private static int[][] map;

        private static int[] diceLoc;
        private static int[] dice;

        public static void main(String[] args) throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            dice = new int[]{1, 6, 4, 3, 5, 2};
            diceLoc = new int[]{0, 0};

            for(int y = 0; y < N; ++y) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int x = 0; x < M; ++x) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            dir = 0;

            while(K-- > 0) {
                move();
                ans += getScore();
                setDir();
            }

            System.out.println(ans);
        }

        public static void setDir() {
            int bottomNum = dice[1];
            int mapNum = map[diceLoc[0]][diceLoc[1]];

            if(bottomNum > mapNum) dir = (dir + 1) % 4;
            else if(bottomNum < mapNum) dir = (dir + 3) % 4 ;
        }

        public static int getScore() {
            int cnt = 1;
            int score = 0;
            int nowY = diceLoc[0];
            int nowX = diceLoc[1];
            int num = map[nowY][nowX];

            Queue<int[]> q = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];

            visited[nowY][nowX] = true;
            q.offer(new int[]{nowY, nowX});

            while(!q.isEmpty()) {
                int[] now = q.poll();
                for(int d = 0; d < 4; ++d) {
                    int ny = now[0] + dy[d];
                    int nx = now[1] + dx[d];

                    if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] != num) continue;

                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                    ++cnt;
                }
            }

            score += num * cnt;
            return score;
        }

        public static void move() {
            int diceY = diceLoc[0];
            int diceX = diceLoc[1];

            int ny = 0, nx = 0;
            for(int d = 0; d < 3; d += 2) {
                dir = (dir + d) % 4;
                ny = diceY + dy[dir];
                nx = diceX + dx[dir];

                if(!isIn(ny, nx)) continue;
                break;
            }

            diceLoc[0] = ny;
            diceLoc[1] = nx;

            switch(dir) {
                case 0:
                    moveEast();
                    break;
                case 1:
                    moveSouth();
                    break;
                case 2:
                    moveWest();
                    break;
                case 3:
                    moveNorth();
                    break;
            }
        }

        public static void moveEast() {
            int tmp = dice[0];
            dice[0] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[3];
            dice[3] = tmp;
        }

        public static void moveSouth() {
            int tmp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[4];
            dice[4] = tmp;
        }

        public static void moveWest() {
            int tmp = dice[0];
            dice[0] = dice[3];
            dice[3] = dice[1];
            dice[1] = dice[2];
            dice[2] = tmp;
        }

        public static void moveNorth() {
            int tmp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = tmp;
        }

        public static boolean isIn(int y, int x) {
            return y >= 0 && x >= 0 && y < N && x < M;
        }
}
