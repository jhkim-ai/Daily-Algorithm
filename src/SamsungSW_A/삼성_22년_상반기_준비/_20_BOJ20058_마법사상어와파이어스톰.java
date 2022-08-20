package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class _20_BOJ20058_마법사상어와파이어스톰 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int n, N, Q;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        N = (int)Math.pow(2, n);
        map = new int[N][N];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        st = new StringTokenizer(br.readLine(), " ");
        while(st.hasMoreTokens()){
            int k = Integer.parseInt(st.nextToken());
            divide((int)Math.pow(2, k), N, 0, 0);
            melt();
        }

        boolean[][] visited = new boolean[N][N];
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(visited[y][x] || map[y][x] == 0) continue;
                ans = Math.max(getArea(visited, y, x), ans);
            }
        }
        System.out.println(getSumIce());
        System.out.println(ans);
    }

    public static void divide(int target, int len, int y, int x) {
        if(target == len){
            rotation(y, x, len);
            return;
        }

        int half = len/2;
        divide(target, half, y, x);
        divide(target, half, y, x + half);
        divide(target, half, y + half, x);
        divide(target, half, y + half, x + half);
    }

    public static void rotation(int y, int x, int len) {
        List<Integer>[] tmpArr = new List[len];
        for(int row = 0; row < len; ++row){
            tmpArr[row] = new ArrayList<>();
        }

        for(int ny = y, idx = 0; ny < y + len; ++ny, ++idx){
            for(int nx = x; nx < x + len; ++nx){
                tmpArr[idx].add(map[ny][nx]);
            }
        }

        int arrIdx = len - 1;
        for(int nx = x; nx < x + len; ++nx){
            int elemIdx = 0;
            for(int ny = y; ny < y + len; ++ny){
                map[ny][nx] = tmpArr[arrIdx].get(elemIdx++);
            }
            arrIdx--;
        }
    }

    public static void melt() {
        List<int[]> list = new ArrayList<>();

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                int cnt = 4;
                if(map[y][x] == 0) continue;
                for(int d = 0; d < 4; ++d){
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] == 0) {
                        cnt--;
                        continue;
                    }
                }
                if(cnt < 3) list.add(new int[]{y, x});
            }
        }

        for(int[] arr : list){
            map[arr[0]][arr[1]]--;
        }
    }

    public static int getSumIce() {
        int sum = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                sum += map[y][x];
            }
        }
        return sum;
    }

    public static int getArea(boolean[][] visited, int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0){
                    continue;
                }

                q.offer(new int[]{ny, nx});
                visited[ny][nx] = true;
                ++cnt;
            }
        }
        return cnt;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
