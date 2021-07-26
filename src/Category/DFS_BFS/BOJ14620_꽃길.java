package Category.DFS_BFS;

import java.util.*;
import java.io.*;

public class BOJ14620_꽃길 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, ans;
    private static int[][] costMap;
    private static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        ans = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        costMap = new int[N][N];
        visited = new int[N][N];
        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                costMap[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차원 배열 조합
        combination(3, new int[3], N);
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            boolean[][] tmpMap = new boolean[N][N];
            int sum = 0;
            for(int num : selected){
                int y = num / N;
                int x = num % N;
                tmpMap[y][x] = true;
                sum += costMap[y][x];
                for(int d = 0; d < 4; ++d){
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(tmpMap[ny][nx]) return;
                    sum += costMap[ny][nx];
                    tmpMap[ny][nx] = true;
                }
            }
            ans = Math.min(ans, sum);
            return;
        }

        // 0 ~ N-1 까지를 선형이라 생각한다.
        // i= N 부터(1행 제외) N * N - N 까지(마지막 행 제외)
        outer:
        for (int i = startIdx; i < N * N - N; ++i) {
            if (i % N == 0 || i % N == N - 1) continue; // 첫 번째 + 마지막 열 제외
            int y = i / N; // 행
            int x = i % N; // 열

            // 씨앗이 서로 인접해있다면 굳이 볼 필요 없음
            for (int d = 0; d < 4; ++d) {
                int ny = y + dy[d];
                int nx = x + dx[d];
                if (visited[ny][nx] == 1) continue outer;
            }

            visited[y][x] = 1; // 씨앗 선택
            selected[selected.length - cnt] = i; // 씨앗 위치 저장
            combination(cnt - 1, selected, i + 1); // dfs
            visited[y][x] = 0; // 씨앗 선택 해제
        }
    }
}
