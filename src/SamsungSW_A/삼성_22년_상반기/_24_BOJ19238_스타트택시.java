package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class _24_BOJ19238_스타트택시 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, L;
    private static int[][] map;
    private static Set[][] setDestination;
    private static Point taxi;
    private static Set<Integer> setGuest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        setDestination = new Set[N][N];
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                setDestination[y][x] = new HashSet<Integer>();
            }
        }
        setGuest = new HashSet<>();

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                int id = Integer.parseInt(st.nextToken());
                map[y][x] = id;
                setDestination[y][x].add(id);
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = M + 2;

            y = Integer.parseInt(st.nextToken()) - 1;
            x = Integer.parseInt(st.nextToken()) - 1;
            setDestination[y][x].add(M + 2);
            setGuest.add(M+2);
        }

        int ans = run();
        System.out.println(ans);
    }

    public static int run() {
        int size = setGuest.size();
//        printGuest();
//        printDest();
        for(int i = 0; i < size; ++i){
            Point guest = findGuest();
            if(guest == null) return -1;
            L -= guest.s;

            int fuel = move(guest);
            if(fuel == -1) return -1;
            L -= fuel;

            if(L < 0) return -1;
            L += fuel * 2;
//            printGuest();
//            printDest();
        }

        if(!setGuest.isEmpty()) return -1;
        return L;
    }

    public static Point findGuest() {

        if(map[taxi.y][taxi.x] != 0){
            return new Point(taxi.y, taxi.x, 0, map[taxi.y][taxi.x]);
        }

        int len = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        PriorityQueue<Point> pq = new PriorityQueue<>();

        q.offer(new Point(taxi.y, taxi.x));
        visited[taxi.y][taxi.x] = true;

        while(!q.isEmpty()) {
            ++len;
            int size = q.size();
            for(int s = 0; s < size; ++s) {
                Point now = q.poll();
                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if (!isIn(ny, nx) || isWall(ny, nx) || visited[ny][nx]) continue;

                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                    if(map[ny][nx] != 0) pq.offer(new Point(ny, nx, len, map[ny][nx]));
                }
            }

            if(!pq.isEmpty()) break;
        }
        if(pq.isEmpty()) return null;
        return pq.poll();
    }

    public static int move(Point guest) {
        int len = 0;
        int endId = guest.id;

        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        visited[guest.y][guest.x] = true;
        q.offer(guest);

        while(!q.isEmpty()) {
            ++len;
            int size = q.size();
            for(int s = 0; s < size; ++s){
                Point now = q.poll();
                for(int d = 0; d < 4; ++d){
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || isWall(ny, nx) || visited[ny][nx]) continue;

                    if(setDestination[ny][nx].contains(endId)) {
                        map[guest.y][guest.x] = 0;
                        setDestination[ny][nx].remove(endId);
                        taxi.y = ny;
                        taxi.x = nx;
                        setGuest.remove(guest.id);
                        return len;
                    }

                    q.offer(new Point(ny, nx));
                    visited[ny][nx] = true;
                }
            }
        }

        return -1;
    }

    public static boolean isWall(int y, int x){
        return map[y][x] == 1;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point implements Comparable<Point>{
        int y;
        int x;
        int s;
        int id;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int s, int id){
            this.y = y;
            this.x = x;
            this.s = s;
            this.id = id;
        }

        @Override
        public int compareTo(Point p){
            if(this.s == p.s){
                if(this.y == p.y){
                    return Integer.compare(this.x, p.x);
                }
                return Integer.compare(this.y, p.y);
            }
            return Integer.compare(this.s, p.s);
        }

    }

    public static void printGuest() {
        System.out.println("================");
        for(int y = 0; y < N; ++y) {
            System.out.println(Arrays.toString(map[y]));
        }
    }

    public static void printDest() {
        System.out.println("================");
        for(int y = 0; y < N; ++y) {
            System.out.println(Arrays.toString(setDestination[y]));
        }
    }
}
