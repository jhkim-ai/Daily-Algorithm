package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class _22_BOJ21609_상어중학교 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ans = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            int[] baseBlock = getBigBlockGroup();
            if(baseBlock[0] == -1) break;
            deleteBlockGroup(baseBlock);
            gravity();
            counterClockwise();
            gravity();
        }

        System.out.println(ans);
    }

    public static int[] getBigBlockGroup(){
        int baseY = -1;
        int baseX = -1;
        int cnt = 0;
        int rainbowBlock = 0;
        visited = new boolean[N][N];
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(visited[y][x] || map[y][x] <= 0) continue;
                if(map[y][x] > 0) {
                    visited[y][x] = true;
                    int[] getCntInfo = bfs(y, x);
                    if(cnt < getCntInfo[0]) {
                        cnt = getCntInfo[0];
                        rainbowBlock = getCntInfo[1];
                        baseY = y;
                        baseX = x;
                    } else if(cnt == getCntInfo[0]){
                        if(rainbowBlock < getCntInfo[1]){
                            baseY = y;
                            baseX = x;
                            rainbowBlock = getCntInfo[1];
                        } else if(rainbowBlock == getCntInfo[1]){
                            baseY = y;
                            baseX = x;
                        }
                    }
                }
            }
        }
        ans += cnt * cnt;
        return new int[]{baseY, baseX};
    }

    public static int[] bfs(int y, int x) {
        int cnt = 1;
        int rainbowBlock = 0;
        int base = map[y][x];
        boolean[][] tmpVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        tmpVisited[y][x] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if(!isIn(ny, nx) || tmpVisited[ny][nx] || map[ny][nx] == -1) continue;
                if(map[ny][nx] == 0 || map[ny][nx] == base) {
                    q.offer(new int[]{ny, nx});
                    tmpVisited[ny][nx] = true;
                    ++cnt;
                    if(map[ny][nx] == 0) rainbowBlock++;
                    if (map[ny][nx] > 0)
                        visited[ny][nx] = true;
                }
            }
        }

        if(cnt <= 1) return new int[]{-1, -1};

        return new int[]{cnt, rainbowBlock};
    }

    public static void deleteBlockGroup(int[]baseBlock) {
        int y = baseBlock[0];
        int x = baseBlock[1];
        int base = map[y][x];
        boolean[][] tmpVisited = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        tmpVisited[y][x] = true;
        map[y][x] = -2;

        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if(!isIn(ny, nx) || tmpVisited[ny][nx] || map[ny][nx] == -1) continue;
                if(map[ny][nx] == 0 || map[ny][nx] == base) {
                    q.offer(new int[]{ny, nx});
                    tmpVisited[ny][nx] = true;
                    map[ny][nx] = -2;
                }
            }
        }
    }

    public static void gravity() {
        Stack<Integer> stack = new Stack<>();
        for(int x = 0; x < N; ++x){
            stack.clear();
            for(int y = 0; y < N; ++y){
                if(map[y][x] >= 0) {
                    stack.push(map[y][x]);
                    map[y][x] = -2;
                } else if(map[y][x] == -1){
                    int idx = y-1;
                    while(!stack.isEmpty()){
                        map[idx--][x] = stack.pop();
                    }
                }
            }
            if(!stack.isEmpty()){
                int idx = N-1;
                while(!stack.isEmpty()){
                    map[idx][x] = stack.pop();
                    idx--;
                }
            }
        }
    }

    public static void counterClockwise() {
        List<Integer>[] rowList = new List[N];
        for(int y = 0; y < N; ++y){
            rowList[y] = new ArrayList<>();
        }
        for(int y = 0; y < N; ++y){
            for(int x = N-1; x >= 0; --x){
                rowList[y].add(map[y][x]);
            }
        }
        for(int x = 0; x < N; ++x) {
            for(int y = 0; y < N; ++y){
                map[y][x] = rowList[x].get(y);
            }
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static int[][] getCopyMap(){
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }
        return copyMap;
    }
}
