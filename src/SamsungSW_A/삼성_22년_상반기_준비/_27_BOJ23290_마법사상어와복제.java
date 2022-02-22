package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _27_BOJ23290_마법사상어와복제 {

    private static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static final int[] sy = {-1, 0, 1, 0};
    private static final int[] sx = {0, -1, 0, 1};

    private static int N, M, S;
    private static int gCntFeed;
    private static int[] selectedSharkMove;
    private static Queue<Point>[][] fishMap;
    private static Queue<Point>[][] deathMap;
    private static Queue<Point>[][] tmpDuplMap;
    private static Queue<Point>[][] tmpFishMoveMap;
    private static Point shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = 4;
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        fishMap = new Queue[N][N];
        deathMap = new Queue[N][N];
        tmpDuplMap = new Queue[N][N];
        tmpFishMoveMap = new Queue[N][N];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                fishMap[y][x] = new LinkedList<>();
                deathMap[y][x] = new LinkedList<>();
                tmpDuplMap[y][x] = new LinkedList<>();
                tmpFishMoveMap[y][x] = new LinkedList<>();
            }
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            fishMap[y][x].offer(new Point(y, x, d));
        }

        st = new StringTokenizer(br.readLine());
        int sharkY = Integer.parseInt(st.nextToken()) - 1;
        int sharkX = Integer.parseInt(st.nextToken()) - 1;
        shark = new Point(sharkY, sharkX);
        while(S-- > 0) {
            upDeathFishCnt();
//            deathPrint();

            readyDuplicateFish();
//            tmpPrint();

            gCntFeed = Integer.MIN_VALUE;
            moveFish();
//            fishPrint();

//            sharkPrint();
            moveShark(3, new int[3]);

            deathAllFish();
//            sharkPrint();
//            fishPrint();

            deleteDeathFish();
//            deathPrint();

            doDuplicateFish();
//            fishPrint();
        }
        System.out.println(getFishCnt());
    }


    // 물고기 알 낳기
    public static void readyDuplicateFish() {
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                Iterator<Point> it = fishMap[y][x].iterator();
                while (it.hasNext()) {
                    Point now = it.next();
                    tmpDuplMap[y][x].offer(new Point(y, x, now.d));
                }
            }
        }
    }

    // 알에서 부화하기
    public static void doDuplicateFish() {
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(tmpDuplMap[y][x].size() == 0) continue;
                Queue<Point> qTmp = tmpDuplMap[y][x];
                while(!qTmp.isEmpty()){
                    Point now = qTmp.poll();
                    fishMap[y][x].offer(new Point(now.y, now.x, now.d));
                }
            }
        }
    }

    // 물고기 이동하기
    public static void moveFish() {
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                Queue<Point> qFish = fishMap[y][x];
                int size = qFish.size();
                for (int s = 0; s < size; ++s) {
                    Point now = qFish.poll();
                    boolean isMove = false;
                    for (int d = 8; d > 0; --d) {
                        int nd = (now.d + d) % 8;
                        int ny = now.y + dy[nd];
                        int nx = now.x + dx[nd];
                        if(!isIn(ny ,nx) || isShark(ny, nx) || deathMap[ny][nx].size() != 0) continue;

                        isMove = true;
                        now.y = ny;
                        now.x = nx;
                        now.d = nd;
                        tmpFishMoveMap[ny][nx].offer(now); // 내가 놓친 부분: 임시 저장소를 만들지 않고 원본에 넣어서 이동된 물고기가 또 움직임
                        break;
                    }
                    if(!isMove) tmpFishMoveMap[now.y][now.x].offer(now);
                }
            }
        }

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                while(!tmpFishMoveMap[y][x].isEmpty()){
                    Point now = tmpFishMoveMap[y][x].poll();
                    fishMap[now.y][now.x].offer(now);
                }
            }
        }
    }

    // 상어 이동 루트 구하기
    public static void moveShark(int cnt, int[] selected) {
        if (cnt == 0) {
            boolean[][] visited = new boolean[N][N];
            int cntFeed = 0;
            int y = shark.y;
            int x = shark.x;
            for (int d : selected) {
                y = y + sy[d];
                x = x + sx[d];

                if (!isIn(y, x)) return;

                if(!visited[y][x]) { // 내가 놓쳤던 부분
                    visited[y][x] = true;
                    cntFeed += fishMap[y][x].size();
                }
            }

            if(cntFeed > gCntFeed){
//                System.out.println("cntFeed: " + cntFeed + ", gCntFeed: " + gCntFeed);
                gCntFeed = cntFeed;
                selectedSharkMove = selected.clone();
//                System.out.println(Arrays.toString(selectedSharkMove));
            }
            return;
        }

        for (int d = 0; d < 4; ++d) {
            selected[selected.length - cnt] = d;
            moveShark(cnt - 1, selected);
        }
    }

    // 상어의 이동 루트를 구했다면, 해당 루트의 물고기를 잡아 먹기
    public static void deathAllFish() {
        int y = shark.y;
        int x = shark.x;

        for(int d : selectedSharkMove){
            y = y + sy[d];
            x = x + sx[d];
            int size = fishMap[y][x].size();
            while(size-- > 0){
                deathMap[y][x].offer(new Point(0));
            }
            fishMap[y][x].clear();
        }

        shark.y = y;
        shark.x = x;
    }

    // 2년지난 물고기는 삭제
    public static void deleteDeathFish() {
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                Queue<Point> qDeath = deathMap[y][x];
                int size = qDeath.size();
                while(size-- > 0){
                    Point now = qDeath.poll();
                    if(now.age != 2) qDeath.offer(now);
                }
            }
        }
    }

    // 죽은 물꼬기의 age 계산
    public static void upDeathFishCnt() {
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(deathMap[y][x].size() == 0) continue;
                Iterator<Point> it = deathMap[y][x].iterator();
                while (it.hasNext()){
                    Point now = it.next();
                    now.age++;
                }
            }
        }
    }

    // 남은 물고기 수 구하기기
   public static int getFishCnt(){
        int cnt = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                cnt += fishMap[y][x].size();
            }
        }
        return cnt;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 & x >= 0 && y < N && x < N;
    }

    public static boolean isShark(int y, int x){
        return y == shark.y && x == shark.x;
    }

    public static class Point {

        int y;
        int x;
        int d;
        int age;

        public Point(int y, int x) { // 상어
            this.y = y;
            this.x = x;
        }

        public Point(int y, int x, int d) { // 물고기
            this.y = y;
            this.x = x;
            this.d = d;
        }

        public Point(int age) { // 시체
            this.age = age;
        }

        @Override
        public String toString() {
            return String.valueOf(d);
        }
    }

    public static void fishPrint() {
        System.out.println("fishPrint: =================");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if(fishMap[y][x].size() == 0) System.out.print("0, ");
                else {
                    System.out.print("(");
                    Iterator<Point> it = fishMap[y][x].iterator();
                    while (it.hasNext()) {
                        Point now = it.next();
                        System.out.print(now.d + ", ");
                    }
                    System.out.print(")");
                }
            }
            System.out.println();
        }
    }

    public static void sharkPrint() {
        System.out.println("sharkPrint: =================");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if(isShark(y, x)) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
    }

    public static void deathPrint() {
        System.out.println("deathPrint: =================");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if(deathMap[y][x].size() == 0) System.out.print("-1, ");
                else {
                    System.out.print("(");
                    Iterator<Point> it = deathMap[y][x].iterator();
                    while (it.hasNext()) {
                        Point now = it.next();
                        System.out.print(now.age + ", ");
                    }
                    System.out.print(")");
                }
            }
            System.out.println();
        }
    }

    public static void tmpPrint() {
        System.out.println("tmpPrint: =================");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if(tmpDuplMap[y][x].size() == 0) System.out.print("0, ");
                else {
                    System.out.print("(");
                    Iterator<Point> it = tmpDuplMap[y][x].iterator();
                    while (it.hasNext()) {
                        Point now = it.next();
                        System.out.print(now.d + ", ");
                    }
                    System.out.print(")");
                }
            }
            System.out.println();
        }
    }
}
