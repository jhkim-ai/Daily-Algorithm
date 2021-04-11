package StepByStep.day210410;

import java.util.*;
import java.io.*;

public class BOJ18428_감시피하기 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N;
    static char[][] map;
    static List<Point> teacherList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teacherList = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = st.nextToken().charAt(0);
                if (map[y][x] == 'T')
                    teacherList.add(new Point(y, x));
            }
        }

        combination(3, new Point[3], 0, 0);
        System.out.println("NO");
    }

    // 조합
    static void combination(int cnt, Point[] selected, int startY, int startX) {
        if (cnt == 0) {
            for (Point teacher : teacherList) {
                for (int d = 0; d < 4; d++) {
                    int ny = teacher.y + dy[d];
                    int nx = teacher.x + dx[d];
                    while (true) {
                        ny += dy[d];
                        nx += dx[d];
                        if (!isIn(ny, nx)) break;
                        if (isObstacle(ny, nx)) break;
                        if (isStudent(ny, nx)) {
                            for(Point obstacle : selected)
                                map[obstacle.y][obstacle.x] = 'X';
                            return;
                        }
                    }
                }
            }
            System.out.println("YES");
            System.exit(0);
            return;
        }

        if (startX == N) {
            startX = 0;
            startY++;
        }

        for (int y = startY; y < N; y++) {
            for (int x = startX; x < N; x++) {
                if (map[y][x] == 'S' || map[y][x] == 'T')
                    continue;
                selected[selected.length - cnt] = new Point(y, x);
                map[y][x] = 'O';
            }
        }
    }

    static boolean isStudent(int y, int x) {
        if (map[y][x] == 'S')
            return true;
        return false;
    }

    static boolean isObstacle(int y, int x) {
        if (map[y][x] == 'O')
            return true;
        return false;
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static void print(int[][] arr) {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(arr[y][x]);
            }
            System.out.println();
        }
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
