package StepByStep._2022._22_01_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16967_배열복원하기 {

    private static int H, W;
    private static int X, Y;
    private static int[][] arrB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        arrB = new int[H+X][W+Y];

        for(int y = 0; y < H+X; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < W+Y; ++x){
                arrB[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        getOriginArr();
        print();
    }

    public static void getOriginArr(){
        for(int y = 0; y < H; ++y){
            for(int x = 0; x < W; ++x){
                arrB[y+X][x+Y] -= arrB[y][x];
            }
        }
    }

    public static void print(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < H; ++y){
            for(int x= 0; x < W; ++x){
                sb.append(arrB[y][x]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
