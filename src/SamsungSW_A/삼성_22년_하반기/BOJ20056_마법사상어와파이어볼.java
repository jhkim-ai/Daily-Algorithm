package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {

    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    private static int N, M, K;
    private static List<Ball>[][] map;
    private static List<Ball>[][] tmpMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new List[N][N];
        tmpMap = new List[N][N];

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x) {
                map[y][x] = new ArrayList<>();
                tmpMap[y][x] = new ArrayList<>();
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[y][x].add(new Ball(y, x, m, s, d));
        }

        while(K-- > 0) {
            move();
            sum();
        }
//        print(map);
        int ans = getMass();
        System.out.println(ans);
    }

    public static void sum() {
        for(int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                int size = tmpMap[y][x].size();

                if(size == 0) continue;
                if(size == 1) {
                    map[y][x].add(tmpMap[y][x].get(0));
                    tmpMap[y][x].clear();
                    continue;
                }

                int sumM = 0;
                int sumS = 0;
                int oddD = 0;
                int evenD = 0;
                int newD = 1;

                for(Ball ball : tmpMap[y][x]){
                    sumM += ball.m;
                    sumS += ball.s;

                    if(ball.d % 2 == 0) evenD++;
                    else oddD++;
                }

                if(sumM / 5 != 0) {
                    if (oddD == size || evenD == size) newD = 0;
                    for (int d = newD; d <= 7; d += 2) {
                        map[y][x].add(new Ball(y, x, (sumM / 5), (sumS / size), d));
                    }
                }

                tmpMap[y][x].clear();
            }
        }
    }

    public static void move() {
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(map[y][x].size() == 0) continue;

                for(Ball ball : map[y][x]) {
                    int ny = ((ball.y + dy[ball.d] * ball.s) % N + N) % N;
                    int nx = ((ball.x + dx[ball.d] * ball.s) % N + N) % N;

                    tmpMap[ny][nx].add(new Ball(ny, nx, ball.m, ball.s, ball.d));
                }

                map[y][x].clear();
            }
        }
    }

    public static int getMass() {
        int sum = 0;
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(map[y][x].size() == 0) continue;

                for(Ball ball : map[y][x]) {
                    sum += ball.m;
                }
            }
        }

        return sum;
    }

    public static void print(List[][] map) {
        System.out.println("===================");
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                System.out.print(map[y][x].size() + " ");
            }
            System.out.println();
        }
        System.out.println("===================");
    }

    public static class Ball {
        int y;
        int x;
        int m;
        int s;
        int d;

        public Ball(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}



