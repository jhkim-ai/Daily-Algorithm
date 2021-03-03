package Baekjoon;

import java.io.*;
import java.util.*;

public class Baekjoon16935 {

    static int N, M, R;
    static int K;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        // ------------- 데이터 입력(전처리) ------------- //

        // 디버깅을 편하게 하기 위한 입력 방식
        BufferedReader br = new BufferedReader(new StringReader(src));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];

        for (int y = 1; y <= N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // ------------- 실행문 ------------- //
        st = new StringTokenizer(br.readLine());
        for (int t = 0; t<R; t++) {
            K = Integer.parseInt(st.nextToken());

            switch (K) {
                // 상하 반전
                case 1:
                    func1();
                    break;
                    
                // 좌우 반전
                case 2:
                    func2();
                    break;
                    
                // 시계방향 90도 회전
                case 3:
                    func3();
                    break;
                    
                // 반시계방향 90도 회전
                case 4:
                    func4();
                    break;
                
                // 4사 분면 분할 후, 시계 방향 회전
                case 5:
                    func5();
                    break;

                // 4사 분면 분할 후, 반시계 방향 회전
                case 6:
                    func6();
                    break;
            }
        }
        // 출력
        print(map);
    }

    static void func1() {
        for (int y = 1; y <= (N + 1) / 2; ++y) {
            int[] tmp = map[y];
            map[y] = map[N + 1 - y];
            map[N + 1 - y] = tmp;
        }
    }

    static void func2() {
        for (int y = 1; y < N + 1; ++y) {
            for (int x = 1; x <= (M + 1) / 2; ++x) {
                int tmp = map[y][x];
                map[y][x] = map[y][M + 1 - x];
                map[y][M + 1 - x] = tmp;
            }
        }
    }

    static void func3() {
        int [][] t = new int[N][];
        int tmp = N;
        N = M;
        M = tmp;

        int[][] copy = new int[N + 1][M + 1];
        for (int y = 1; y <= M; ++y) {
            for (int x = 1; x <= N; ++x) {
                copy[x][M + 1 - y] = map[y][x];
            }
        }
        map = copy;
    }

    static void func4() {

        int tmp = N;
        N = M;
        M = tmp;

        int[][] copy = new int[N + 1][M + 1];
        for (int y = 1; y <= M; ++y) {
            for (int x = 1; x <= N; ++x) {
                copy[N + 1 - x][y] = map[y][x];
            }
        }
        map = copy;
    }

    static void func5() {
        int[][] tmp = new int[(N / 2) + 1][(M / 2) + 1];

        // tmp에 1사분면을 저장
        for (int i = 1; i <= N / 2; ++i) {
            for (int j = 1; j <= M / 2; ++j) {
                tmp[i][j] = map[i][j];
            }
        }

        // delta 를 이용한 사분면의 위치 선정
        // ( N/2 ) X ( M/2 ) 인 1사 분면의 데이터는
        // 가로로 M/2 만큼, 세로로 N/2 만큼의 거리를 두고 있는 것을 이용
        int[] dy = {0, 1, 1, 0}; // 사분면 순서 : 1, 4, 3, 2
        int[] dx = {0, 0, 1, 1};

        for (int d = 0; d < 3; ++d) {
            for (int y = 1; y <= (N + 1) / 2; ++y) {
                for (int x = 1; x <= (M + 1) / 2; ++x) {

                    // 바뀌기 전 위치의 데이터
                    int beforeY = y + ((N + 1) / 2) * dy[d + 1];
                    int beforeX = x + ((M + 1) / 2) * dx[d + 1];

                    // 바뀐 후 위치의 데이터
                    int afterY = y + ((N + 1) / 2) * dy[d];
                    int afterX = x + ((M + 1) / 2) * dx[d];

                    map[afterY][afterX] = map[beforeY][beforeX];
                }
            }
        }

        // 첫 위치의 데이터 (1사 분면) -> (2사 분면) 으로 이동
        for (int y = 1; y <= (N + 1) / 2; ++y) {
            for (int x = 1; x <= (M + 1) / 2; ++x) {
                map[y][x + ((M + 1) / 2)] = tmp[y][x];
            }
        }
    }

    static void func6() {

        int[][] tmp = new int[(N + 1) / 2 + 1][(M + 1) / 2 + 1];
        for (int y = 1; y <= N / 2; ++y) {
            for (int x = 1; x <= M / 2; ++x) {
                tmp[y][x] = map[y][x];
            }
        }

        // delta 를 이용한 사분면의 위치 선정
        // ( N/2 ) X ( M/2 ) 인 1사 분면의 데이터는
        // 가로로 M/2 만큼, 세로로 N/2 만큼의 거리를 두고 있는 것을 이용

        int[] dy = {0, 0, 1, 1}; // 1, 2, 3, 4
        int[] dx = {0, 1, 1, 0};
        for (int d = 0; d < 3; ++d) {
            for (int y = 1; y <= (N + 1) / 2; ++y) {
                for (int x = 1; x <= (M + 1) / 2; ++x) {

                    // 바뀌기 전 위치의 데이터
                    int beforeY = y + ((N + 1) / 2) * dy[d+1];
                    int beforeX = x + ((M + 1) / 2) * dx[d+1];

                    // 바뀐 후 위치의 데이터
                    int afterY = y + ((N + 1) / 2) * dy[d];
                    int afterX = x + ((M + 1) / 2) * dx[d];

                    map[afterY][afterX] = map[beforeY][beforeX];
                }
            }
        }

        // 첫 위치의 데이터 (1사 분면) -> (4사 분면) 으로 이동
        for (int y = 1; y <= N / 2; ++y) {
            for (int x = 1; x <= M / 2; ++x) {
                map[y+(N/2)][x] = tmp[y][x];
            }
        }
    }
    
    // 출력 함수
    static void print(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y < map.length; ++y) {
            for (int x = 1; x < map[0].length; ++x) {
                sb.append(map[y][x]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 디버깅을 위한 Input
    static String src = "6 8 1\n" +
            "3 2 6 3 1 2 9 7\n" +
            "9 7 8 2 1 4 5 3\n" +
            "5 9 2 1 9 6 1 8\n" +
            "2 1 3 8 6 3 9 2\n" +
            "1 3 2 8 7 9 2 1\n" +
            "4 5 1 9 8 2 1 3\n" +
            "3";
}
