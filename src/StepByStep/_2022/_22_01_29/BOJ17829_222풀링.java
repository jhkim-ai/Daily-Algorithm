package StepByStep._2022._22_01_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17829_222풀링 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = pulling(map, N);
        System.out.println(ans);
    }

    public static int pulling(int[][] map, int len){
        if(len == 1){
            return map[0][0];
        }

        int[][] tmp = downSize(map, len);

        return pulling(tmp, len/2);
    }

    public static int[][] downSize(int[][] map, int len){
        int nLen = len/2;
        int[][] tmp = new int[nLen][nLen];
        List<Integer> list = new ArrayList<>();

        for(int y = 0; y < len; y += 2){
            for(int x = 0; x < len; x += 2){
                list.clear();
                for(int ny = x; ny < x+2; ++ny){
                    for(int nx = y; nx < y+2; ++nx){
                       list.add(map[ny][nx]);
                    }
                }
                Collections.sort(list);
                tmp[y/2][x/2] = list.get(2);
            }
        }
        return tmp;
    }
}
