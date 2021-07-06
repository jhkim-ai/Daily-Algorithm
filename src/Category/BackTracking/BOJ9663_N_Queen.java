package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ9663_N_Queen {

    private static final int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] dx = {0, 0, -1, 1, 1, -1, 1, -1};

    private static int N, ans;
    private static int[][] map;
    private static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];     // debugging 용 : 퀸이 놓여있는 자리를 확인
        visited = new int[N][N]; // 퀸이 지나갈 수 있는 곳을 방문 표시

        // 조합을 통하여 경우의 수 구하기
        combination(N, 0);

        // 정답 출력
        System.out.println(ans);
    }

    // 조합(dfs)
    public static void combination(int cnt, int startIdx) {

        // N개의 Queen 이 규칙에 맞게 놓인 경우
        if (cnt == 0) {
            // print(); // debugging 용
            ans++;
            return;
        }

        // N*N 개 중 N 개를 선택
        for (int i = startIdx; i < N * N; ++i) {
            int y = i / N; // 행
            int x = i % N; // 열
            if(visited[y][x] != 0) continue; // 퀸이 지나갈 수 있는 곳이라면, Pass
            setVisited(y, x, cnt); // 퀸이 놓을 수 있는 자리라면, 퀸이 지나갈 수 있는 모든 곳 방문 표시
            combination(cnt - 1, i + 1); // 다음 퀸이 놓을 자리를 구하기
            unSetVisited(y, x, cnt); // 다른 조합에 사용되어야하기 때문에, 이미했던 경우의 수는 방문표시를 되돌려 놓는다.
        }
    }

    // 방문 표시 : 퀸이 지나갈 수 있는 곳을 방문 표시한다.
    public static void setVisited(int y, int x, int num) {
        // 8방 check
        for (int d = 0; d < 8; ++d) {
            int ny = y;
            int nx = x;
            while (isIn(ny, nx)) {
                if (visited[ny][nx] == 0) visited[ny][nx] = num;
                ny += dy[d];
                nx += dx[d];
            }
        }
    }

    // 방문 표시 해제 : 이미 시행한 경우의 수가 다음 경우의 수에 영향을 미치면 안되기 때문에
    //                시행했던 경우의 수가 방문한 자리를 되돌려 놓는다(초기화 한다).
    public static void unSetVisited(int y, int x, int num) {
        for (int d = 0; d < 8; ++d) {
            int ny = y;
            int nx = x;
            while (isIn(ny, nx)) {
                if (visited[ny][nx] == num) visited[ny][nx] = 0;
                ny += dy[d];
                nx += dx[d];
            }
        }
    }

    // 배열의 유효 범위 체크
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    // Debugging 용 함수 (퀸이 놓여있는 자리 확인)
    public static void print(){
        for(int y = 0; y < N; ++y){
            for(int x = 0; x <N; ++x){
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("============");
    }
}
