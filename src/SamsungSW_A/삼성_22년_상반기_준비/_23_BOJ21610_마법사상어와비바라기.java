package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _23_BOJ21610_마법사상어와비바라기 {

    private static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static int N, M;
    private static int[][] map;
    private static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        q = new LinkedList<>();

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        q.offer(new Point(N-2, 0));
        q.offer(new Point(N-2, 1));
        q.offer(new Point(N-1, 0));
        q.offer(new Point(N-1, 1));

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            move(d, s);
            rain();
            basketUp();
            createNewCloud();
        }

        System.out.println(getTotalBasket());
    }

    public static void move(int d, int s){
        int size = q.size();
        while(size-- > 0){
            Point now = q.poll();
            int ny = (now.y + dy[d] * s) % N;
            int nx = (now.x + dx[d] * s) % N;

            if(ny < 0) ny += N;
            if(nx < 0) nx += N;

            now.y = ny;
            now.x = nx;
            q.offer(now);
        }
    }

    public static void rain() {
        Iterator<Point> it = q.iterator();
        while(it.hasNext()){
            Point cloud = it.next();
            map[cloud.y][cloud.x]++;
        }
    }

    public static void basketUp() {
        Iterator<Point> it = q.iterator();
        while(it.hasNext()){
            int cntBasket = 0;
            Point cloud = it.next();

            for(int d = 1; d < 8; d += 2){
                int ny = cloud.y + dy[d];
                int nx = cloud.x + dx[d];

                if(!isIn(ny, nx) || map[ny][nx] == 0) continue;
                cntBasket++;
            }

            map[cloud.y][cloud.x] += cntBasket;
        }
    }

    public static void createNewCloud() {
        boolean[][] inThePast = new boolean[N][N];
        while(!q.isEmpty()){
            Point cloud = q.poll();
            inThePast[cloud.y][cloud.x] = true;
        }

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(map[y][x] < 2) continue;
                if(inThePast[y][x]) continue;
                map[y][x] -= 2;
                q.offer(new Point(y, x));
            }
        }
    }

    public static int getTotalBasket(){
        int sum = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                sum += map[y][x];
            }
        }
        return sum;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point {
        int y;
        int x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
