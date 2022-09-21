package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058_마법사상어와파이어스톰 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, L, Q;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = (int)Math.pow(2, N);
        Q = Integer.parseInt(st.nextToken());
        map = new int[M][M];

        for(int y = 0; y < M; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while(Q-- > 0) {
            L = Integer.parseInt(st.nextToken());
            divide(M, 0, 0);
            int[][] tmpMap = new int[M][M];
            melt(tmpMap);
            setMap(tmpMap);
        }

        int ans = 0;
        boolean[][] visited = new boolean[M][M];
        for(int y = 0; y < M; ++y) {
            for(int x = 0; x < M; ++x) {
                if(map[y][x] < 1 || visited[y][x]) continue;
                ans = Math.max(ans, getIceBergSize(y, x, visited));
            }
        }
        System.out.println(getSumIce());
        System.out.println(ans);
    }

    public static void divide(int len, int y, int x) {
        if(len == (int)Math.pow(2, L)) {
            rotate(len, y, x);
            return;
        }

        int half = len / 2;
        divide(half, y, x);
        divide(half, y + half, x);
        divide(half, y, x + half);
        divide(half, y + half, x + half);
    }

    public static void rotate(int len, int y, int x) {
        List<Integer> list = new ArrayList<>();
        for(int i = y; i < y + len; ++i) {
            for(int j = x; j < x + len; ++j) {
                list.add(map[i][j]);
            }
        }

        int idx = 0;
        for(int i = x + len - 1; i >= x; --i) {
            for(int j = y; j < y + len; ++j) {
                map[j][i] = list.get(idx++);
            }
        }
    }

    public static void melt(int[][] tmpMap) {
        for(int y = 0; y < M; ++y) {
            for(int x = 0; x < M; ++x) {
                int cnt = 4;
                for(int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] == 0) {
                        cnt--;
                        continue;
                    }
                }

                if(cnt < 3 && map[y][x] > 0) {
                    tmpMap[y][x] = -1;
                }
            }
        }
    }

    public static int getSumIce() {
        int sum = 0;
        for(int y = 0; y < M; ++y) {
            for(int x = 0; x < M; ++x) {
                sum += map[y][x];
            }
        }

        return sum;
    }

    public static int getIceBergSize(int y, int x, boolean[][] visited) {
        int size = 1;
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];
                if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] < 1) continue;

                ++size;
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }

        return size;
    }

    public static void setMap(int[][] tmpMap) {
        for(int y = 0; y < M; ++y) {
            for(int x = 0; x < M; ++x) {
                if(tmpMap[y][x] == 0) continue;
                map[y][x] += tmpMap[y][x];
            }
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < M;
    }

    public static void print(int[][] map) {
        System.out.println("============");
        for(int y = 0; y < M; ++y){
            System.out.println(Arrays.toString(map[y]));
        }
        System.out.println("============");
    }
}
