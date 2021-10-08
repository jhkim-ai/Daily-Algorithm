package SamsungSW_A.삼성_21년_하반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_프로세서_연결하기 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, ans, maxCore;
    private static int[][] map;
    private static List<Point> processes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            processes = new ArrayList<>();
            ans = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;

            for (int y = 0; y < N; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < N; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if (map[y][x] == 1 && !isEdge(y, x)) {
                        processes.add(new Point(y, x));
                    }
                }
            }
            int size = processes.size();
            permutation(size, new int[size]);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static void permutation(int cnt, int[] selected) {
        if (cnt == 0) {
            int count = 0;
            int core = 0;
            int[][] copyMap = new int[N][N];
            for (int y = 0; y < N; ++y) {
                copyMap[y] = map[y].clone();
            }

            for (int i = 0; i < processes.size(); ++i) {
                if(selected[i] == 4){
                    continue;
                }
                ++core;
                Point process = processes.get(i);
                int y = process.y;
                int x = process.x;
                int d = selected[i];
                if (isEdge(y, x)){ // 가장자리에 있다면, pass
                    continue;
                }
                while(true){
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    // 경계를 넘었다는 것은 계속 가장자리까지 쭉 연결이 되었다는 의미. 다음 프로세스 진행
                    if(!isIn(ny, nx)){
                        break;
                    }
                    // 0이 아니라는 것은 process 가 있거나 선이 겹친다는 의미(불가능한 경우)
                    if(copyMap[ny][nx] != 0){
                        return;
                    }
                    count++;
                    copyMap[ny][nx] = 2;
                    y = ny;
                    x = nx;
                }
            }

            if(core >= maxCore) {
                maxCore = core;
                ans = Math.min(count, ans);
            }
            return;
        }

        for (int d = 0; d < 5; d++) {
            selected[selected.length - cnt] = d;
            permutation(cnt - 1, selected);
        }

    }

    public static boolean isEdge(int y, int x) {
        return y == 0 || y == N - 1 || x == 0 || x == N - 1;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
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




