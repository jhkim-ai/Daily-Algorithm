package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21609_상어중학교 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;
    private static List<BlockGroup> listBlockGroup;

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

        while(true) {
            searchBigGroup();
            if (listBlockGroup.size() == 0) break;
            getScore();
            getRid();
            runGravity();
            rotateCounterClockwise();
            runGravity();
        }

        System.out.println(ans);
    }

    public static void rotateCounterClockwise() {
        List<Integer> tmpList = new ArrayList<>();
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                tmpList.add(map[y][x]);
            }
        }

        int idx = 0;
        for(int x = 0; x < N; ++x) {
            for(int y = N-1; y >= 0; --y) {
                map[y][x] = tmpList.get(idx++);
            }
        }
    }

    public static void runGravity() {
        List<Integer> tmpList = new ArrayList<>();
        for(int x = 0; x < N; ++x) {
            tmpList.clear();
            int idx = 0;
            int base = N-1;
            for(int y = N-1; y >= 0; --y) {
                if(map[y][x] == -2) continue;
                if(map[y][x] == -1) {
                    for(int ny = base; ny > y; --ny){
                        if(tmpList.size() <= idx) map[ny][x] = -2;
                        else map[ny][x] = tmpList.get(idx++);
                    }
                    tmpList.clear();
                    base = y - 1;
                    idx = 0;
                    continue;
                };
                tmpList.add(map[y][x]);
            }

            idx = 0;
            for(int ny = base; ny >= 0; --ny) {
                if(map[ny][x] == -1) continue;
                if(tmpList.size() <= idx) map[ny][x] = -2;
                else map[ny][x] = tmpList.get(idx++);
            }
        }
    }

    public static void getRid() {
        BlockGroup selectedGroup = listBlockGroup.get(0);

        int y = selectedGroup.baseY;
        int x = selectedGroup.baseX;
        int baseColor = map[y][x];
        map[y][x] = -2;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        visited[y][x] = true;
        q.offer(new int[]{y, x});

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == -1) continue;

                if(map[ny][nx] == baseColor || map[ny][nx] == 0) {
                    map[ny][nx] = -2;
                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
                }
            }
        }
    }

    public static void getScore() {
        BlockGroup selectedGroup = listBlockGroup.get(0);
        ans += selectedGroup.size * selectedGroup.size;
    }

    public static void searchBigGroup() {
        boolean[][] visited = new boolean[N][N];
        listBlockGroup = new ArrayList<>();

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(map[y][x] <= 0 || visited[y][x]) continue;
                bfs(y, x, visited);
            }
        }

        Collections.sort(listBlockGroup);
    }

    public static void bfs(int y, int x, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> listRainbowLoc = new ArrayList<>();

        int baseColor = map[y][x];
        int cnt = 1;
        int cntRainbow = 0;

        visited[y][x] = true;
        q.offer(new int[]{y, x});

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d) {
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == -1) continue;

                if(map[ny][nx] == baseColor || map[ny][nx] == 0) {
                   ++cnt;
                   visited[ny][nx] = true;
                   q.offer(new int[]{ny, nx});
                   if(map[ny][nx] == 0) {
                       cntRainbow++;
                       listRainbowLoc.add(new int[]{ny, nx});
                   }
                }

            }
        }

        if(cnt != 1) listBlockGroup.add(new BlockGroup(y, x, cnt, cntRainbow));
        for(int[] rainbowLoc : listRainbowLoc) {
            visited[rainbowLoc[0]][rainbowLoc[1]] = false;
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class BlockGroup implements Comparable<BlockGroup> {
        int baseY;
        int baseX;
        int size;
        int cntRainbow;

        public BlockGroup(int baseY, int baseX, int size, int cntRainbow) {
            this.baseY = baseY;
            this.baseX = baseX;
            this.size = size;
            this.cntRainbow = cntRainbow;
        }

        @Override
        public int compareTo(BlockGroup other) {
            if(this.size == other.size) {
                if(this.cntRainbow == other.cntRainbow) {
                    if(this.baseY == other.baseY){
                        return Integer.compare(other.baseX, this.baseX);
                    }
                    return Integer.compare(other.baseY, this.baseY);
                }
                return Integer.compare(other.cntRainbow, this.cntRainbow);
            }
            return Integer.compare(other.size, this.size);
        }
    }

    public static void print() {
        System.out.println("====================");
        for(int y = 0; y < N; ++y){
            System.out.println(Arrays.toString(map[y]));
        }
        System.out.println("====================");
    }
}
