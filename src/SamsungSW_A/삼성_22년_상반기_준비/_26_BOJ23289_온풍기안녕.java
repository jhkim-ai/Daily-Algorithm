package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class _26_BOJ23289_온풍기안녕 {

    private static int[] hy = {0, 1, -1, 1, 1, 1, 0, -1, 1, -1, -1, -1};
    private static int[] hx = {1, 1, 1, 0, -1, 1, -1, -1, -1, 0, 1, -1};

    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {1, 0, -1, 0};

    private static int N, M, K, W, chocolate;
    private static int[][] tmpMap;
    private static List<Point> searchLoc;
    private static List<Point> listHeater;
    private static Map<Integer, Set<Integer>> mapWallInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        chocolate = 0;
        tmpMap = new int[N][M];
        listHeater = new ArrayList<>();
        searchLoc = new ArrayList<>();
        mapWallInfo = new HashMap<>();

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 5) {
                    searchLoc.add(new Point(y, x));
                } else if (num > 0) {
                    if (num == 1) {
                        num = 0;
                    } else if (num == 4) {
                        num = 1;
                    }
                    listHeater.add(new Point(y, x, num));
                }
            }
        }

        W = Integer.parseInt(br.readLine());
        while (W-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());
            int idx = y * M + x;

            if (!mapWallInfo.containsKey(idx)) {
                mapWallInfo.put(idx, new HashSet<>());
            }

            if (t == 0) {
                mapWallInfo.get(idx).add(3);
                idx = (y - 1) * M + x;
                if (!mapWallInfo.containsKey(idx)) {
                    mapWallInfo.put(idx, new HashSet<>());
                }
                mapWallInfo.get(idx).add(1);
            } else {
                mapWallInfo.get(idx).add(0);
                idx = y * M + x + 1;
                if (!mapWallInfo.containsKey(idx)) {
                    mapWallInfo.put(idx, new HashSet<>());
                }
                mapWallInfo.get(idx).add(2);
            }
        }

        // Simulation 시작
        while (true) {
            runHeater();
            adjust();
            decreaseEdgeArea();
            chocolate++;
            if (inspect() || chocolate > 100) {
                break;
            }
        }

        System.out.println(chocolate);
    }

    // Heater 틀기기
    public static void runHeater() {
        Queue<Point> q = new LinkedList<>();
        for (Point heater : listHeater) {
            q.clear();
            boolean[][] visited = new boolean[N][M];
            int d = heater.d;
            int ny = heater.y + dy[d];
            int nx = heater.x + dx[d];
            int temperature = 5;

            if (!isIn(ny, nx)) {
                continue;
            }

            tmpMap[ny][nx] += temperature;
            visited[ny][nx] = true;
            q.offer(new Point(ny, nx));

            // bfs 를 이용하여 온도 상승 위치 구하기
            while (!q.isEmpty()) {
                temperature--;
                if (temperature == 0) {
                    break;
                }

                int size = q.size();
                for (int s = 0; s < size; ++s) {
                    Point now = q.poll();
                    for (int nd = d * 3; nd < d * 3 + 3; ++nd) {

                        // 현재 위치 기준으로 벽 체크
                        int idx = now.y * M + now.x;
                        if (nd % 3 == 1) {
                            if (isWall(idx, (d + 1) % 4)) {
                                continue;
                            }
                        } else if (nd % 3 == 2) {
                            if (isWall(idx, (d + 3) % 4)) {
                                continue;
                            }
                        }

                        // 이동할 위치 기준으로 벽 체크
                        ny = now.y + hy[nd];
                        nx = now.x + hx[nd];
                        if (!isIn(ny, nx) || visited[ny][nx]) {
                            continue;
                        }

                        idx = ny * M + nx;
                        if (isWall(idx, (d + 2) % 4)) {
                            continue;
                        }

                        // 온도 증가
                        tmpMap[ny][nx] += temperature;
                        visited[ny][nx] = true;
                        q.offer(new Point(ny, nx));
                    }
                }
            }
        }
    }

    // 온도 조절하기
    public static void adjust() {
        int[][] tmp = new int[N][M];
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                for (int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (!isIn(ny, nx)) { // 배열 index 유효 검사
                        continue;
                    }

                    int idx = y * M + x;
                    if (isWall(idx, d)) { // 벽 검사
                        continue;
                    }

                    // 조건에 따라 온도 증감 저장 (주의. 동시에 온도가 바뀐다)
                    int L = 0;
                    if (tmpMap[y][x] > tmpMap[ny][nx]) {
                        L = tmpMap[y][x] - tmpMap[ny][nx];
                        L /= 4;
                        tmp[y][x] += -L;
                        tmp[ny][nx] += L;
                    } else if (tmpMap[y][x] < tmpMap[ny][nx]) {
                        L = tmpMap[ny][nx] - tmpMap[y][x];
                        L /= 4;
                        tmp[y][x] += L;
                        tmp[ny][nx] += -L;
                    }
                }
            }
        }

        // 온도 증감 update
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                tmpMap[y][x] += tmp[y][x] / 2; // 온도 조절을 한 위치당 2번 계산하기에 2를 나눈다.
            }
        }
    }

    // 가장자리 온도 감소
    public static void decreaseEdgeArea() {
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                if ((y == 0 || y == N - 1) && tmpMap[y][x] > 0) {
                    tmpMap[y][x]--;
                } else if ((x == 0 || x == M - 1) && tmpMap[y][x] > 0) {
                    tmpMap[y][x]--;
                }
            }
        }
    }

    // 온도 검사
    public static boolean inspect() {
        for (Point p : searchLoc) {
            if (tmpMap[p.y][p.x] < K) {
                return false;
            }
        }
        return true;
    }

    // 배열 index 가 유효한가?
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    // 벽인가?
    public static boolean isWall(int idx, int d) {
        if (mapWallInfo.containsKey(idx)
            && mapWallInfo.get(idx).contains(d)) {
            return true;
        }
        return false;
    }

    public static class Point {

        int y;
        int x;
        int d;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}


