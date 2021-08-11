import java.io.*;
import java.util.*;

public class Main {

    private static final int[] dy = {0, 0, 0, -1, 1}; // 동, 서, 북, 남
    private static final int[] dx = {0, 1, -1, 0, 0};

    private static int N, M, K;
    private static int Y, X;
    private static int[] commands, dice;
    private static int[][] map;

    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        commands = new int[K];
        dice = new int[6]; // 0: 윗, 1: 아래, 2: 왼, 3: 오, 4: 앞, 5: 뒷
        sb = new StringBuilder();

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; ++k){
            commands[k] = Integer.parseInt(st.nextToken());
        }

        for(int command : commands){
            int ny = Y + dy[command];
            int nx = X + dx[command];
            if(!isIn(ny, nx)) {
                continue;
            }
            Y = ny;
            X = nx;
            move(command);
        }
        System.out.print(sb);
    }

    public static void move(int dir){
        switch(dir){
            case 1: // 동
                moveEast();
                break;
            case 2: // 서
                moveWest();
                break;
            case 3: // 북
                moveNorth();
                break;
            case 4: // 남
                moveSouth();
                break;
        }
    }

    public static void moveEast(){
        // 0: 윗, 1: 아래, 2: 왼, 3: 오, 4: 앞, 5: 뒷
        int[] tmp = new int[6];
        tmp[0] = dice[2];
        tmp[1] = dice[3];
        tmp[2] = dice[1];
        tmp[3] = dice[0];
        tmp[4] = dice[4];
        tmp[5] = dice[5];

        dice = tmp;
        copyBottomOfDice();
        sb.append(dice[0]).append("\n");
    }

    public static void moveWest(){
        // 0: 윗, 1: 아래, 2: 왼, 3: 오, 4: 앞, 5: 뒷
        int[] tmp = new int[6];
        tmp[0] = dice[3];
        tmp[1] = dice[2];
        tmp[2] = dice[0];
        tmp[3] = dice[1];
        tmp[4] = dice[4];
        tmp[5] = dice[5];

        dice = tmp;
        copyBottomOfDice();
        sb.append(dice[0]).append("\n");
    }

    public static void moveSouth(){
        // 0: 윗, 1: 아래, 2: 왼, 3: 오, 4: 앞, 5: 뒷
        int[] tmp = new int[6];
        tmp[0] = dice[5];
        tmp[1] = dice[4];
        tmp[2] = dice[2];
        tmp[3] = dice[3];
        tmp[4] = dice[0];
        tmp[5] = dice[1];

        dice = tmp;
        copyBottomOfDice();
        sb.append(dice[0]).append("\n");
    }

    public static void moveNorth(){
        // 0: 윗, 1: 아래, 2: 왼, 3: 오, 4: 앞, 5: 뒷
        int[] tmp = new int[6];
        tmp[0] = dice[4];
        tmp[1] = dice[5];
        tmp[2] = dice[2];
        tmp[3] = dice[3];
        tmp[4] = dice[1];
        tmp[5] = dice[0];

        dice = tmp;
        copyBottomOfDice();
        sb.append(dice[0]).append("\n");
    }

    public static void copyBottomOfDice(){
        if(map[Y][X] == 0){
            map[Y][X] = dice[1];
        } else {
            dice[1] = map[Y][X];
            map[Y][X] = 0;
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}