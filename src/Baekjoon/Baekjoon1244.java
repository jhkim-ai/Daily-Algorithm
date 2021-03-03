package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon1244 {

    static int T, N;
    static int[] arr;
    static int[][] player;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        arr = new int[T + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < T + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        N = Integer.parseInt(br.readLine());
        player = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            player[i][0] = Integer.parseInt(st.nextToken());
            player[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; i++) {
            int num = player[i][1];

            switch (player[i][0]) {
                case 1:
                    for (int j = num; j < T + 1; j += num) {
                        if (1 <= j && j < T + 1) {
                            arr[j] = arr[j] == 0 ? 1 : 0;
                        }
                    }
                    break;
                case 2:
                    int left = num - 1;
                    int right = num + 1;
                    arr[num] = arr[num] == 0 ? 1 : 0;
                    if(0 < left && right < T + 1) {
                        if (arr[left] != arr[right]) {
                            break;
                        } else {
                            while (0 < left && right < T + 1) {
                                if (arr[left] != arr[right]) break;
                                else {
                                    arr[left] = arr[left] == 0 ? 1 : 0;
                                    arr[right] = arr[right] == 0 ? 1 : 0;
                                    left--;
                                    right++;
                                }
                            }
                        }

                        break;
                    }
            }
        }
        for (int idx = 1; idx < arr.length; idx++) {

            if (idx % 20 == 0) {
                System.out.println(arr[idx]);
                continue;
            }
            System.out.print(arr[idx] + " ");
        }
    }
}


