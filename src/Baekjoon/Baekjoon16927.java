package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 배열돌리기2
public class Baekjoon16927 {

    static int N, M, R;
    static int[][] map;

    public static void main(String[] args) throws Exception {

        // ---------------- 데이터 입력(전처리) ---------------- //

        // Tip. 고정된 BufferedReader 를 받기 위하여 String 으로 읽음
        BufferedReader br = new BufferedReader(new StringReader(src));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        // ---------------- 알고리즘 ---------------- //

        // 문제 조건 : M, N 중 작은 값(min_size)은 짝수
        // Point : ( min_size / 2 ) 개수만큼 회전할 사각형이 만들어진다.

        int min_size = Math.min(N, M);

        // 각 사각형의 원소 개수만큼 회전할 시, 원래 상태
        // 따라서, %(modulo) 연산을 이용하여 불필요한 회전을 줄임.

        int[] cnt = new int[min_size / 2];              // 사각형의 개수만큼 배열을 만듦.
        for (int n = 0; n < cnt.length; ++n) {          // 사각형마다 원소의 개수를
            cnt[n] = (2 * N + 2 * M - 4 - 8 * n) % R;   // 회전 횟수(R)로 나누어 나머지를 구함
        }

        // 회전할 사각형의 개수만큼 반복 시행.
        for (int n = 0; n < min_size / 2; ++n) {
            for (int r = 0; r < cnt.length; r++) {

                // (n,n)의 좌표를 다른 곳에 저장시킨 후, 이동 시작.
                int tmp = map[0 + n][0 + n];

                // 첫 시작 좌표
                int y = n;
                int x = n;

                // 이동 방향 : 1. 윗선 (미는 방향 : "우" -> 좌) 2. 오른선("하"->상)
                //           3. 아랫선 ("좌"->우)            4. 왼선 ("상"->하)

                // deltas : 우, 하, 좌, 상
                int[] dy = {0, 1, 0, -1};
                int[] dx = {1, 0, -1, 0};

                for (int d = 0; d < 4; ++d) {

                    // 다음 좌표
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    // [d] 방향으로 이동한 새 좌표 Valid 검사
                    // 사각형 범위 밖을 나갈 시, 방향체인지

                    // 사각형 범위 : ( 0+n <= ny < N-n)
                    //             ( 0+n <= nx < M-n)
                    while (0 + n <= ny && ny < N - n && 0 + n <= nx && nx < M - n) {
                        map[y][x] = map[ny][nx];

                        // 현 좌표 갱신
                        y = ny;
                        x = nx;

                        // 다음 좌표
                        ny = y + dy[d];
                        nx = x + dx[d];
                    }
                }

                // (n, n+1) 에 다시 배치
                map[n + 1][n] = tmp;
            }
        }
        print();
    }

    // 출력
    static void print() {
        for (int[] arr : map)
            System.out.println(Arrays.toString(arr));
    }

    // Tip. 고정된 BufferedReader 를 받기 위하여 String 으로 읽음
    static String src = "4 4 2\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16";
}
