package Category.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ22993_서든어택3 {

    private static int N;
    private static long JoonWon;
    private static int[] players;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        players = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        JoonWon = Long.parseLong(st.nextToken());
        for (int i = 0; i < N - 1; ++i) {
            players[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        Arrays.sort(players);

        for (int num : players) {
            if (JoonWon > num) {
                JoonWon += num + 0l;
                ++cnt;
            } else {
                break;
            }
        }

        String ans = null;
        if (cnt == N - 1) {
            ans = "Yes";
        } else {
            ans = "No";
        }
        System.out.println(ans);
    }
}
