package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14499_주사위굴리기 {

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {1, -1, 0, 0};

    private static int N, M, K;
    private static int[][] map;
    private static Dice dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int diceY = Integer.parseInt(st.nextToken());
        int diceX = Integer.parseInt(st.nextToken());
        dice = new Dice(diceY, diceX);

        K = Integer.parseInt(st.nextToken());

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while(K-- > 0) {
            int d = Integer.parseInt(st.nextToken()) - 1;
            int ny = dice.y + dy[d];
            int nx = dice.x + dx[d];

            if(!isIn(ny, nx)) continue;

            dice.move(d);
            dice.y = ny;
            dice.x = nx;

            if(map[dice.y][dice.x] == 0) {
                map[dice.y][dice.x] = dice.info[1];
            } else {
                dice.info[1] = map[dice.y][dice.x];
                map[dice.y][dice.x] = 0;
            }
            sb.append(dice.info[0]).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static class Dice {
        int y;
        int x;
        int[] info;

        public Dice(int y, int x) {
            this.y = y;
            this.x = x;
            this.info = new int[6]; // 윗, 아래, 오른, 왼, 앞, 뒤
        }

        public void moveRight() {
            int tmp = this.info[0];
            this.info[0] = this.info[3];
            this.info[3] = this.info[1];
            this.info[1] = this.info[2];
            this.info[2] = tmp;
        }

        public void moveLeft() {
            int tmp = this.info[0];
            this.info[0] = this.info[2];
            this.info[2] = this.info[1];
            this.info[1] = this.info[3];
            this.info[3] = tmp;
        }

        public void moveUp() {
            int tmp = this.info[0];
            this.info[0] = this.info[4];
            this.info[4] = this.info[1];
            this.info[1] = this.info[5];
            this.info[5] = tmp;
        }

        public void moveDown() {
            int tmp = this.info[0];
            this.info[0] = this.info[5];
            this.info[5] = this.info[1];
            this.info[1] = this.info[4];
            this.info[4] = tmp;
        }

        public void move(int d) {
            switch (d) {
                case 0:
                    moveRight();
                    break;
                case 1:
                    moveLeft();
                    break;
                case 2:
                    moveUp();
                    break;
                case 3:
                    moveDown();
                    break;
            }
        }
    }
}
