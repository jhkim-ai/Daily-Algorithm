package SamsungSW_A.삼성_21년_하반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class BOJ19236_청소년상어 {

    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException, CloneNotSupportedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int score = 0;
        int[][] originMap = new int[4][4];
        Point[] originFishes = new Point[17]; // 물고기 생존 여부
        Map<Integer, Integer> originMapSharkLocationInfo = new HashMap<>(); // 물고기 위치 정보

        for (int y = 0; y < 4; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < 4; x++) {
                int idx = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;
                originMap[y][x] = idx;
                originFishes[idx] = new Point(y, x, dir, idx);  // idx 번의 물고기 정보
                originMapSharkLocationInfo.put(idx, y * 4 + x); // idx 번의 물고기 위치
            }
        }
        // Step 1. 상어가 (0,0)으로 들어간다.
        Point firstPrey = originFishes[originMap[0][0]]; // (0,0) 위치의 n번 물고기 정보
        Point originShark = new Point(0, 0, firstPrey.d, 0); // n번 물고기의 방향 획득
        score += firstPrey.idx; // n번 물고기의 번호 합
        originFishes[originMap[0][0]] = null; // n번 물고기 죽음
        originMapSharkLocationInfo.remove(firstPrey.idx); // n번 물고기 정보 제거
        originMap[0][0] = -1; // 상어 idx = -1

        run(originShark, originFishes, originMap, originMapSharkLocationInfo);
    }

    public static void run(Point originShark, Point[] originFishes, int[][] originMap,
        Map<Integer, Integer> originMapSharkLocationInfo) throws CloneNotSupportedException {

        Point tmpShark = originShark.clone();
        Point[] tmpFishes = originFishes.clone();
        int[][] tmpMap = new int[4][];
        for (int y = 0; y < 4; y++) {
            tmpMap[y] = originMap[y].clone();
        }
        Map<Integer, Integer> tmpMapSharkLocationInfo = new HashMap<>();
        for(Entry<Integer, Integer> elem : originMapSharkLocationInfo.entrySet()){
            tmpMapSharkLocationInfo.put(elem.getKey(), elem.getValue());
        }

        // Step 2. 물고기 이동
        // moveFish(fishes, map);

        /*
        // Step 3. 상어 이동
        List<int[]> nextLocations = new ArrayList<>();
        for(int len = 1; len < 4; ++len){
            int ny = shark.y + dy[shark.d] * len;
            int nx = shark.x + dx[shark.d] * len;
            if(!isIn(ny, nx)) {
                break;
            }
            if(map[ny][nx] == 0){
                continue;
            }
            nextLocations.add(new int[]{ny, nx});
        }
        for(int[] nextLoc : nextLocations){
            moveShark();
        }
        */
    }

    public static void moveFish(Point[] fishes, int[][] map) {
        for (int idx = 1; idx <= 16; idx++) {
            if (fishes[idx] == null) { // 상어에게 먹힌 물고기 번호는 Pass
                continue;
            }
            Point fish = fishes[idx];
            for (int d = 0; d < 8; d++) {
                int nd = (fish.d + d) % 8;
                int ny = fish.y + dy[nd];
                int nx = fish.x + dx[nd];

                // 범위 밖 or 상어 위치인 경우 pass
                if(!isIn(ny, nx) || map[ny][nx] == -1){
                    continue;
                }

                int nextFishIdx = map[ny][nx]; // 자리를 교환할 물고기 번호
                Point nextFish = fishes[nextFishIdx]; // 자리를 교환활 물고기 정보

                map[fish.y][fish.x] = nextFishIdx;
                map[ny][nx] = idx;

                nextFish.y = fish.y;
                nextFish.x = fish.x;

                fish.d = nd;
                fish.y = ny;
                fish.x = nx;

                break;
            }
        }
    }

    public static void moveShark(){

    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < 4 && x < 4;
    }

    public static void print(int[][] map){
        System.out.println("===============");
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static class Point {

        int y;
        int x;
        int d;
        int idx;

        public Point(int y, int x, int d, int idx) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.idx = idx;
        }

        @Override
        protected Point clone() throws CloneNotSupportedException {
            return (Point)super.clone();
        }
    }
}
