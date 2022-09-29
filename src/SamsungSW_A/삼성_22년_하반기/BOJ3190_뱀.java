package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3190_뱀 {

    private static final int Y = 0;
    private static final int X = 1;

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};


    private static int N, K, L, ans;
    private static int dir;
    private static int[] head, tail;
    private static int[][] map, dirMap;

    private static char[] commands;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dirMap = new int[N][N];
        for(int y = 0; y < N; ++y){
            Arrays.fill(dirMap[y], -1);
        }

        K = Integer.parseInt(br.readLine());
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = -1;
        }

        L = Integer.parseInt(br.readLine());
        commands = new char[10001];
        while(L-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            commands[time] = c;
        }

        dir = 1;
        start();
        System.out.println(ans);
    }

    public static void start() {
        head = new int[]{0, 0};
        tail = new int[]{0, 0};

        int len = 1;
        map[0][0] = len;
        dirMap[0][0] = dir;
        ans= 0;

        int nextHeadY;
        int nextHeadX;

        while(true) {
            ++ans;
            nextHeadY = head[Y] + dy[dir];
            nextHeadX = head[X] + dx[dir];

            if(!isIn(nextHeadY, nextHeadX) || isMe(nextHeadY, nextHeadX)) break;

            ++len;
            head[Y] = nextHeadY;
            head[X] = nextHeadX;

            if(map[head[Y]][head[X]] == -1){
                map[head[Y]][head[X]] = len;
                changeDir();
                continue;
            }

            map[head[Y]][head[X]] = len;

            int tailDir = dirMap[tail[Y]][tail[X]];

            map[tail[Y]][tail[X]] = 0;
            dirMap[tail[Y]][tail[X]] = -1;

            tail[Y] = tail[Y] + dy[tailDir];
            tail[X] = tail[X] + dx[tailDir];

            changeDir();
        }
    }

    public static void changeDir() {
        char command = commands[ans];

        if(command == 'L') dir = (dir + 3) % 4;
        else if(command == 'D') dir = (dir + 1) % 4;

        dirMap[head[Y]][head[X]] = dir;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static boolean isMe(int y, int x){
        return map[y][x] > 0;
    }
}
