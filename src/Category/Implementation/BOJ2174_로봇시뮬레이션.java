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
    private static Queue<Command> q;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        sb = new StringBuilder();
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[B][A];

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robots = new Robot[N + 1];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = B - Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            int d = -1;
            switch (dir) {
                case 'N':
                    d = 0;
                    break;
                case 'E':
                    d = 1;
                    break;
                case 'S':
                    d = 2;
                    break;
                case 'W':
                    d = 3;
                    break;
            }
            robots[i+1] = new Robot(i + 1, y, x, d);
            map[y][x] = i + 1;
        }

        q = new LinkedList<>();
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int robotId = Integer.parseInt(st.nextToken()); // 로봇 id
            char c = st.nextToken().charAt(0);
            int command = -1; // 명령어(0: 'L', 1: 'R', 2: 'F')
            if (c == 'L') {
                command = 0;
            } else if (c == 'R') {
                command = 1;
            } else {
                command = 2;
            }
            int times = Integer.parseInt(st.nextToken());  // 반복 횟수
            q.offer(new Command(robotId, command, times)); // q에 저장
        }

        run();
        if(sb.length() == 0){
            sb.append("OK");
        }
        System.out.println(sb);
    }

    public static void run() {
        while (!q.isEmpty()) {
            Command command = q.poll();
            int robotId = command.id;
            int cmd = command.command;
            int times = command.times;

            while (times-- > 0) {
                Robot robot = robots[robotId];
                int dir = robot.dir;

                switch (cmd) {
                    case 0: // 왼쪽으로 90도 회전
                        --dir;
                        if (dir < 0) {
                            robot.dir = 3;
                        } else {
                            robot.dir = dir;
                        }
                        break;
                    case 1: // 오른쪽으로 90도 회전
                        robot.dir = ++dir % 4;
                        break;
                    case 2: // 전진
                        if (!move(robot, dir)) {
                            return;
                        }
                        break;
                }
            }
        }
    }

    public static boolean move(Robot robot, int dir) {
        int ny = robot.y + dy[dir];
        int nx = robot.x + dx[dir];

        if (!isIn(ny, nx)) { // 벽에 부딪칠 때
            sb.append(String.format("Robot %d crashes into the wall", robot.id));
            return false;
        }
        if (map[ny][nx] != 0) { // 다른 로봇을 만날 때
            sb.append(String.format("Robot %d crashes into robot %d", robot.id, map[ny][nx]));
            return false;
        }

        map[ny][nx] = map[robot.y][robot.x];
        map[robot.y][robot.x] = 0;
        robot.y = ny;
        robot.x = nx;

        return true;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < B && x < A;
    }

    public static void print() {
        for (int y = 0; y < B; ++y) {
            for (int x = 0; x < A; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    static class Robot {
        int id;
        int y;
        int x;
        int dir;

        public Robot(int id, int y, int x, int dir) {
            this.id = id;
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

    static class Command {
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
