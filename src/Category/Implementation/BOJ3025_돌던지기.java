package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3025_돌던지기 {

    private static int R, C, N;
    private static int[] col;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        col = new int[C];
        Arrays.fill(col, -1);

        for (int y = 0; y < R; ++y) {
            String str = br.readLine();
            for (int x = 0; x < C; ++x) {
                map[y][x] = str.charAt(x);
                if (map[y][x] == 'X') {
                    col[x] = y;
                }
            }
        }
        N = Integer.parseInt(br.readLine());

        System.out.println(Arrays.toString(col));

        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine()) - 1;
            drop(num);
            print();
        }
    }

    public static void drop(int num) {
        while(true) {
            if (col[num] == -1) {
                col[num] = R - 1;
                map[col[num]][num] = 'O';
                break;
            } else {
                if (checkLeft(col[num] - 1, num)) {
                    col[num - 1] = col[num];
                    map[num][num - 1] = 'O';
                } else if (checkRight(col[num] - 1, num)) {
                    col[num + 1] = col[num];
                    map[num][num + 1] = 'O';
                } else {
                    map[num - 1][num] = 'O';
                    col[num]--;
                    break;
                }
            }
        }
    }

    public static boolean checkLeft(int y, int x){
        int ny = y;
        int nx = x - 1;
        if(!checkSliding(ny, nx)){
            return false;
        }

        ny += 1;
        if(!checkSliding(ny, nx)){
            return false;
        };

        return true;
    }

    public static boolean checkRight(int y, int x){
        int ny = y;
        int nx = x + 1;

        if(!checkSliding(ny, nx)){
            return false;
        }

        ny += 1;
        if(!checkSliding(ny, nx)){
            return false;
        };

        return true;
    }


    public static boolean checkSliding(int y, int x){
        if(!isIn(y, x) || map[y][x] != '.'){
            return false;
        }
        return true;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < R && x < C;
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < R; ++y) {
            for (int x = 0; x < C; ++x) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
