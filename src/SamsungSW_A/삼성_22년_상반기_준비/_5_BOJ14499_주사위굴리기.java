package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _5_BOJ14499_주사위굴리기 {

    private static final int[] dy = {0, 0, 0, -1, 1};
    private static final int[] dx = {0, 1, -1, 0, 0};

    private static int N, M, K;
    private static int Y, X;
    private static int[] dice; // 상, 하, 좌, 우, 앞, 뒤
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dice = new int[6];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while(K-- > 0){
            int dir = Integer.parseInt(st.nextToken());
            if(!run(dir)) continue;
            sb.append(dice[0]).append("\n");
        }

        System.out.println(sb);

    }

    public static boolean run(int dir){
        int ny = Y + dy[dir];
        int nx = X + dx[dir];

        if(!isIn(ny, nx)) return false;

        int tmp = dice[1];
        switch(dir){
            case 1: // 동
                dice[1] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmp;
                break;
            case 2: // 서
                dice[1] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = tmp;
                break;
            case 3: // 북
                dice[1] = dice[5];
                dice[5] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmp;
                break;
            case 4: // 남
                dice[1] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[5];
                dice[5] = tmp;
                break;
        }

        Y = ny;
        X = nx;

        if(map[Y][X] == 0){
            map[Y][X] = dice[1];
        } else {
            dice[1] = map[Y][X];
            map[Y][X] = 0;
        }

        return true;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
