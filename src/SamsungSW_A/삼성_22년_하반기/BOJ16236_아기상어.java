package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236_아기상어 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, ans;
    private static int[][] map;
    private static Fish shark;
    private static PriorityQueue<Fish> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        pq = new PriorityQueue<>();

        for(int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                int num =Integer.parseInt(st.nextToken());

                if(num == 9) shark = new Fish(y, x, 2);
                else map[y][x] = num;
            }
        }

        start();
        System.out.println(ans);
    }

    public static void start() {

        while(true) {

            ans += bfs();
            System.out.println("end: " + ans);
            if(pq.size() == 0) return;

            Fish next = pq.peek();

            shark.y = next.y;
            shark.x = next.x;
            map[shark.y][shark.x] = 0;
            shark.exp++;

            if(shark.exp == shark.size) {
                shark.size++;
                shark.exp = 0;
            }

            pq.clear();
        }
    }
    static int idx = 0;
    public static int bfs() {
        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[]{shark.y, shark.x});
        visited[shark.y][shark.x] = true;

        while(!q.isEmpty()) {
            ++time;
            int size = q.size();
            System.out.println(time);
            for(int s = 0; s < size; ++s) {
                int[] now = q.poll();
                for(int d = 0; d < 4; ++d) {
                    int ny = now[0] + dy[d];
                    int nx = now[1] + dx[d];

                    if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] > shark.size) continue;

                    if(map[ny][nx] != 0 && shark.size > map[ny][nx]) {
                        pq.offer(new Fish(ny, nx, 0));
                    }

                    visited[ny][nx] = true;
                    q.offer(new int[]{ny, nx});
//                    print(visited);
//                    ++idx;
//                    if(idx == 50) System.exit(0);
                }
            }
//            System.out.println(pq);
            if(pq.size() > 0) return time;
        }

        return 0;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Fish implements Comparable<Fish> {
        int y;
        int x;
        int size;
        int exp = 0;

        public Fish(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }

        @Override
        public int compareTo(Fish f) {
            if(this.y == f.y) return Integer.compare(this.x, f.x);
            return Integer.compare(this.y, f.y);
        }

        @Override
        public String toString() {
            return "y: " + y + ", x: " + x + ", map[y][x]: " + map[y][x];
        }
    }

    public static void print(boolean[][] visited) {
        System.out.println("==========================");
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x) {
                if(visited[y][x]) System.out.print("1");
                else System.out.print("0");
            }
            System.out.println();
        }
        System.out.println("==========================");
    }
}
