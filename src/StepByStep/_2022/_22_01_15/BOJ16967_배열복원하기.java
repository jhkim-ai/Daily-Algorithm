package StepByStep._2022._22_01_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16967_배열복원하기 {

    private static int H, W;
    private static int X, Y;
    private static int[][] arrA;
    private static int[][] arrB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        int row = H + X;
        int col = W + Y;

        arrB = new int[row][col];

        for (int y = 0; y < row; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < col; ++x) {
                arrB[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        arrA = getOriginArr(row, col);

        for (int y = 0; y < H; ++y) {
            for (int x = 0; x < W; ++x) {
                sb.append(arrA[y][x]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[][] getOriginArr(int row, int col) {
        int[][] tmp = new int[row][];
        for(int y = 0; y < row; ++y){
            tmp[y] = arrB[y].clone();
        }

        for (int y = X; y < row; ++y) {
            for (int x = Y; x < col; ++x) {
                tmp[y][x] -= arrB[y - X][x - Y];
            }
        }
        return tmp;
    }
}
