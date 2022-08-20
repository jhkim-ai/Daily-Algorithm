package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


public class BOJ18405_경쟁적전염 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, K, S, Y, X;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        S = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken()) - 1;
        X = Integer.parseInt(st.nextToken()) - 1;

        bfs();
        System.out.println(map[Y][X]);
    }

    public static void bfs(){
        Queue<Point> q = getVirusQueue();
        int time = 0;
        while(!q.isEmpty()){
            ++time;
            if(time > S) return;
            int size = q.size();
            for(int n = 0; n < size; ++n){
                Point now = q.poll();
                for(int d = 0; d < 4; ++d){
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] != 0) continue;

                    map[ny][nx] = now.idx;
                }
            }

            q = getVirusQueue();
        }
    }

    public static Queue<Point> getVirusQueue(){
        Queue<Point> q = new LinkedList<>();
        List<Point> tmpList = new ArrayList<>();

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(map[y][x] == 0 || visited[y][x]) continue;
                tmpList.add(new Point(y, x, map[y][x]));
                visited[y][x] = true;
            }
        }

        Collections.sort(tmpList);
        for(Point p : tmpList){
            q.offer(p);
        }
        return q;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point implements Comparable<Point>{
        int y;
        int x;
        int idx;

        public Point(int y, int x, int idx){
            this.y = y;
            this.x = x;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point p){
            return Integer.compare(this.idx, p.idx);
        }
    }
}
