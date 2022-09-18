package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20055_컨베이어벨트위의로봇 {

    public static int N, K, cnt, ans;
    public static int[] belt;
    public static boolean[] robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new int[2*N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 2 * N; ++ i) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while(true) {
            ++ans;
            moveBelt();
            moveRobot();
            addRobot();

            if(cnt >= K) break;
        }

        System.out.println(ans);
    }

    public static void moveBelt() {
        int tmp = belt[2*N - 1];
        for(int i = 2*N - 1; i > 0; --i) {
            belt[i] = belt[i-1];
        }
        belt[0] = tmp;

        for(int i = N-1; i > 0; --i) {
            robot[i] = robot[i-1];
        }

        robot[0] = false;

        ridRobot();
    }

    public static void moveRobot() {
        for(int i = N-1; i >= 0; --i) {
            if(!robot[i]) continue;

            if(robot[i+1] || belt[i+1] < 1) continue;
            robot[i] = false;
            robot[i+1] = true;
            belt[i+1]--;

            if(belt[i+1] < 1) ++cnt;
        }

        ridRobot();
    }

    public static void addRobot() {
        if(robot[0] || belt[0] < 1) return;
        robot[0] = true;
        belt[0]--;
        if(belt[0] < 1) ++cnt;
    }

    public static void ridRobot() {
        if(robot[N-1]) robot[N-1] = false;
    }
}
