package SWEA;

import java.util.*;
import java.io.*;

public class SWEA4014_활주로건설 {

    static int N, X, ans;
    static int[][] map, transposedMap;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new int[N][N];
            transposedMap = new int[N][N];

            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < N; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    transposedMap[y][x] = map[x][y];
                }
            }

            // 1. 가로 검사
            check(map);
            System.out.println("============");
            // 2. 세로 검사
            check(transposedMap);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    // 행만큼 실행
    static void check(int[][] arr) {
        all:
        for (int y = 0; y < N; y++) {
            int sameHeight = 1;

            rowCheck:
            for (int x = 1; x < N; x++) {
                // 높이가 같을 때 : pass (개수증가)
                if (arr[y][x] == arr[y][x - 1]) {
                    sameHeight++;
                    continue;
                }

                // 높이가 다를 때 : 2가지 check
                if (arr[y][x] != arr[y][x - 1]) {
                    // (1) 올라가는 경사로
                    if (arr[y][x] == arr[y][x - 1] + 1) {
                        if (sameHeight >= X) {
                            System.out.println("올라가는="+y+":"+x);
                            ans++;
                            break;
                        }
                        sameHeight = 1;
                    }
                    // (2) 내려가는 경사로
                    else if (arr[y][x] == arr[y][x - 1] - 1) {
                        sameHeight = 1;
                        int possibleHeight = arr[y][x];

                        // (2)-1 연속되는 수 확인
                        for (int i = x + 1; i < N; i++) {
                            // (2)-2 연속되는 수일 경우
                            if(arr[y][i] == possibleHeight){
                                sameHeight++;
                                continue;
                            }
                            // (2)-3 연속되는 수가 아니라면
                            if(arr[y][i] != possibleHeight) {
                                // (3)-1. 이전에 연속되는 수가 X개 이상이면, 활주로 가능
                                if (sameHeight >= X) {
                                    ans++;
                                    System.out.println("내려가는="+y+":"+x);
                                    break rowCheck;
                                }
                                // (2)-3. 연속되는 수가 X개 미만이면, 그 행을 계속 탐색
                                sameHeight = 1;
                                x = i;
                                break;
                            }
                            // x = 4
                            // 3 3 2 2 2 3 2 2 2 2
                        }
                    }

                    // (3) 같은 높이면, sameHeight 증가
                    else if(arr[y][x] == arr[y][x-1])
                        sameHeight++;
                }
            }
            if(sameHeight == N) {
                System.out.println("평지" + y);
                ans++;
            }
        }
    }
}
