package StepByStep.day211030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2116_주사위쌓기 {

    private static final int[] pair = {5, 3, 4, 1, 2, 0};

    private static int N, ans;
    private static int[][] dices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dices = new int[N][6];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 6; j++) {
                dices[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 6; i++) {
            int bottomIdx = i;
            int topIdx = pair[bottomIdx];
            int top = dices[0][topIdx];
            int sum = getMaxNumOfDice(dices[0], bottomIdx);

            for(int next = 1; next < N; ++next){
                bottomIdx = getBottomIdx(dices[next], top);
                topIdx = pair[bottomIdx];
                top = dices[next][topIdx];
                sum += getMaxNumOfDice(dices[next], bottomIdx);
            }

            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }

    public static int getMaxNumOfDice(int[] dice, int bottomIdx){
        int maxNum = Integer.MIN_VALUE;
        int topIdx = pair[bottomIdx];

        for (int i = 0; i < 6; i++) {
            if(i == bottomIdx || i == topIdx){
                continue;
            }
            maxNum = Math.max(maxNum, dice[i]);
        }

        return maxNum;
    }

    public static int getBottomIdx(int[] dice, int top){
        int bottomIdx = -1;

        for(int i = 0; i < 6; ++i){
            if(dice[i] == top){
                bottomIdx = i;
                break;
            }
        }

        return bottomIdx;
    }
}
