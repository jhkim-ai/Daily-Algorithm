package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _18_BOJ20056_마법사상어와파이어볼 {

    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    private static int N, M, K;
    private static Queue<Ball>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Queue[N][N];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                map[y][x] = new LinkedList<>();
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Ball ball = new Ball(y, x, m, s, d);

            map[y][x].offer(ball);
        }

        run();
        System.out.println(getSumWeight());
    }

    public static void run() {
        while(K-- > 0) {
            move();
            mixBall();
        }
    }

    public static void move() {
        Queue<Ball> tmpQ = new LinkedList<>();
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(map[y][x].isEmpty()) continue;

                while(!map[y][x].isEmpty()){
                    Ball ball = map[y][x].poll();
                    int ny = (ball.y + (dy[ball.d]) * ball.s) % N;
                    int nx = (ball.x + (dx[ball.d]) * ball.s) % N;
                    if(ny < 0) ny = (ny + N) % N;
                    if(nx < 0) nx = (nx + N) % N;
                    ball.y = ny;
                    ball.x = nx;
                    tmpQ.offer(ball);
                }
            }
        }

        while(!tmpQ.isEmpty()){
            Ball ball = tmpQ.poll();
            map[ball.y][ball.x].offer(ball);
        }
    }

    public static void mixBall() {
        for(int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if (map[y][x].size() < 2) continue;

                int size = map[y][x].size();
                int sumWeight = 0;
                int sumVelocity = 0;
                int oddCnt = 0;
                int evenCnt = 0;

                while(!map[y][x].isEmpty()){
                    Ball ball = map[y][x].poll();
                    sumWeight += ball.m;
                    sumVelocity += ball.s;
                    if(ball.d % 2 == 0) evenCnt++;
                    else oddCnt++;
                }

                sumWeight /= 5;
                if(sumWeight == 0) continue;
                sumVelocity /= size;

                int d = 1;
                if(oddCnt == size || evenCnt == size) d = 0;

                for(; d < 8; d += 2){
                    map[y][x].offer(new Ball(y, x, sumWeight, sumVelocity, d));
                }
            }
        }
    }

    public static int getSumWeight() {
        int sumWeight = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                while(!map[y][x].isEmpty()){
                    Ball ball = map[y][x].poll();
                    sumWeight += ball.m;
                }
            }
        }

        return sumWeight;
    }

    public static class Ball {

        int y;
        int x;
        int m;
        int d;
        int s;

        public Ball(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.d = d;
            this.s = s;
        }

        @Override
        public String toString(){
            return "{y: " + y + ", x: " + x + "}";
        }
    }
}
