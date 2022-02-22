package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class _26_BOJ23289_온풍기안녕 {

    private static final int[] hy = {0, -1, 1, 0, -1, 1, -1, -1, -1, 1, 1, 1};
    private static final int[] hx = {1, 1, 1, -1, -1, -1, 0, -1, 1, 0, -1, 1};

    private static int N, M, K;
    private static int[][] map;
    private static int[][] tmpMap;
    private static Map<Integer, Integer> mapWallInfo;
    private static List<int[]> searchTempList;
    private static List<Heater> listHeater;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        mapWallInfo = new HashMap<>();
        searchTempList = new ArrayList<>();
        listHeater = new LinkedList<>();

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 5) {
                    map[y][x] = num;
                    searchTempList.add(new int[]{y, x});
                } else if (num == 0) {
                    map[y][x] = num;
                } else {
                    map[y][x] = num - 1;
                    listHeater.add(new Heater(y, x, map[y][x]));
                }
            }
        }

        // 벽 설정
        int w = Integer.parseInt(br.readLine());
        while (w-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int idx = y * M + x;
            if (d == 0) {
                mapWallInfo.put(idx, 2);
                idx = (y - 1) * M + x;
                mapWallInfo.put(idx, 3);
            } else { // d == 1
                mapWallInfo.put(idx, 0);
                idx = y * M + (x + 1);
                mapWallInfo.put(idx, 1);
            }
        }

        runHeater();
    }

    public static void runHeater() {
        boolean[][] visited;
        Queue<Point> q = new LinkedList<>();
        for (Heater heater : listHeater) {
            int hd = heater.d;
            int temperature = 6;
            visited = new boolean[N][M];
            q.clear();
            q.offer(new Point(heater.y, heater.x));
            visited[heater.y][heater.x] = true;

            while (!q.isEmpty()) {
                int size = q.size();
                temperature--;
                if (temperature == 0) {
                    break;
                }
                for (int s = 0; s < size; ++s) {
                    Point now = q.poll();
                    for (int d = hd; d < hd + 3; ++d) {
                        int ny = now.y + hy[d];
                        int nx = now.x + hx[d];
                        if(!isIn(ny, nx)) continue;

                        visited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                        tmpMap[ny][nx] += temperature;
                    }
                }
            }
        }
    }

    public static boolean isWall(int y, int x, int d) {
        int idx = y * M + x;
        if (mapWallInfo.get(idx) == d) {
            return true;
        }
        return false;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static class Heater {

        int y;
        int x;
        int d;

        public Heater(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }

    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
