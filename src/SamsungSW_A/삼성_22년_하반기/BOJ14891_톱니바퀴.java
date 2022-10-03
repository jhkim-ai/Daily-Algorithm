package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14891_톱니바퀴 {

    private static final int N = 4;

    private static int K;
    private static List<Integer>[] gears;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gears = new List[N];
        for(int i = 0; i < N; ++i) {
            char[] arr = br.readLine().toCharArray();
            gears[i] = new ArrayList<>();
            for(int j = 0; j < 8; ++j) {
                gears[i].add(arr[j] - '0');
            }
        }

        K = Integer.parseInt(br.readLine());
        while(K-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int startIdx = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            checkRotate(startIdx, dir);
        }

        System.out.println(getScore());
    }

    public static void checkRotate(int startIdx, int originDir) {

        int dir = originDir;
        int[] dirInfo = new int[N];
        dirInfo[startIdx] = originDir;

        for(int i = startIdx; i < N - 1; ++i) {
            if(gears[i].get(2) == gears[i+1].get(6)) break;
            dir *= -1;
            dirInfo[i+1] = dir;
        }

        dir = originDir;
        for(int i = startIdx; i > 0; --i) {
            if(gears[i-1].get(2) == gears[i].get(6)) break;
            dir *= -1;
            dirInfo[i-1] = dir;
        }

        for(int i = 0; i < N; ++i){
            if(dirInfo[i] == 0) continue;
            rotate(i, dirInfo[i]);
        }
    }

    public static void rotate(int idx, int dir) {
        if(dir == 1){ // 시계
            int lastVal = gears[idx].remove(7);
            gears[idx].add(0, lastVal);
        } else { // 반시계
            int lastVal = gears[idx].remove(0);
            gears[idx].add(lastVal);
        }
    }

    public static int getScore() {
        int score = 0;

        for(int i = 0; i < N; ++i) {
            if(gears[i].get(0) == 0) continue;
            score += Math.pow(2, i);
        }

        return score;
    }
}
