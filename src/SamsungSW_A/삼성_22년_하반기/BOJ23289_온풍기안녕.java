package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ23289_온풍기안녕 {

    private static final int cntDir = 4;

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M, K, ans;
    private static int[][] map;

    private static Set<Integer>[][] setWall;

    private static Queue<int[]> qTarget;
    private static Queue<Heater> qHeater;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        setWall = new Set[N][M];
        qTarget = new LinkedList<>();
        qHeater = new LinkedList<>();

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                setWall[y][x] = new HashSet<>();
                map[y][x] = Integer.parseInt(st.nextToken());

                if (map[y][x] == 0) continue;

                if (map[y][x] == 5) {
                    qTarget.offer(new int[]{y, x});
                    map[y][x] = 0;
                    continue;
                }

                qHeater.offer(new Heater(y, x, map[y][x]));
                map[y][x] = 0;
            }
        }

        int cnt = Integer.parseInt(br.readLine());
        while (cnt-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");

            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int wallShape = Integer.parseInt(st.nextToken()); // 0: vertical, 1: horizontal

            int dir = 0;
            if (wallShape != 0) dir = 1;

            setWall[y][x].add(dir);
            dir = (dir + 2) % 4;

            if (wallShape == 0) setWall[y - 1][x].add(dir);
            else setWall[y][x + 1].add(dir);
        }

        while (true) {
            blow();
            divide();
            downTemperacture();

            ans++;
            if (ans > 100) {
                ans = 101;
                break;
            }

            if (check()) break;
        }
        System.out.println(ans);
    }

    public static void blow() {
        Iterator<Heater> iterator = qHeater.iterator();
        Queue<int[]> tmpQ = new LinkedList<>();

        while (iterator.hasNext()) {
            boolean[][] visited = new boolean[N][M];
            Heater nowHeater = iterator.next();
            tmpQ.clear();

            int y = nowHeater.y;
            int x = nowHeater.x;
            int d = nowHeater.d;
            int len = 1;

            int ny = y + dy[d];
            int nx = x + dx[d];
            tmpQ.offer(new int[]{ny, nx});

            while (!tmpQ.isEmpty()) {
                int size = tmpQ.size();
                int score = 6 - len;

                for (int time = 0; time < size; ++time) {
                    int[] now = tmpQ.poll();

                    int nowY = now[0];
                    int nowX = now[1];

                    map[nowY][nowX] += score;

                    for (int s = 1; s >= -1; --s) {
                        int nd = ((d + s) % cntDir + cntDir) % cntDir;

                        if (setWall[nowY][nowX].contains(nd)) continue;
                        ny = nowY + dy[nd];
                        nx = nowX + dx[nd];

                        if (!isIn(ny, nx) || visited[ny][nx]) continue;
                        if (s == 0) {
                            tmpQ.offer(new int[]{ny, nx});
                            visited[ny][nx] = true;
                            continue;
                        }

                        if (setWall[ny][nx].contains(d)) continue;
                        int nny = ny + dy[d];
                        int nnx = nx + dx[d];

                        if (!isIn(nny, nnx) || visited[nny][nnx]) continue;

                        tmpQ.offer(new int[]{nny, nnx});
                        visited[nny][nnx] = true;
                    }

                    if (!isIn(ny, nx)) continue;
                }
                ++len;

                if (len == 6) break;
            }
        }
    }

    public static void divide() {

        int[][] copyMap = new int[N][M];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                for (int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (!isIn(ny, nx) || map[y][x] <= map[ny][nx]) continue;
                    if (setWall[y][x].contains(d)) continue;

                    int diff = (map[y][x] - map[ny][nx]) / 4;
                    copyMap[y][x] -= diff;
                    copyMap[ny][nx] += diff;
                }
            }
        }

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                map[y][x] += copyMap[y][x];
                if (map[y][x] < 0) map[y][x] = 0;
            }
        }
    }

    public static void downTemperacture() {
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                if (y == 0 || y == N - 1 || x == 0 || x == M - 1) {
                    if (map[y][x] > 0) map[y][x]--;
                }
            }
        }
    }

    public static boolean check() {

        int cnt = 0;
        int size = qTarget.size();
        Iterator<int[]> iterator = qTarget.iterator();

        while (iterator.hasNext()) {
            int[] now = iterator.next();
            if (map[now[0]][now[1]] >= K) ++cnt;
        }

        if (cnt == size) return true;
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
            this.setD(d);

        }

        public void setD(int d) {
            switch (d) {
                case 1:
                    this.d = 1;
                    break;
                case 2:
                    this.d = 3;
                    break;
                case 3:
                    this.d = 0;
                    break;
                case 4:
                    this.d = 2;
                    break;
            }
        }
    }

}
