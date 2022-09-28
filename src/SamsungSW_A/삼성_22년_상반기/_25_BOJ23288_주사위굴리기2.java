package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _25_BOJ23288_주사위굴리기2 {

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};

    private static int N, M, K, ans;
    private static int[][] map;
    private static int[] diceNum;
    private static Point locDice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ans = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        locDice = new Point(0, 0, 0);
        diceNum = new int[7];
        for(int i = 1; i < 7; ++i){
            diceNum[i] = i;
        }

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while(K-- > 0){
            int ny = locDice.y + dy[locDice.d];
            int nx = locDice.x + dx[locDice.d];
            if(!isIn(ny, nx)) {
                locDice.d = (locDice.d + 2) % 4;
                ++K;
                continue;
            }
            locDice.y = ny;
            locDice.x = nx;

            diceMove(locDice.d);
            getScore();
            locDice.d = getNextDir();
        }

        System.out.println(ans);
    }

    public static void diceMove(int d) {
        int tmp;
        if(d == 0){ // 동
            tmp = diceNum[3];
            diceNum[3] = diceNum[1];
            diceNum[1] = diceNum[4];
            diceNum[4] = diceNum[6];
            diceNum[6] = tmp;
        } else if(d == 1){ // 남
            tmp = diceNum[6];
            diceNum[6] = diceNum[5];
            diceNum[5] = diceNum[1];
            diceNum[1] = diceNum[2];
            diceNum[2] = tmp;
        } else if(d == 2) { // 서
            tmp = diceNum[4];
            diceNum[4] = diceNum[1];
            diceNum[1] = diceNum[3];
            diceNum[3] = diceNum[6];
            diceNum[6] = tmp;
        } else { // 북
            tmp = diceNum[2];
            diceNum[2] = diceNum[1];
            diceNum[1] = diceNum[5];
            diceNum[5] = diceNum[6];
            diceNum[6] = tmp;
        }
    }

    public static void getScore() {
        int y = locDice.y;
        int x = locDice.x;
        int B = map[y][x];
        int C = bfs(y, x);
        ans += B*C;
    }

    public static int bfs(int y, int x){
        int cnt = 1;
        int base = map[y][x];
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();

        visited[y][x] = true;
        q.offer(new int[]{y, x});

        while(!q.isEmpty()){
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];

                if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] != base) continue;

                ++cnt;
                visited[ny][nx] = true;
                q.offer(new int[]{ny, nx});
            }
        }

        return cnt;
    }

    public static int getNextDir() {
        int nd = locDice.d;
        int A = diceNum[6];
        int B = map[locDice.y][locDice.x];

        if(A > B){
            nd = (locDice.d + 1) % 4;
        } else if (A < B) {
            nd = (locDice.d + 3) % 4;
        }
        return nd;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static class Point {
        int y;
        int x;
        int d;
        public Point(int y, int x, int d){
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}
