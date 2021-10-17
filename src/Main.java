import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static char[][] map;
    static int[] dx = { 0, 1, 1, 1, 0, 0, 0, -1, -1, -1 };
    static int[] dy = { 0, -1, 0, 1, -1, 0, 1, -1, 0, 1 };
    static Point jongsu;
    static List<Point> crazys;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        crazys = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'I') {
                    jongsu = new Point(i, j);
                } else if (map[i][j] == 'R') {
                    crazys.add(new Point(i, j));
                }
            }
        }

        boolean lose = false;
        String dirs = br.readLine();
        int moveCount = 1;
        int len = dirs.length();

        for (; moveCount <= len; moveCount++) {
            int dir = Integer.parseInt(dirs.substring(moveCount - 1, moveCount));

            // 1. 종수 이동
            map[jongsu.x][jongsu.y] = '.';
            jongsu.x = jongsu.x + dx[dir];
            jongsu.y = jongsu.y + dy[dir];

            // 2. 종수의 아두이노가 미친 아두이노가 있는 칸으로 이동하는 경우 게임 끝
            if (map[jongsu.x][jongsu.y] == 'R') {
                lose = true;
                break;
            } else {
                map[jongsu.x][jongsu.y] = 'I';
            }

            // 3. 미친 아두이노 이동.
            List<Point> list = new ArrayList<>();
            List<Point> removed = new ArrayList<>();

            for (int i = 0, crazysLen = crazys.size(); i < crazysLen; i++) {
                Point arduino = crazys.get(i);
                int d = getDir(jongsu.x, jongsu.y, arduino.x, arduino.y);

                map[arduino.x][arduino.y] = '.';
                arduino.x = arduino.x + dx[d];
                arduino.y = arduino.y + dy[d];

                // 4. 미친 아두이노가 종수의 칸으로 이동하는 경우 게임이 끝
                if (arduino.equals(jongsu)) {
                    lose = true;
                    break;
                }
                if (contains(list, arduino)) { // A(1, 2), B(1, 3), C(1, 4)
                    removed.add(arduino); // D(1, 4)
                } else {
                    list.add(arduino);
                }

                crazys.set(i, arduino);
            }

            if (lose) {
                break;
            }

            // 5. 두 개 이상의 아두이노가 같은 칸에 있는 경우 폭발
            for (int i = 0; i < crazys.size(); i++) {
                if (contains(removed, crazys.get(i))) {
                    map[crazys.get(i).x][crazys.get(i).y] = '.';
                    crazys.remove(i);
                    i--;
                } else {
                    map[crazys.get(i).x][crazys.get(i).y] = 'R';
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if (lose) {
            sb.append("kraj " + moveCount);
        } else {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    static void print() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    static boolean contains(List<Point> list, Point p) {
        for (int i = 0, len = list.size(); i < len; i++) {
            if (list.get(i).x == p.x && list.get(i).y == p.y) {
                return true;
            }
        }
        return false;
    }

    static int getDir(int jongsuX, int jongsuY, int crazyX, int crazyY) {
        int minDistance = Integer.MAX_VALUE;
        int dir = 0;
        for (int d = 1; d <= 9; d++) {
            if (d == 5)
                continue;
            int distance = getDistance(jongsuX, jongsuY, crazyX + dx[d], crazyY + dy[d]);
            if (distance < minDistance) {
                dir = d;
                minDistance = distance;
            }
        }
        return dir;
    }

    static int getDistance(int jongsuX, int jongsuY, int crazyX, int crazyY) {
        return Math.abs(jongsuX - crazyX) + Math.abs(jongsuY - crazyY);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String toString() {
            return "[" + this.x + ", " + this.y + "]";
        }

        public boolean equals(Point p) {
            return this.x == p.x && this.y == p.y && this.hashCode() == p.hashCode();
        }

        public int hashCode() {
            return 1;
        }
    }
}
