package Test.KaKao.신입_2022;

import java.util.Arrays;

public class Solution4 {

    private static int n;
    private static int[] info;

    private static int N;
    private static int ans;
    private static int[] apeachInfo;
    private static int[] tmpLionInfo;

    public static void main(String[] args) {
        n = 10;
        info = new int[]{0,0,0,0,0,0,0,0,3,4,3};

        N = n;
        apeachInfo = info;
        ans = Integer.MIN_VALUE;

        // 중복 조합
        duplicatedCombination(n, new int[N], 0);
        if (tmpLionInfo == null) {
            System.out.println(-1);
        } else {
            System.out.println(Arrays.toString(tmpLionInfo));
        }
    }

    // 중복 조합 (모든 경우의 수를 따지자)
    public static void duplicatedCombination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            calculate(selected);
            return;
        }

        for (int i = startIdx; i < 11; ++i) {
            selected[selected.length - cnt] = i;
            duplicatedCombination(cnt - 1, selected, i);
        }
    }

    public static void calculate(int[] selected) {
        int lionScore = 0; // 라이언 Score
        int apeachScore = 0; // 어피치 Score
        int[] lionInfo = new int[11];

        // lion 과녁 정보 입력
        for (int targetScore : selected) {
            lionInfo[targetScore]++;
        }

        for (int i = 0; i < 11; ++i) {
            if (apeachInfo[i] == 0 && lionInfo[i] == 0) {
                continue;
            } else if (lionInfo[i] > apeachInfo[i]) {
                lionScore += 10 - i;
            } else {
                apeachScore += 10 - i;
            }
        }

        int diff = lionScore - apeachScore;
        if(diff <= 0){
            return;
        } else if (diff > ans) {
            ans = diff;
            tmpLionInfo = lionInfo;
        } else if (diff == ans) {
            for (int i = 10; i >= 0; --i) {
                if (lionInfo[i] == tmpLionInfo[i]) {
                    continue;
                } else if (tmpLionInfo[i] < lionInfo[i]) {
                    tmpLionInfo = lionInfo;
                    break;
                } else {
                    break;
                }
            }
        }
    }
}
