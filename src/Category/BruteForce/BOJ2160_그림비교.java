package Category.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2160_그림비교 {

    private static int N;
    private static char[][][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][5][7];

        for(int i = 0; i < N; ++i){
            for(int y = 0; y < 5; ++y){
                map[i][y] = br.readLine().toCharArray();
            }
        }

        int cnt = Integer.MAX_VALUE;
        int A = -1;
        int B = -1;
        for(int i = 0; i < N - 1; ++i){
            for(int j = i + 1; j < N; ++j){
                int tmpCnt = 0;
                for(int y = 0; y < 5; ++y){
                    for(int x = 0; x < 7; ++x){
                        if(map[i][y][x] != map[j][y][x]){
                            tmpCnt++;
                        }
                    }
                }
                if(cnt > tmpCnt){
                    cnt = tmpCnt;
                    A = i+1;
                    B = j+1;
                }
            }
        }
        System.out.print(A + " " + B);
    }
}
