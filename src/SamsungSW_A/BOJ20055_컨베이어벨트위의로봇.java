package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ20055_컨베이어벨트위의로봇 {

    private static int N, K;
    private static int[] conveyerBelt;
    private static boolean[] robots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        conveyerBelt = new int[2 * N];
        robots = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 2 * N; i++) {
            conveyerBelt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(run());

    }

    public static int run(){
        int cnt = 0;
        while(isValid()) {
            ++cnt;

            // 1. 벨트 움직이기
            int tmp = conveyerBelt[2 * N - 1];
            for (int i = 2 * N - 1; i > 0; --i) {
                conveyerBelt[i] = conveyerBelt[i - 1];
            }
            conveyerBelt[0] = tmp;

            // 로봇도 움직이기
            for (int i = N - 1; i > 0; --i) {
                robots[i] = robots[i - 1];
            }
            robots[0] = false; // 움직인 후, '올리는 위치'에는 로봇이 없다.
            robots[N - 1] = false; // 움직인 후, '내리는 위치'에서 로봇이 내리기 때문에 로봇이 없다.

            // 2. 로봇 움직이기
            for (int i = N - 1; i > 0; --i) {
                if (robots[i - 1] && !robots[i] && conveyerBelt[i] >= 1) {
                    robots[i] = true;
                    robots[i - 1] = false;
                    conveyerBelt[i]--;
                }
            }
            robots[N - 1] = false; // 마지막 로봇 내리기

            // 3. 로봇 추가
            if (conveyerBelt[0] >= 1) {
                robots[0] = true;
                conveyerBelt[0]--;
            }
        }

        return cnt;
    }

    public static boolean isValid(){
        int cnt = 0;
        for(int i = 0; i < 2*N; ++i){
            if(conveyerBelt[i] < 1){
                ++cnt;
            }
            if(cnt == K){
                return false;
            }
        }
        return true;
    }
}
