package StepByStep.day211002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10703_유성 {

    private static int N, M;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        int[] locComet = new int[M];
        Arrays.fill(locComet, -1);

        // 입력
        for (int y = 0; y < N; y++) {
            String tmp = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = tmp.charAt(x);
                if(map[y][x] == 'X'){
                    locComet[x] = y;
                }
            }
        }

        int minDiff = getMinDiff();  // 땅과 혜성 사이의 최소차 구하기
        moveDown(minDiff, locComet); // 아래로 이동

        // 출력
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void moveDown(int minDiff, int[] locComet){
        for (int x = 0; x < M; x++) {
            if(locComet[x] == -1){
                continue;
            }
            for (int y = locComet[x]; y >= 0; y--) {
                map[y+minDiff][x] = map[y][x];
                map[y][x] = '.';
            }
        }
    }

    public static int getMinDiff(){
        int minDiff = Integer.MAX_VALUE;
        for (int x = 0; x < M; x++) {
            int locOfComet = -1;
            int land = N-1;
            for (int y = 0; y < N; y++) {
                if (map[y][x] == 'X') {
                    locOfComet = y;
                } else if (map[y][x] == '#') {
                    land = y;
                    break;
                }
            }

            if(locOfComet != -1){
                minDiff = Math.min(land - locOfComet -1, minDiff);
            }
        }

        return minDiff;
    }
}
