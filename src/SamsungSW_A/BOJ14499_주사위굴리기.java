package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ14499_주사위굴리기 {

    public static final int[] dy = {0, 0, 0, -1, 1};
    public static final int[] dx = {0, 1, -1, 0, 0};

    public static int N, M, K;
    public static int[] command;
    public static int[][] map;
    public static Dice dice;

    static class Dice{
        int y;
        int x;
        // 0:윗면, 1:아랫면, 2:앞면, 3:뒷면, 4:왼면, 5:오른면
        int[] value = new int[6];

        public Dice(int y, int x){
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로 길이
        M = Integer.parseInt(st.nextToken());   // 가로 길이
        dice = new Dice(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); // dice 위치
        K = Integer.parseInt(st.nextToken());   // 명령어 개수
        map = new int[N][M];                    // map
        command = new int[K];                   // 명령어 모음

        // map 입력
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령어 입력
        st = new StringTokenizer(br.readLine());
        for(int k = 0; k < K; ++k){
            command[k] = Integer.parseInt(st.nextToken());
        }

        String result = start();
        System.out.println(result);
    }

    public static String start(){
        StringBuilder sb = new StringBuilder();

        // 명령어 시작
        for(int k = 0; k < K; ++k){
            String result = move(command[k]);
            if(result == null) continue;  // 바깥으로 이동 시, 무시 + 출력 X
            sb.append(result).append("\n");
        }

        return sb.toString();
    }

    // 움직이기
    public static String move(int dir){
        int ny = dice.y + dy[dir];
        int nx = dice.x + dx[dir];

        if(!isIn(ny, nx)) return null;  // 바깥으로 이동 시, 명령어 무시
        int[] tmp = new int[6]; // 주사위의 값을 swap 하기 위한 임시 변수

        // 주사위 굴리기 => tmp 에 임시저장 후, 원래 dice.value 로 옮긴다.
        switch(dir){
            // 주사위 => 0:윗면, 1:아랫면, 2:앞면, 3:뒷면, 4:왼면, 5:오른면
            case 1: // 동
                tmp[0] = dice.value[4];
                tmp[1] = dice.value[5];
                tmp[2] = dice.value[2];
                tmp[3] = dice.value[3];
                tmp[4] = dice.value[1];
                tmp[5] = dice.value[0];
                break;
            case 2: // 서
                tmp[0] = dice.value[5];
                tmp[1] = dice.value[4];
                tmp[2] = dice.value[2];
                tmp[3] = dice.value[3];
                tmp[4] = dice.value[0];
                tmp[5] = dice.value[1];
                break;
            case 3: // 북
                tmp[0] = dice.value[2];
                tmp[1] = dice.value[3];
                tmp[2] = dice.value[1];
                tmp[3] = dice.value[0];
                tmp[4] = dice.value[4];
                tmp[5] = dice.value[5];
                break;
            case 4: // 남
                tmp[0] = dice.value[3];
                tmp[1] = dice.value[2];
                tmp[2] = dice.value[0];
                tmp[3] = dice.value[1];
                tmp[4] = dice.value[4];
                tmp[5] = dice.value[5];
                break;
        }

        // tmp 의 값 => dice.value 로 옮김
        for(int i = 0; i < 6; ++i){
            dice.value[i] = tmp[i];
        }

        // 이동한 칸의 숫자가 0이라면, 주사위 => 칸 값 복사
        if(map[ny][nx] == 0) map[ny][nx] = dice.value[1];
        // 이동한 칸의 숫자가 0이 아니라면, (칸 => 주사위 복사) + (칸 => 0)
        else {
            dice.value[1] = map[ny][nx];
            map[ny][nx] = 0;
        }

        // dice 새 좌표로 갱신
        dice.y = ny;
        dice.x = nx;

        return Integer.toString(dice.value[0]);
    }

    // 배열 범위 검사
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
