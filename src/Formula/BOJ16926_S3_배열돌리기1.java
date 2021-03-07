package Formula;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ16926_S3_배열돌리기1 {

    static int N, M, R;
    static int[][] map;

    // 좌표를 이동시키는 선의 순서는 (윗선, 오른선, 아랫선, 왼선) 순으로 진행
    // <주의!> 선의 순서와 좌표의 탐색 순서 구분을 확실히 해야 함. (deltas 배열 참고)

    // 예를 들어, 이동시키는 선의 첫 번째 순서는 "윗선"
    // 하지만, 윗선에서의 좌표이동은 오른쪽으로 이동.
    static int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // (우, 하, 좌, 상) 순으로 이동
    public static void main(String[] args) throws Exception {

        // ------------ 데이터 입력(전처리) ------------ //

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(src));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N + 1][M + 1];    // (1,1) 부터 시작하기 위함

        for (int y = 1; y <= N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // ------------ 알고리즘 시작 ------------ //

        // 문제를 구현할 Idea

        // 각 사각형 마다 탐색. 사각형의 개수는 [ Math.min(M,N) / 2 ]
        // 1st. 사각형의 윗선을 왼쪽으로 한 칸씩 이동
        // 2nd. 오른쪽 선을 위쪽으로 한 칸씩 이동
        // 3rd. 아랫선을 오른쪽으로 한 칸씩 이동
        // 4th. 왼쪽 선을 아래쪽으로 한 칸씩 이동

        for (int t = 0; t < R; ++t) {

            // Math.min(M,N)/2 : 회전할 사각형의 개수
            // 회전할 사각형의 첫 시작은 (1,1) -> (2,2) -> ... -> (k,k) 순
            for (int n = 0; n < Math.min(M, N) / 2; n++) {
                
                // (k, k)부터 시작
                int y = 1 + n;
                int x = 1 + n;
                
                // (k,k) Data 를 num 에 저장한 후,
                // (k,k)의 빈 자리를 채우기 위하여 아래의 4단계를 시행
                int num = map[y][x];

                // (우, 하, 좌, 상) 순으로 이동
                for (int d = 0; d < deltas.length; d++) {

                    // ( ny, nx ) 새로운 좌표 탐색
                    int ny = y + deltas[d][0];
                    int nx = x + deltas[d][1];

                    // ( ny, nx ) 새로운 좌표의 Valid Test
                    while (1 + n <= ny && ny <= N - n && 1 + n <= nx && nx <= M - n) {

                        // swap
                        map[y][x] = map[ny][nx];

                        // 다음 좌표로 갱신
                        y = ny;
                        x = nx;

                        // 다음 좌표로 이동
                        ny = y + deltas[d][0];
                        nx = x + deltas[d][1];
                    }

                }
                // 기존에 저장된 num 을 마지막 이동에 입력
                map[2 + n][1 + n] = num;
            }
        }

        // Output
        StringBuilder sb = new StringBuilder();
        for (int y = 1; y <= N; ++y) {
            for (int x = 1; x <= M; ++x) {
                sb.append(map[y][x]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // Input Data
    static String src = "4 4 2\n" +
            "1 2 3 4\n" +
            "5 6 7 8\n" +
            "9 10 11 12\n" +
            "13 14 15 16";
}
