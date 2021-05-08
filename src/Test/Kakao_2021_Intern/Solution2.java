package Test.Kakao_2021_Intern;

import java.util.*;
import java.io.*;

public class Solution2 {
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new StringReader(input5));

        map = new char[5][5];
        for (int i = 0; i < 5; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        List<Integer> list = new ArrayList<>();

        inner:for (int y = 0; y < 5; ++y) {
            for (int x = 0; x < 5; ++x) {
                if (map[y][x] == 'P') {
                    // System.out.println("\n\nBFS 새로 시작");
                    if(bfs(y, x))
                        list.add(1);
                    else
                        list.add(0);
                }
            }
        }
        System.out.println(list);
    }

    public static boolean bfs(int y, int x) {
        boolean[][] visited = new boolean[5][5];
        int[][] dist = new int[5][5];

        Queue<Point> q = new LinkedList<>();
        visited[y][x] = true;
        q.offer(new Point(y, x));

        int distance = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            distance++;

            for (int i = 0; i < size; ++i) {
                Point now = q.poll();

                // 새 좌표
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    // System.out.println("현좌표 : " + ny + "-" + nx);
                    // 범위 검사, 방문 체크, 파티션 검사
                    if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] =='X') continue;

                    dist[ny][nx] = distance;
                    visited[ny][nx] = true;

                    // 새 좌표가 P이고 맨해튼 거리가 2이하면 불가능
                    if(map[ny][nx] == 'P' && dist[ny][nx] <= 2)
                        return false;

                    q.offer(new Point(ny, nx));
                }
            }
            // System.out.println("\n다시 시작");
            // print(dist);
        }
        return true;
    }

    public static void print(int[][] dist) {
        System.out.println("==============");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(dist[i][j] + " ");
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

    // 배열 범위 검사
    public static boolean isIn(int y, int x) {
        return y >= 0 && y < 5 && x >= 0 && x < 5;
    }

    private static String input = "POOOP\n" +
            "OXXOX\n" +
            "OPXPX\n" +
            "OOXOX\n" +
            "POXXP";

    private static String input2 = "POOPX\n" +
            "OXPXP\n" +
            "PXXXO\n" +
            "OXXXO\n" +
            "OOOPP";

    private static String input3 = "PXOPX\n" +
            "OXOXP\n" +
            "OXPXX\n" +
            "OXXXP\n" +
            "POOXX";

    private static String input4 = "OOOXX\n" +
            "XOOOX\n" +
            "OOOXX\n" +
            "OXOOX\n" +
            "OOOOO";
    private static String input5 = "PXPXP\n" +
            "XPXPX\n" +
            "PXPXP\n" +
            "XPXPX\n" +
            "PXPXP";
}
