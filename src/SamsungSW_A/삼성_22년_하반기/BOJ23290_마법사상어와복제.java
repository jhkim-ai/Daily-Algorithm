package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ23290_마법사상어와복제 {

    private static final int cntDir = 8;

    private static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static final int[] sharkDy = {-1, 0, 1, 0};
    private static final int[] sharkDx = {0, -1, 0, 1};

    private static int N, M, S;
    private static int cntPrey;

    private static int[] sharkRoute;
    private static int[][] cntDeath;
    private static Queue<Fish> ready;
    private static Queue<Fish> death;
    private static Queue<Fish>[][] originMap;
    private static Queue<Fish>[][] afterMoveMap;

    private static Fish shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = 4;
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        cntDeath = new int[N][N];
        ready = new LinkedList<>();
        death = new LinkedList<>();
        originMap = new Queue[N][N];
        afterMoveMap = new Queue[N][N];

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                originMap[y][x] = new LinkedList<>();
                afterMoveMap[y][x] = new LinkedList<>();
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            originMap[y][x].add(new Fish(y, x, d));
        }

        st = new StringTokenizer(br.readLine(), " ");
        int sharkY = Integer.parseInt(st.nextToken()) - 1;
        int sharkX = Integer.parseInt(st.nextToken()) - 1;

        shark = new Fish(sharkY, sharkX, -1);

        while(S-- > 0) {
            cntPrey = -1;
            addDeathFishAge();
            getCopyFish();
            moveFish();
            moveShark();
            deleteDeathFish();
            setCopyFish();
        }

        System.out.println(getFishCnt());
    }

    public static void deleteDeathFish() {
        int size = death.size();
        while(size-- > 0) {
            Fish deathFish = death.poll();

            if(deathFish.age == 2) {
                cntDeath[deathFish.y][deathFish.x]--;
                continue;
            }

            death.offer(deathFish);
        }
    }

    public static void moveShark() {
        setSharkRoute(3, new int[3]);

        int ny = shark.y;
        int nx = shark.x;

        for(int d : sharkRoute) {
            ny = ny + sharkDy[d];
            nx = nx + sharkDx[d];

            while(!originMap[ny][nx].isEmpty()) {
                Fish deadFish = originMap[ny][nx].poll();
                death.offer(deadFish);
                cntDeath[ny][nx]++;
            }
        }

        shark.y = ny;
        shark.x = nx;
    }

    public static void setSharkRoute(int cnt, int[] selected) {
        if(cnt == 0) {
            int ny = shark.y;
            int nx = shark.x;
            int tmpCntPrey = 0;
            boolean[][] isEat = new boolean[N][N];

            for(int d : selected) {
                ny = ny + sharkDy[d];
                nx = nx + sharkDx[d];

                if(!isIn(ny, nx)) return;

                if(isEat[ny][nx]) continue;
                tmpCntPrey += originMap[ny][nx].size();
                isEat[ny][nx] = true;
            }

            if(tmpCntPrey > cntPrey) {
                cntPrey = tmpCntPrey;
                sharkRoute = selected.clone();
            }

            return;
        }

        for(int d = 0; d < 4; ++d) {
            selected[selected.length - cnt] = d;
            setSharkRoute(cnt-1, selected);
        }
    }

    public static void moveFish() {
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {

                move:
                while(!originMap[y][x].isEmpty()) {
                    Fish fish = originMap[y][x].poll();

                    for(int d = 0; d < cntDir; ++d) {
                        int nd = ((fish.d - d) % cntDir + cntDir) % cntDir;
                        int ny = fish.y + dy[nd];
                        int nx = fish.x + dx[nd];

                        if(!isIn(ny, nx) || isShark(ny, nx) || isDeath(ny, nx)) continue;

                        afterMoveMap[ny][nx].offer(new Fish(ny, nx, nd));
                        continue move;
                    }

                    afterMoveMap[y][x].offer(fish);
                }
            }
        }

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                originMap[y][x].clear();
                while(!afterMoveMap[y][x].isEmpty()) {
                    originMap[y][x].offer(afterMoveMap[y][x].poll());
                }
            }
        }
    }

    public static void getCopyFish() {
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                for(Fish fish : originMap[y][x]) {
                    ready.offer(new Fish(y, x, fish.d));
                }
            }
        }
    }

    public static void setCopyFish() {
        while(!ready.isEmpty()) {
            Fish fish = ready.poll();
            originMap[fish.y][fish.x].offer(fish);
        }
    }

    public static void addDeathFishAge() {

        if(death.isEmpty()) return;

        Iterator<Fish> iterator = death.iterator();
        while(iterator.hasNext()){
            Fish fish = iterator.next();
            fish.age++;
        }
    }

    public static int getFishCnt() {
        int sum = 0;
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                sum += originMap[y][x].size();
            }
        }
        return sum;
    }

    public static boolean isDeath(int y, int x) {
        return cntDeath[y][x] > 0;
    }

    public static boolean isShark(int y, int x) {
        return shark.y == y && shark.x == x;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Fish {
        int y;
        int x;
        int d;
        int age = 0;

        public Fish(int y, int x, int d) {
            this.y = y;
            this.x = x;
            this.d = d;
        }
    }
}
