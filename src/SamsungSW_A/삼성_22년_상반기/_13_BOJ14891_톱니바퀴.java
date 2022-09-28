package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _13_BOJ14891_톱니바퀴 {

    private static int N, K;
    private static int gearNum, dir;
    private static int[] isOk;
    private static int[][] gears;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 4;
        gears = new int[N+1][8];
        for(int i = 1; i <= N; ++i){
            char[] tmp = br.readLine().toCharArray();
            for(int j = 0; j < 8; ++j){
                gears[i][j] = tmp[j] - '0';
            }
        }

        K = Integer.parseInt(br.readLine());
        while(K-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            gearNum = Integer.parseInt(st.nextToken());
            dir = Integer.parseInt(st.nextToken());

            gearCheck();
            rotate();
        }

        System.out.println(getScore());
    }

    public static void gearCheck(){
        int baseGearNum = gearNum;
        int baseLeft = gears[baseGearNum][6];
        int baseRight = gears[baseGearNum][2];
        int baseDir = dir;
        isOk = new int[N+1];
        isOk[baseGearNum] = dir;

        while(++baseGearNum <= 4){ // 오른쪽 확인
            int compareLeft = gears[baseGearNum][6];
            int compareRight = gears[baseGearNum][2];

            if(baseRight == compareLeft) break;

            baseDir = -baseDir;
            isOk[baseGearNum] = baseDir;
            baseRight = compareRight;
        }

        baseGearNum = gearNum;
        baseLeft = gears[baseGearNum][6];
        baseRight = gears[baseGearNum][2];
        baseDir = dir;

        while(--baseGearNum > 0){
            int compareLeft = gears[baseGearNum][6];
            int compareRight = gears[baseGearNum][2];

            if(baseLeft == compareRight) break;

            baseDir = -baseDir;
            isOk[baseGearNum] = baseDir;
            baseLeft = compareLeft;
        }
    }

    public static void rotate(){
        for(int gearIdx = 1; gearIdx <= N; ++gearIdx){
            if(isOk[gearIdx] == 0) continue;
            if(isOk[gearIdx] == 1){
                clockwise(gearIdx);
            } else {
                counterClockwise(gearIdx);
            }
        }
    }

    public static void clockwise(int gearIdx){
        int tmp = gears[gearIdx][7];
        for(int i = 6; i >= 0; --i){
            gears[gearIdx][i+1] = gears[gearIdx][i];
        }
        gears[gearIdx][0] = tmp;
    }

    public static void counterClockwise(int idx){
        int tmp = gears[idx][0];
        for(int i = 0; i < 7; ++i){
            gears[idx][i] = gears[idx][i+1];
        }
        gears[idx][7] = tmp;
    }

    public static int getScore(){
        int sum = 0;

        for(int gearIdx = 1; gearIdx <= N; ++gearIdx){
            if(gears[gearIdx][0] == 0) continue;
            sum += Math.pow(2, gearIdx-1);
        }

        return sum;
    }
}
