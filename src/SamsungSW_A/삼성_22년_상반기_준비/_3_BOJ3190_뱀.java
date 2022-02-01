package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class _3_BOJ3190_뱀 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, K, L, nextIdx;
    private static int snakeY, snakeX, snakeLen;
    private static int[][] map;
    private static boolean[][] appleMap;
    private static Map<Integer, Character> command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        appleMap = new boolean[N][N];
        command = new HashMap<>();
        for(int i = 0; i < K; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            appleMap[y][x] = true; // 사과 위치
        }

        L = Integer.parseInt(br.readLine());
        for(int i = 0; i < L; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            command.put(time, dir);
        }

        map[0][0] = 1;
        snakeLen = 1;
        nextIdx = 2;
        run();
    }

    public static void run(){
        int d = 1; // 뱀은 처음에 오른쪽을 향한다.
        int time = 0;
        while(true){
            ++time;
            if(!move(d)) break; // 1. 이동(+게임 종료 여부)

            if(appleMap[snakeY][snakeX]) { // 2. 사과를 먹었나?
                snakeLen++;
                appleMap[snakeY][snakeX] = false;
            }

            if(!command.containsKey(time)) continue; // 3. 해당 시간에 방향이 바뀌나?
            char c =command.get(time);
            if(c == 'L') d = (d + 3) % 4;
            else d = (d + 1) % 4;
        }

        System.out.println(time);
    }

    public static boolean move(int d){
        int ny = snakeY + dy[d];
        int nx = snakeX + dx[d];

        // 범위 밖이거나 자기 몸에 닿으면 끝
        if(!isIn(ny, nx) || nextIdx - map[ny][nx] <= snakeLen) return false;

        map[ny][nx] = nextIdx; // 이동한 위치 기록
        nextIdx++;             // 다음 이동할 Idx

        snakeY = ny;           // 좌표 갱신
        snakeX = nx;

        return true;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
