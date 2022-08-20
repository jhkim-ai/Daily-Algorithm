package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 63% 에서 틀리는 경우는 모두가 0 일 때
public class _23_BOJ21611_마법사상어와블리자드 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static final int[] moveY = {0, 1, 0, -1};
    private static final int[] moveX = {-1, 0, 1, 0};

    private static int N, M, ans;
    private static int[][] map;
    private static Map<Integer, Point> locMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        locMap = new HashMap<>();
        ans = 0;

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        init();
        while(M-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            blizzard(d, s);
            move();
            while(true){
                if(!searchBombArea()) break;
                move();
            }
            transition();
        }
        System.out.println(ans);
    }

    public static void init() {
        int d = 0;
        int idx = 1;
        int size = 1;
        int y = N / 2;
        int x = N / 2;

        outer:
        while(true){
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(!isIn(y, x)) break outer;
                locMap.put(idx++, new Point(y, x));
            }
            d = (d + 1) % 4;
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                locMap.put(idx++, new Point(y, x));
            }
            d = (d + 1) % 4;
            ++size;
        }
    }

    public static void blizzard(int d, int s) {
        int startY = N / 2;
        int startX = N / 2;

        for(int len = 1; len <= s; ++len){
            int ny = startY + dy[d] * len;
            int nx = startX + dx[d] * len;
            map[ny][nx] = 0;
        }
    }

    public static void move() {
        int d = 0;
        int idx = 0;
        int size = 1;
        int y = N / 2;
        int x = N / 2;
        List<Integer> tmpStorage = getTmpStorage();
        int storageSize = tmpStorage.size();

        outer:
        while(true){
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(!isIn(y, x) || idx == storageSize) break outer;
                map[y][x] = tmpStorage.get(idx++);
            }
            d = (d + 1) % 4;
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(idx == storageSize) break outer;
                map[y][x] = tmpStorage.get(idx++);
            }
            d = (d + 1) % 4;
            ++size;
        }
    }

    public static boolean searchBombArea() {
        int d = 0;
        int idx = 0;
        int size = 1;
        int y = N / 2;
        int x = N / 2;
        int cnt = 1;
        int num = 0;
        int startIdx = 0;
        boolean isBomb = false;

        outer:
        while(true){
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                ++idx;
                if(!isIn(y, x) || map[y][x] == 0) break outer;
                if(num != map[y][x]){
                    if(cnt >= 4){
                        bomb(startIdx, cnt, num);
                        isBomb = true;
                    }
                    startIdx = idx;
                    num = map[y][x];
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            d = (d + 1) % 4;
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                ++idx;
                if(map[y][x] == 0) break outer;
                if(num != map[y][x]){
                    if(cnt >= 4){
                        bomb(startIdx, cnt, num);
                        isBomb = true;
                    }
                    startIdx = idx;
                    num = map[y][x];
                    cnt = 1;
                } else {
                    cnt++;
                }
            }
            d = (d + 1) % 4;
            ++size;
        }

        if(cnt >= 4){
            bomb(startIdx, cnt, num);
            isBomb = true;
        }

        return isBomb;
    }

    public static void bomb(int startIdx, int cnt, int num) {
        ans += num * cnt;

        for(int i = startIdx; i < startIdx + cnt; ++i){
            Point p = locMap.get(i);
            map[p.y][p.x] = 0;
        }
    }

    public static void transition() {
        int d = 0;
        int size = 1;
        int cnt = 1;
        int num = 0;
        int y = N / 2;
        int x = N / 2;
        List<Integer> tmpStorage = getTmpStorage();
        Queue<Integer> q = new LinkedList<>();

        for (int beadNum : tmpStorage) {
            if (num != beadNum) {
                if (num != 0) {
                    q.offer(cnt);
                    q.offer(num);
                }
                num = beadNum;
                cnt = 1;
            } else {
                cnt++;
            }
        }

        if (num != 0) {
            q.offer(cnt);
            q.offer(num);
        }

        outer:
        while(true){
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(!isIn(y, x) || q.isEmpty()) break outer;
                map[y][x] = q.poll();
            }
            d = (d + 1) % 4;
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(q.isEmpty()) break outer;
                map[y][x] = q.poll();
            }
            d = (d + 1) % 4;
            ++size;
        }
    }

    public static ArrayList<Integer> getTmpStorage() {
        int d = 0;
        int size = 1;
        int y = N / 2;
        int x = N / 2;
        ArrayList<Integer> tmpStorage = new ArrayList<>();

        outer:
        while(true){
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(!isIn(y, x)) break outer;
                if(map[y][x] == 0) continue;
                tmpStorage.add(map[y][x]);
                map[y][x] = 0;
            }
            d = (d + 1) % 4;
            for(int i = 0; i < size; ++i){
                y = y + moveY[d];
                x = x + moveX[d];
                if(map[y][x] == 0) continue;
                tmpStorage.add(map[y][x]);
                map[y][x] = 0;
            }
            d = (d + 1) % 4;
            ++size;
        }

        return tmpStorage;
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
