package StepByStep._2021.day211030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1453_피시방알바 {

    private static int N;
    private static boolean[] seats;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        seats = new boolean[101];
        int ans = 0;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        while(N-- > 0){
            int seat = Integer.parseInt(st.nextToken());
            if(!seats[seat]){
                seats[seat] = true;
            } else {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
