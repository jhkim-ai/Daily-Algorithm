package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1633_최고의팀만들기 {

    private static int[][][] dp;
    private static int[] white;
    private static int[] black;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new int[1000][16][16];
        white = new int[1000];
        black = new int[1000];
        int idx = 0;

        String str = br.readLine();
        while (!str.equals("")) {
            StringTokenizer st = new StringTokenizer(str, " ");
            white[idx] = Integer.parseInt(st.nextToken());
            black[idx] = Integer.parseInt(st.nextToken());

            str = br.readLine();
            ++idx;
        }
        System.out.println(idx);
        System.out.println(dfs(0, 0, 0, idx));
    }

    public static int dfs(int i, int idxWhite, int idxBlack, int total) {
        if (idxWhite == 15 && idxBlack == 15) {
            return 0;
        }
        if (i == total) {
            return 0;
        }

        if (dp[i][idxWhite][idxBlack] != 0) {
            return dp[i][idxWhite][idxBlack];
        }

        // 선택 x
        int val = dfs(i+1, idxWhite, idxBlack, total);
        // white 선택
        if(idxWhite < 15) val = Math.max(val, dfs(i+1, idxWhite+1, idxBlack, total) + white[i]);
        // black 선택
        if(idxBlack < 15) val = Math.max(val, dfs(i+1, idxWhite, idxBlack+1, total) + black[i]);

        dp[i][idxWhite][idxBlack] = val;
        return val;
    }
}
