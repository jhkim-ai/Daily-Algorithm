package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2174_로봇시뮬레이션 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int A, B, N, M;
    private static int[][] map;
    private static Robot[] robots;
    private static int[][] commands;
    private static Queue<Command> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B][A];

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robots = new Robot[N];

        for(int i = 0; i < N; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - B;
            char dir = st.nextToken().charAt(0);
            int d = -1;
            switch (dir){
                case 'N':
                    d = 0;
                    break;
                case 'E':
                    d= 1;
                    break;
                case 'S':
                    d = 2;
                    break;
                case 'W':
                    d = 3;
                    break;
            }
            robots[i] = new Robot(i+1, y, x, d);
        }

        q = new LinkedList<>();
        commands = new int[M][3];
        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            int robotId = Integer.parseInt(st.nextToken()); // 로봇 id
            char c = st.nextToken().charAt(0);
            int command = -1; // 명령어(0: 'L', 1: 'R', 2: 'F')
            if(c == 'L'){
                command = 0;
            } else if (c == 'R'){
                command = 1;
            } else {
                command = 2;
            }
            int times = Integer.parseInt(st.nextToken());  // 반복 횟수
            q.offer(new Command(robotId, command, times)); // q에 저장
        }
    }

    static class Robot{
        int id;
        int y;
        int x;
        int dir;

        public Robot(int id, int y, int x, int dir){
            this.id = id;
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    static class Command{
        int id;
        int command;
        int times;

        public Command(int id, int command, int times) {
            this.id = id;
            this.command = command;
            this.times = times;
        }
    }

}
