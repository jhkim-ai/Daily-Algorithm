package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class _17_BOJ19236_청소년상어 {

    public static final int N = 4;
    public static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};

    public static int ans;
    public static int[][] map;
    public static Point shark;
    public static Point[] fishes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        fishes = new Point[4*4 + 1];
        ans = Integer.MIN_VALUE;

        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;
                map[y][x] = a;
                fishes[a] = new Point(a, y, x, b);
            }
        }

        Point firstFeed = fishes[map[0][0]];
        firstFeed.alive = false;
        shark = new Point(0, 0, 0, firstFeed.d);
        shark.feed += map[0][0];
        map[0][0] = 0;

        run();
//        run(map, fishes, shark);
        System.out.println(ans);
    }

    public static void run() {
//        int[][] tmpMap = getCopyMap(map);
//        Point[] tmpFishes = getCopyFishes(fishes);
//        Point tmpShark = getCopyShark(shark);
//
//        move(tmpMap, tmpFishes, tmpShark, tmpShark.feed);
        move(map, fishes, shark, shark.feed);
    }

    public static void move(int[][] map, Point[] fishes, Point shark, int sum) {
        for(int idx = 1; idx <= N*N; ++idx){
            if(!fishes[idx].alive) continue;
            Point fish = fishes[idx];

            for(int d = 0; d < 8; ++d){
                int nd = (fish.d + d) % 8;
                int ny = fish.y + dy[nd];
                int nx = fish.x + dx[nd];

                if(!isIn(ny, nx) || isShark(ny, nx, shark.y, shark.x)) continue;

                map[fish.y][fish.x] = 0;
                if(map[ny][nx] != 0){
                    Point changingFish = fishes[map[ny][nx]];
                    changingFish.y = fish.y;
                    changingFish.x = fish.x;
                    map[changingFish.y][changingFish.x] = changingFish.id;
                }

                fish.y = ny;
                fish.x = nx;
                fish.d = nd;
                map[ny][nx] = fish.id;
                break;
            }
        }
//        print("move");

        for(int s = 1; s <= 3; ++s){
            int[][] tmpMap = getCopyMap(map);
            Point[] tmpFishes = getCopyFishes(fishes);
            Point tmpShark = getCopyShark(shark);

            eat(tmpMap, tmpFishes, tmpShark, sum, s);
        }
    }

    public static void eat(int[][] map, Point[] fishes, Point shark, int sum, int s) {
        int ny = shark.y + dy[shark.d] * s;
        int nx = shark.x + dx[shark.d] * s;

        if(!isIn(ny, nx) || map[ny][nx] == 0) {
            ans = Math.max(sum, ans);
            return;
        }

        Point feed = fishes[map[ny][nx]];
        map[ny][nx] = 0;
        feed.alive = false;
        shark.d = feed.d;
        shark.y = ny;
        shark.x = nx;
//        print("eat");
        move(map, fishes, shark, sum + feed.id);
    }

    public static boolean isShark(int y, int x, int sharkY, int sharkX){
        return y == sharkY && x == sharkX;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static int[][] getCopyMap(int[][] map){
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }
        return copyMap;
    }

    public static Point[] getCopyFishes(Point[] fishes){
        return fishes.clone();
    }

    public static Point getCopyShark(Point shark){
        return new Point(shark.id, shark.y, shark.x, shark.d);
    }

    public static void print(String str, int[][] map, Point[] fishes){
        System.out.println(str + "===================");
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                System.out.print("("+map[y][x] +", " + fishes[map[y][x]].d + ")");
            }
        }
    }

    public static class Point {
        int id;
        int y;
        int x;
        int d;
        int feed = 0;
        boolean alive = true;

        public Point(int id, int y, int x, int d){
            this.id = id;
            this.y = y;
            this.x = x;
            this.d = d;
        }

        @Override
        public String toString(){
            return "{Y: " + y + ", x: " + x + "}";
        }
    }
}
