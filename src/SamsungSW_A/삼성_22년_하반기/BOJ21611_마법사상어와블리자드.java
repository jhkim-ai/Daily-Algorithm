package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ21611_마법사상어와블리자드 {

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {-1, 0, 1, 0};

    private static final int[] dy2 = {-1, 1, 0, 0};
    private static final int[] dx2 = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            throwIceFragment(d, s);
            while(true) {
                move();
                if(!bomb()) break;
            }
            changeBead();
        }

        System.out.println(ans);
    }

    public static void changeBead() {
        int y = N / 2;
        int x = N / 2;
        int d = 0;

        int ny = y;
        int nx = x;
        int len = 1;
        int cnt = 1;
        int base = -1;

        List<Integer> tmpList = new ArrayList<>();

        outer:
        while(true) {

            for(int idx = 0; idx < len; ++idx) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx) || map[ny][nx] == 0) break outer;

                if(base == map[ny][nx]) {
                    ++cnt;
                    continue;
                }

                tmpList.add(cnt);
                tmpList.add(base);

                base = map[ny][nx];
                cnt = 1;
            }

            d = (d + 1) % 4;

            for(int i = 0; i < len; ++i) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx) || map[ny][nx] == 0) break outer;

                if(base == map[ny][nx]) {
                    ++cnt;
                    continue;
                }

                tmpList.add(cnt);
                tmpList.add(base);

                base = map[ny][nx];
                cnt = 1;
            }

            d = (d + 1) % 4;
            ++len;
        }

        tmpList.add(cnt);
        tmpList.add(base);

        d = 0;
        ny = y;
        nx = x;
        len = 1;
        int idx = 2;

        outer:
        while(true) {

            for(int i = 0; i < len; ++i) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx)) break outer;

                if(tmpList.size() > idx) map[ny][nx] = tmpList.get(idx++);
                else map[ny][nx] = 0;
            }

            d = (d + 1) % 4;

            for(int i = 0; i < len; ++i) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx)) break outer;

                if(tmpList.size() > idx) map[ny][nx] = tmpList.get(idx++);
                else map[ny][nx] = 0;
            }

            d = (d + 1) % 4;
            ++len;
        }
    }

    public static boolean bomb() {
        boolean isBomb = false;

        int y = N / 2;
        int x = N / 2;
        int d = 0;

        int ny = y;
        int nx = x;
        int len = 1;
        int base = -1;
        int cnt = 1;

        List<int[]> deleteList = new ArrayList<>();

        outer:
        while(true) {

            for(int idx = 0; idx < len; ++idx) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx) || map[ny][nx] == 0) break outer;

                if(base == map[ny][nx]) {
                    ++cnt;
                    deleteList.add(new int[]{ny, nx});
                    continue;
                }

                if(deleteList.size() >= 4) isBomb = delete(deleteList);

                deleteList.clear();
                base = map[ny][nx];
                cnt = 1;
                deleteList.add(new int[]{ny, nx});
            }

            d = (d + 1) % 4;

            for(int idx = 0; idx < len; ++idx) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx) || map[ny][nx] == 0) break outer;

                if(base == map[ny][nx]) {
                    ++cnt;
                    deleteList.add(new int[]{ny, nx});
                    continue;
                }

                if(deleteList.size() >= 4) isBomb = delete(deleteList);

                deleteList.clear();
                base = map[ny][nx];
                cnt = 1;
                deleteList.add(new int[]{ny, nx});
            }

            d = (d + 1) % 4;
            ++len;
        }

        if(deleteList.size() >= 4) isBomb = delete(deleteList);

        return isBomb;
    }

    public static void move() {
        int y = N / 2;
        int x = N / 2;
        int d = 0;

        int ny = y;
        int nx = x;
        int len = 1;

        List<Integer> tmpList = new ArrayList<>();

        outer:
        while(true) {

            for(int cnt = 0; cnt < len; ++cnt) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx)) break outer;
                if(map[ny][nx] == 0) continue;

                tmpList.add(map[ny][nx]);
            }

            d = (d + 1) % 4;

            for(int cnt = 0; cnt < len; ++cnt) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx)) break outer;
                if(map[ny][nx] == 0) continue;

                tmpList.add(map[ny][nx]);
            }

            d = (d + 1) % 4;
            ++len;
        }

        d = 0;
        ny = y;
        nx = x;
        len = 1;
        int idx = 0;

        outer:
        while(true) {

            for(int cnt = 0; cnt < len; ++cnt) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx)) break outer;

                if(tmpList.size() > idx) map[ny][nx] = tmpList.get(idx++);
                else map[ny][nx] = 0;
            }

            d = (d + 1) % 4;

            for(int cnt = 0; cnt < len; ++cnt) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if(!isIn(ny, nx)) break outer;

                if(tmpList.size() > idx) map[ny][nx] = tmpList.get(idx++);
                else map[ny][nx] = 0;
            }

            d = (d + 1) % 4;
            ++len;
        }
    }

    public static void throwIceFragment(int d, int s) {
        int y = N / 2;
        int x = N / 2;

        for(int ns = 1; ns <= s; ++ns) {
            int ny = y + dy2[d] * ns;
            int nx = x + dx2[d] * ns;
            map[ny][nx] = 0;
        }
    }

    public static boolean delete(List<int[]> deleteList) {
        int sum = map[deleteList.get(0)[0]][deleteList.get(0)[1]];
        ans += sum * deleteList.size();

        for(int[] loc : deleteList) {
            map[loc[0]][loc[1]] = 0;
        }

        return true;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
