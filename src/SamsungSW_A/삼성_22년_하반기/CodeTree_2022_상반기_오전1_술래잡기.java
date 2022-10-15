package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree_2022_상반기_오전1_술래잡기 {
    private static final int DIR_CNT = 4;
    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M, H, K;
    private static int nowTurn, score;

    private static boolean clockwise;
    private static boolean[][] isTree;
    private static boolean[][] seekerVisited;

    private static Person seeker;
    private static Queue<Person>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nowTurn = 0;
        score = 0;
        map = new Queue[N][N];
        isTree = new boolean[N][N];
        clockwise = true;
        seeker = new Person(N/2, N/2, 0);

        for(int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                map[y][x] = new LinkedList<>();
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            map[y][x].offer(new Person(y, x, d));
        }

        while(H-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            isTree[y][x] = true;
        }

        for(int t = 1; t <= K; ++t){
            nowTurn = t;
            moveHider();
            moveSeeker();
            catchHider();
        }

        System.out.println(score);
    }
    public static void moveHider() {

        Queue<Person>[][] tmpQ = makeTmpQ();

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                int len = Math.abs(seeker.y - y) + Math.abs(seeker.x - x);
                if(len > 3) {
                    while(!map[y][x].isEmpty()){
                        tmpQ[y][x].offer(map[y][x].poll());
                    }
                    continue;
                }

                while(!map[y][x].isEmpty()) {
                    Person hider = map[y][x].poll();
                    int ny = hider.y + dy[hider.d];
                    int nx = hider.x + dx[hider.d];

                    if(!isIn(ny, nx)) {
                        hider.d = (hider.d + 2) % DIR_CNT;
                        ny = hider.y + dy[hider.d];
                        nx = hider.x + dx[hider.d];
                    }

                    if(isSeeker(ny, nx)) {
                        tmpQ[hider.y][hider.x].offer(hider);
                        continue;
                    }

                    hider.y = ny;
                    hider.x = nx;
                    tmpQ[ny][nx].offer(hider);
                }
            }
        }

        map = tmpQ;
    }

    public static void catchHider() {
        int y = seeker.y;
        int x = seeker.x;
        int d = seeker.d;
        int catchCount = 0;

        for(int len = 0; len < 3; ++len) {
            int ny = y + dy[d] * len;
            int nx = x + dx[d] * len;

            if(!isIn(ny, nx)) break;
            if(isTree[ny][nx] || map[ny][nx].isEmpty()) continue;

            catchCount += map[ny][nx].size();
            map[ny][nx].clear();
        }

        score += nowTurn * catchCount;
    }

    public static void moveSeeker() {
        if(clockwise) clockwise();
        else counterClockwise();

        if(seeker.y == 0 && seeker.x == 0) {
            clockwise = !clockwise;
            seeker.d = 2;
            seekerVisited = new boolean[N][N];
            seekerVisited[0][0] = true;
        }

        if(seeker.y == N/2 && seeker.x == N/2) {
            clockwise = !clockwise;
            seeker.d = 0;
            seeker.len = 1;
            seeker.cntLen = 0;
            seeker.cntMove = 0;
        }
    }

    public static void clockwise() {
        seeker.y += dy[seeker.d];
        seeker.x += dx[seeker.d];
        seeker.cntMove++;

        if(seeker.cntMove == seeker.len) {
            seeker.cntMove = 0;
            seeker.cntLen++;

            if(seeker.cntLen == 2) {
                seeker.cntLen = 0;
                seeker.len++;
            }

            seeker.d = (seeker.d + 1) % DIR_CNT;
        }
    }

    public static void counterClockwise() {
        int ny = seeker.y + dy[seeker.d];
        int nx = seeker.x + dx[seeker.d];

        seeker.y = ny;
        seeker.x = nx;
        seekerVisited[ny][nx] = true;

        ny = seeker.y + dy[seeker.d];
        nx = seeker.x + dx[seeker.d];

        if(!isIn(ny, nx) || seekerVisited[ny][nx]) {
            seeker.d = (seeker.d + 3) % DIR_CNT;
        }
    }

    public static Queue<Person>[][] makeTmpQ() {
        Queue<Person>[][] tmpQ = new Queue[N][N];
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                tmpQ[y][x] = new LinkedList<>();
            }
        }

        return tmpQ;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static boolean isSeeker(int y, int x) {
        return seeker.y == y && seeker.x == x;
    }

    public static class Person {
        int y;
        int x;
        int d;
        int len = 1;
        int cntLen = 0;
        int cntMove = 0;


        public Person(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}
