package SamsungSW_A;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20058_마법사상어와파이어스톰 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, Q, mapSize;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        mapSize = (int)Math.pow(2, N);
        map = new int[mapSize][mapSize];

        for (int y = 0; y < mapSize; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < mapSize; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()){
            int L = Integer.parseInt(st.nextToken());
            int partialGridLen = (int)Math.pow(2, L);
            divide(0, 0, partialGridLen, mapSize);
            melt();
        }

        // 예외 상황 Check
        if(check()) {
            int cntIce = getIceCount(); // Step 4. 남은 얼음의 양 구하기
            int maxArea = Integer.MIN_VALUE; // Step 5. 가장 큰 얼음 덩어리 구하기
            visited = new boolean[mapSize][mapSize];

            for (int y = 0; y < mapSize; y++) {
                for (int x = 0; x < mapSize; x++) {
                    if (!visited[y][x] && map[y][x] != 0) {
                        visited[y][x] = true;
                        maxArea = Math.max(maxArea, getIceArea(y, x)); // dfs 탐색 후, 최댓값 비교
                    }
                }
            }

            System.out.println(cntIce);
            System.out.println(maxArea);
        } else {
            System.out.println(0);
            System.out.println(0);
        }
    }

    // Step 1. 격자 구하기 (분할 정복)
    public static void divide(int y, int x, int targetLen, int len){
        if(targetLen == len){
            // Step 2. 시계 방향으로 90도 회전
            clockwise(y, x, targetLen);
            return;
        }

        int half = len / 2;
        divide(y, x, targetLen, half);
        divide(y, x + half, targetLen, half);
        divide(y + half, x, targetLen, half);
        divide(y + half, x + half, targetLen, half);
    }

    // Step 2. 시계 방향으로 90 도 회전
    public static void clockwise(int startY, int startX, int len){
        List<Integer>[] tmpLists = new List[len]; // row 별로 임시 Data 저장
        for (int i = 0; i < len; i++) {
            tmpLists[i] = new ArrayList<>();
        }

        for (int y = startY, idx = 0; y < startY + len; y++, idx++) {
            for (int x = startX; x < startX + len; x++) {
                tmpLists[idx].add(map[y][x]);
            }
        }

        // 배열 값 갱신 (회전 완료)
        for (int x = startX, idx = len-1; x < startX + len; x++, idx--) {
            for (int y = startY, elemIdx = 0; y < startY + len; y++, elemIdx++) {
                map[y][x] = tmpLists[idx].get(elemIdx);
            }
        }
    }

    // Step 3. 얼음양 줄이기
    public static void melt(){
        // 얼음을 한 번에 녹이기 위해 "녹을 얼음의 위치" 저장소
        List<Integer> idxToMelt = new ArrayList<>();

        for (int y = 0; y < mapSize; y++) {
            xLoop:
            for (int x = 0; x < mapSize; x++) {
                if(map[y][x] <= 0){ // 얼음이 없다면 pass
                    continue;
                }
                int count = 4;
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(!isIn(ny , nx) || map[ny][nx] == 0){ // 얼음과 인접하지 않을 경우 count 감소
                        count--;
                    }
                    if(count < 3){ // 만약 인접한 얼음의 양이 2개 이하이면, 이 칸은 녹아야 한다.
                        idxToMelt.add(y * mapSize + x);
                        continue xLoop; // 다음 탐색
                    }
                }
            }
        }

        // 얼음을 한 번에 녹이기
        for(int idx : idxToMelt){
            int y = idx / mapSize;
            int x = idx % mapSize;
            map[y][x]--;
        }
    }

    // Step 4. 남은 얼음의 양 구하기 (total)
    public static int getIceCount(){
        int sum = 0;
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
               sum += map[y][x];
            }
        }
        return sum;
    }

    // Step 5. 연결된 가장 큰 얼음 덩어리 구하기(dfs)
    public static int getIceArea(int y, int x){
        int cnt = 1;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            // (범위 밖 + 이미 방문 + 얼음이 없는 곳) 일 경우 pass
            if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0){
                continue;
            }
            visited[ny][nx] = true;
            cnt += getIceArea(ny, nx);
        }

        return cnt;
    }

    // Step 6. [예외 상황] 얼음 덩어리가 없는 경우 0 출력
    public static boolean check(){

        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                if(map[y][x] > 0){
                    return true;
                }
            }
        }

        return false;
    }

    // 배열 범위 검사
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < mapSize && x < mapSize;
    }

    public static void print(){
        System.out.println("================");
        for (int y = 0; y < mapSize; y++) {
            for (int x = 0; x < mapSize; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
