import java.io.*;
import java.util.*;

public class sample {

    static int N, ans;
    static int[][] map;
    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        ans = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j < N+1; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        comb(N/2, new int[N/2], 1);
        System.out.println(ans);
    }

    static void comb(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            List<Integer> list = new ArrayList<>();
            boolean isIn;

            for (int i = 1; i <N+1 ; i++) {
                isIn = false;
                for (int j = 0; j < selected.length; j++) {
                    if(i == selected[j]) {
                        isIn = true;
                        break;
                    }
                }
                if(!isIn)
                    list.add(i);
            }
            int start = 0;
            int link = 0;
            for (int i = 0; i < N/2 -1; i++) {
                for (int j = i+1; j < N/2; j++) {
                    start += map[selected[i]][selected[j]];
                    start += map[selected[j]][selected[i]];
                    link += map[list.get(i)][list.get(j)];
                    link += map[list.get(j)][list.get(i)];
                }
            }
            ans = Math.min(ans, Math.abs(start-link));
            return;
        }

        for (int i = startIdx; i <= N; i++) {
            selected[selected.length -cnt] = i;
            comb(cnt-1, selected, i+1);
        }
    }

    static String input = "6\n" +
            "0 1 2 3 4 5\n" +
            "1 0 2 3 4 5\n" +
            "1 2 0 3 4 5\n" +
            "1 2 3 0 4 5\n" +
            "1 2 3 4 0 5\n" +
            "1 2 3 4 5 0";
}
