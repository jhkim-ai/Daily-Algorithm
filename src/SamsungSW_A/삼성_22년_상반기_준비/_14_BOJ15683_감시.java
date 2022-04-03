package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _14_BOJ15683_감시 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M, ans;
    private static int cntCCTV;
    private static int[][] map;
    private static List<int[]> listCCTV;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;
        map = new int[N][M];
        listCCTV = new ArrayList<>();

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] != 0 && map[y][x] != 6 && map[y][x] != 5) {
                    cntCCTV++;
                    listCCTV.add(new int[]{y, x, map[y][x]});
                }
            }
        }

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(map[y][x] == 5) no5CCTV(y, x);
            }
        }

        duplPermutation(cntCCTV, new int[cntCCTV]);
        System.out.println(ans);
    }

    public static void duplPermutation(int cnt, int[] selected){
        if(cnt == 0){
            int[][] copyMap = getCopyMap();
            for(int i = 0; i < cntCCTV; ++i){
                int[] cctvInfo = listCCTV.get(i);
                int y = cctvInfo[0];
                int x = cctvInfo[1];
                int no = cctvInfo[2];
                int dir = selected[i];

                if(no == 1) no1CCTV(y, x, dir, copyMap);
                else if(no == 2) no2CCTV(y, x, dir, copyMap);
                else if(no == 3) no3CCTV(y, x, dir, copyMap);
                else if(no == 4) no4CCTV(y, x, dir, copyMap);
            }
            print(copyMap);
            ans = Math.min(ans, getCntArea(copyMap));
            return;
        }

        for(int d = 0; d < 4; ++d){
            selected[selected.length - cnt] = d;
            duplPermutation(cnt-1, selected);
        }
    }

    public static void no1CCTV(int y, int x, int dir, int[][] copyMap){
        cctv(y, x, dir, copyMap);
    }

    public static void no2CCTV(int y, int x, int dir, int[][] copyMap){
        cctv(y, x, dir, copyMap);
        dir = (dir + 2) % 4;
        cctv(y, x, dir, copyMap);
    }

    public static void no3CCTV(int y, int x, int dir, int[][] copyMap){
        cctv(y, x, dir, copyMap);
        dir = (dir + 1) % 4;
        cctv(y, x, dir, copyMap);
    }

    public static void no4CCTV(int y, int x, int dir, int[][] copyMap){
        for(int d = 0; d < 4; ++d){
            if(dir == d) continue;
            cctv(y, x, d, copyMap);
        }
    }

    public static void no5CCTV(int y, int x){
        for(int d = 0; d < 4; ++d){
            cctv(y, x, d, map);
        }
    }

    public static void cctv(int y, int x, int dir, int[][] copyMap){
        while(true){
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if(!isIn(ny, nx) || copyMap[ny][nx] == 6) break;

            if(copyMap[ny][nx] == 0) copyMap[ny][nx] = 8;

            y = ny;
            x = nx;
        }
    }

    public static int getCntArea(int[][] copyMap){
        int cnt = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(copyMap[y][x] == 0) ++cnt;
            }
        }
        return cnt;
    }

    public static int[][] getCopyMap(){
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }
        return copyMap;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static void print(int[][] map){
        System.out.println("==============");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
