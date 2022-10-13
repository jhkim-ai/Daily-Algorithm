package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ23291_어항정리 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, K, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; ++i) {
            map[0][i] = Integer.parseInt(st.nextToken());
        }

        while(true){
            ++ans;
            addFish();
            System.out.println("물고기 추가");
            print(map);

            int cnt = 2;
            while (true) {
                if (!stackFishbowl(cnt / 2)) break;
                ++cnt;
            }

            resetCntFish();
            System.out.println("물고기 조절");
            print(map);
            setLine();
            System.out.println("줄서기");
            print(map);

            firstFold(N / 2);
            System.out.println("한 번 접기");
            print(map);
            secondFold(N / 4);
            System.out.println("두 번 접기");
            print(map);
            resetCntFish();
            System.out.println("물고기 조절");
            print(map);
            setLine();
            System.out.println("줄서기");
            print(map);

        if (findFish()) break;
        }
        print(map);
        System.out.println(ans);
    }
    public static void secondFold(int len) {
        int[][] tmpMap = new int[N][N];
        Stack<Integer> stack = new Stack<>();
        List<Integer> tmpList = new ArrayList<>();

        for(int y = 0; y < 2; ++y) {
            for(int x = 0; x < len; ++x) {
                stack.add(map[y][x]);
            }
        }

        for(int y = 0; y < 2; ++y) {
            for(int x = 0; x < len; ++x) {
                tmpMap[y][x] = stack.pop();
            }
        }

        for(int y = 0; y < 2; ++y) {
            for(int x = len; x < len*2; ++x) {
                tmpList.add(map[y][x]);
            }
        }

        int idx = 0;
        for(int y = 2; y < 4; ++y) {
            for(int x = 0; x < len; ++x) {
                tmpMap[y][x] = tmpList.get(idx++);
            }
        }

        for(int y = 0; y < N; ++y) {
            map[y] = tmpMap[y].clone();
        }
    }
    public static void firstFold(int len) {
        int[][] tmpMap = new int[N][N];
        Stack<Integer> stack = new Stack<>();

        for(int x = 0; x < len; ++x) {
            stack.add(map[0][x]);
        }

        for(int x = 0; x < len; ++x) {
            tmpMap[0][x] = stack.pop();
        }

        for(int x = len; x < N; ++x) {
            tmpMap[1][x-len] = map[0][x];
        }

        for(int y = 0; y < N; ++y) {
            map[y] = tmpMap[y].clone();
        }
    }

    public static void resetCntFish() {
        int[][] tmpMap = new int[N][N];

        for(int y = 0; y < N; ++y) {
            if(map[y][0] == 0) break;
            for(int x = 0; x < N; ++x) {
                if(map[y][x] == 0) break;
                for(int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] == 0 || map[y][x] <= map[ny][nx]) continue;

                    int diff = (map[y][x] - map[ny][nx]) / 5;
                    if(diff <= 0) continue;

                    tmpMap[y][x] -= diff;
                    tmpMap[ny][nx] += diff;
                }
            }
        }

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x){
                map[y][x] += tmpMap[y][x];
            }
        }
    }

    public static boolean stackFishbowl(int len) {
        int[][] tmpMap = new int[N][N];
        List<Integer> tmpList = new ArrayList<>();

        int lenY = -1;
        int lenX = len;

        for(int x = 0; x < len; ++x) {
            if(map[0][x] == 0) break;
            for(int y = 0; y < N; ++y) {
                if(map[y][x] == 0) {
                    lenY = y;
                    break;
                }
                tmpList.add(map[y][x]);
            }
        }

        int idx = 0;
        outer:
        for(int y = 0; y < lenX; ++y) {
            for(int x = lenY - 1; x >= 0; x--) {
                tmpMap[y][x] = tmpList.get(idx++);
                if(idx >= tmpList.size()) break outer;
            }
        }

        for(int x = lenX, nx = 0; x < N; ++x, ++nx) {
            if(lenY > lenX) tmpMap[lenY-1][nx] = map[lenY-1][x];
            else tmpMap[lenY][nx] = map[lenY-1][x];
        }

        if(tmpMap[lenX][lenY-1] == 0) return false;

        for(int y = 0; y < N; ++y) {
            map[y] = tmpMap[y].clone();
        }

        return true;
    }

    public static void addFish() {
        int cnt = Integer.MAX_VALUE;
        List<Integer> idxList = new ArrayList<>();

        for(int idx = 0; idx < N; ++idx) {
            if(cnt > map[0][idx]) {
                cnt = map[0][idx];
                idxList.clear();
                idxList.add(idx);
            } else if(cnt == map[0][idx]) {
                idxList.add(idx);
            }
        }

        for(int idx : idxList) {
            map[0][idx]++;
        }
    }

    public static void setLine() {
        List<Integer> tmpList = new ArrayList<>();
        int[][] tmpMap = new int[N][N];

        for(int x = 0; x < N; ++x) {
            for(int y = N-1; y >= 0; --y) {
                if(map[y][x] == 0) continue;
                tmpList.add(map[y][x]);
            }
        }

        for(int y = 0; y < N; ++y) {
            map[y] = new int[N];
            if(y == 0) {
                for(int x = 0; x < N; ++x) {
                    map[y][x] = tmpList.get(x);
                }
            }
        }
    }

    public static boolean findFish() {
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;

        for(int x = 0; x < N; ++x) {
            minVal = Math.min(minVal, map[0][x]);
            maxVal = Math.max(maxVal, map[0][x]);
        }

        if(maxVal-minVal <= K) return true;
        return false;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print(int[][] map) {
        System.out.println("==================");
        for(int y = 0; y < N; ++y) {
            System.out.println(Arrays.toString(map[y]));
        }
        System.out.println("==================");
    }

    public static String input = "8 7\n" +
            "5 2 3 14 9 2 11 8";
}
