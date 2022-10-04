package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_경사로 {
    private static int N, L;
    private static int ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        search();
        System.out.println(ans);
    }

    public static void search() {
        findRow();
        findCol();
    }

    public static void findRow() {

        outer:
        for(int y = 0; y < N; ++y){

            int cnt = 1;
            int baseHeight = map[y][0];

            boolean isDown = false;

            for(int x = 1; x < N; ++x){
                if(map[y][x] == baseHeight) {
                    ++cnt;
                    continue;
                }

                if(map[y][x] == baseHeight + 1) {
                    if(isDown){
                        if(cnt < 2*L) continue outer;
                    } else {
                        if(cnt < L) continue outer;
                    }

                    isDown = false;

                } else if(map[y][x] == baseHeight - 1) {
                    if(isDown){
                        if(cnt < L) continue outer;
                    }

                    isDown = true;

                } else continue outer;

                cnt = 1;
                baseHeight = map[y][x];
            }

            if(isDown && cnt < L) continue outer;

            ++ans;
        }
    }

    public static void findCol() {

        outer:
        for(int x = 0; x < N; ++x){

            int cnt = 1;
            int baseHeight = map[0][x];

            boolean isDown = false;

            for(int y = 1; y < N; ++y){
                if(map[y][x] == baseHeight) {
                    ++cnt;
                    continue;
                }

                if(map[y][x] == baseHeight + 1) {
                    if(isDown){
                        if(cnt < 2*L) continue outer;
                    } else {
                        if(cnt < L) continue outer;
                    }

                    isDown = false;

                } else if(map[y][x] == baseHeight - 1) {
                    if(isDown){
                        if(cnt < L) continue outer;
                    }

                    isDown = true;

                } else continue outer;

                cnt = 1;
                baseHeight = map[y][x];
            }

            if(isDown && cnt < L) continue outer;
            ++ans;
        }
    }
}
