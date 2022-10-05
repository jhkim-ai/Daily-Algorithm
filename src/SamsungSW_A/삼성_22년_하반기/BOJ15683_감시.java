package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ15683_감시 {

        private static final int[] dy = {-1, 0, 1, 0};
        private static final int[] dx = {0, 1, 0, -1};

        private static int N, M, ans;
        private static int[][] map;
        private static List<CCTV> cctvList;

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            cctvList = new ArrayList<>();
            ans = Integer.MAX_VALUE;

            for(int y = 0; y < N; ++y) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int x = 0; x < M; ++x) {
                    map[y][x] = Integer.parseInt(st.nextToken());

                    if(map[y][x] == 0 || map[y][x] == 6) continue;
                    cctvList.add(new CCTV(y, x, map[y][x]));
                }
            }
            int size = cctvList.size();
            combination(size, new int[size]);
            System.out.println(ans);
        }

        public static void combination(int cnt, int[] selected) {
            if(cnt == 0) {
                int[][] copyMap = getCopyMap();
                for(int i = 0; i < selected.length; ++i) {
                    CCTV cctv = cctvList.get(i);

                    switch(cctv.no) {
                        case 1:
                            runCCTV1(copyMap, cctv, selected[i]);
                            break;
                        case 2:
                            runCCTV2(copyMap, cctv, selected[i]);
                            break;
                        case 3:
                            runCCTV3(copyMap, cctv, selected[i]);
                            break;
                        case 4:
                            runCCTV4(copyMap, cctv, selected[i]);
                            break;
                        case 5:
                            runCCTV5(copyMap, cctv, selected[i]);
                            break;
                    }
                }
                getAns(copyMap);
                return;
            }

            for(int d = 0; d < 4; ++d) {
                selected[selected.length - cnt] = d;
                combination(cnt-1, selected);
            }
        }

        public static void runCCTV1(int[][] copyMap, CCTV cctv, int dir) {
            int ny = cctv.y;
            int nx = cctv.x;

            while(true){
                ny = ny + dy[dir];
                nx = nx + dx[dir];

                if(!isIn(ny, nx) || isWall(ny, nx)) break;
                if(copyMap[ny][nx] != 0) continue;
                copyMap[ny][nx] = 7;
            }
        }

        public static void runCCTV2(int[][] copyMap, CCTV cctv, int dir) {

            for(int d = 0; d < 3; ++d) {

                if(d == 1) continue;

                int ny = cctv.y;
                int nx = cctv.x;
                int nd = (dir + d) % 4;

                while(true){
                    ny = ny + dy[nd];
                    nx = nx + dx[nd];

                    if(!isIn(ny, nx) || isWall(ny, nx) || map[ny][nx] == 7) break;
                    if(copyMap[ny][nx] != 0) continue;
                    copyMap[ny][nx] = 7;
                }
            }
        }

        public static void runCCTV3(int[][] copyMap, CCTV cctv, int dir) {

            for(int d = 0; d < 2; ++d) {

                int ny = cctv.y;
                int nx = cctv.x;
                int nd = (dir + d) % 4;

                while(true){
                    ny = ny + dy[nd];
                    nx = nx + dx[nd];

                    if(!isIn(ny, nx) || isWall(ny, nx) || map[ny][nx] == 7) break;
                    if(copyMap[ny][nx] != 0) continue;
                    copyMap[ny][nx] = 7;
                }
            }
        }

        public static void runCCTV4(int[][] copyMap, CCTV cctv, int dir) {

            for(int d = 0; d < 4; ++d) {

                if(d == 2) continue;
                int ny = cctv.y;
                int nx = cctv.x;
                int nd = (dir + d) % 4;

                while(true){
                    ny = ny + dy[nd];
                    nx = nx + dx[nd];

                    if(!isIn(ny, nx) || isWall(ny, nx) || map[ny][nx] == 7) break;
                    if(copyMap[ny][nx] != 0) continue;
                    copyMap[ny][nx] = 7;
                }
            }
        }

        public static void runCCTV5(int[][] copyMap, CCTV cctv, int dir) {

            for(int d = 0; d < 4; ++d) {

                int ny = cctv.y;
                int nx = cctv.x;
                int nd = (dir + d) % 4;

                while(true){
                    ny = ny + dy[nd];
                    nx = nx + dx[nd];

                    if(!isIn(ny, nx) || isWall(ny, nx) || map[ny][nx] == 7) break;
                    if(copyMap[ny][nx] != 0) continue;
                    copyMap[ny][nx] = 7;
                }
            }
        }

        public static void getAns(int[][] copyMap) {
            int cnt = 0;
            for(int y = 0; y < N; ++y) {
                for(int x = 0; x < M; ++x) {
                    if(copyMap[y][x] == 0) ++cnt;
                }
            }

            ans = Math.min(ans, cnt);
        }

        public static int[][] getCopyMap() {
            int[][] copyMap = new int[N][];

            for(int y = 0; y < N; ++y) {
                copyMap[y] = map[y].clone();
            }

            return copyMap;
        }

        public static boolean isWall(int y, int x){
            return map[y][x] == 6;
        }

        public static boolean isIn(int y, int x){
            return y >= 0 && x >= 0 && y < N && x < M;
        }

        public static class CCTV {
            int y;
            int x;
            int no;

            public CCTV(int y, int x, int no){
                this.y = y;
                this.x = x;
                this.no = no;
            }
        }

}
