package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1974_스도쿠검증 {

    static int T;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // T = Integer.parseInt(br.readLine());
        arr = new int[9][9];

        for (int i = 0; i < 9; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; ++j) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        boolean isRight = true;
        all:
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; j++) {
                int num = arr[i][j];
                // 가로 탐색
                int cnt = 0;
                for (int x = 0; x < 9; x++) {
                    if (cnt == 2) {
                        isRight = false;
                        break all;
                    }
                    if (arr[i][x] == num)
                        cnt++;
                }

                // 세로 탐색
                cnt = 0;
                for (int y = 0; y < 9; y++) {
                    if (cnt == 2) {
                        isRight = false;
                        break all;
                    }
                    if (arr[y][j] == num)
                        cnt++;
                }

                // 3X3 탐색
                cnt = 0;
                int y = i % 3;
                int x = j % 3;
                for (int k = 0, dy = y*3; k<3; k++){
                    for (int l = 0, dx = x*3; l<3; l++){
                        if (cnt == 2) {
                            isRight = false;
                            break all;
                        }
                        if(arr[dy+k][dx+l] == num) {
                            cnt++;
                        }
                    }
                }

            }
        }

        if(isRight)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
