package SamsungSW_A.삼성_21년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ21610_마법사상어와비바라기 {

    private static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static int N, M;
    private static int D, S;
    private static int[][] map;
    private static Queue<Point> clouds;
    private static Set<Integer> beforeClouds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        clouds = new LinkedList<>();
        beforeClouds = new HashSet<>();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = N - 2; y < N; y++) {
            for (int x = 0; x < 2; x++) {
                clouds.add(new Point(y, x));
            }
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken()) - 1;
            S = Integer.parseInt(st.nextToken());
            move();
            rain(1);
            waterCopyMagic();
            createCloud();
        }
        System.out.println(getWater());
    }

    // Step 1. 구름 d 방향으로 s 만큼 이동
    public static void move() {
        int size = clouds.size();
        while (size-- > 0) {
            Point cloud = clouds.poll();
            cloud.y = (cloud.y + (dy[D] * S) % N) % N;
            cloud.x = (cloud.x + (dx[D] * S) % N) % N;
            if(cloud.y < 0){
                cloud.y += N;
            }
            if(cloud.x < 0){
                cloud.x += N;
            }
            clouds.offer(cloud);
        }
    }

    // Step 2. 비가 내려 물의 양 증가
    // Step 3. 구름이 모두 사라진다.
    public static void rain(int volume) {
        Iterator<Point> iterator = clouds.iterator();
        while (iterator.hasNext()) {
            Point cloud = iterator.next();
            map[cloud.y][cloud.x] += volume;
        }
    }

    // Step 4. (2)에서 물이 증가한 곳에서 마법을 시전
    public static void waterCopyMagic() {
        beforeClouds.clear();
        while (!clouds.isEmpty()) {
            Point cloud = clouds.poll();
            int y = cloud.y;
            int x = cloud.x;
            int count = 0;
            beforeClouds.add(y * N + x);

            for (int d = 1; d < 8; d += 2) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (!isIn(ny, nx) || map[ny][nx] == 0) {
                    continue;
                }
                ++count;
            }
            map[y][x] += count;
        }
    }

    // Step 5. 물의 양이 2이상인 모든 칸에 구름이 생기고, 물의 양 -2
    public static void createCloud(){
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(map[y][x] < 2 || beforeClouds.contains(y * N + x)){
                    continue;
                }
                map[y][x] -= 2;
                clouds.offer(new Point(y, x));
            }
        }
    }

    // Step 6. 남은 물의 양 구하기
    public static int getWater(){
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                sum += map[y][x];
            }
        }
        return sum;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print(){
        System.out.println("===================");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("===================");
    }

    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Point{" +
                "y=" + y +
                ", x=" + x +
                '}';
        }
    }
}
