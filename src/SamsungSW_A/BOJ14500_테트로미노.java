package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ14500_테트로미노 {

    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, M, ans;
    public static int[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 세로 길이
        M = Integer.parseInt(st.nextToken());   // 가로 길이
        ans = Integer.MIN_VALUE;
        map = new int[N][M];
        visited = new boolean[N][M];

        // map 입력
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // Idea. dfs 를 이용하여 도형을 바꾸어 탐색한다.
        // "주의" => 'ㅏ' 도형을 제외하곤 'ㅣ','ㄱ','ㅁ','ㄹ'의 도형은 dfs 로 회전 or 대칭된 도형을 만들 수 있다.
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                visited[y][x] = true;
                dfs(y, x, 0, map[y][x]);
                exception(y, x, map[y][x]);
                visited[y][x] = false;
            }
        }

        // 정답 출력
        System.out.println(ans);
    }

    // 'ㅏ' 모형을 제외한 모든('ㅣ', 'ㅁ, 'ㄱ', 'ㄹ') 모형 만들기
    public static void dfs(int y, int x, int depth, int sum) {
        if (depth == 3) {
            ans = Math.max(sum, ans); // 최댓값 비교
            return;
        }

        // 4방 탐색
        for (int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            // 범위 밖이거나 이미 채택된 곳이라면 pass
            if(!isIn(ny, nx) || visited[ny][nx]) continue;

            visited[ny][nx] = true;
            dfs(ny, nx, depth+1, sum+map[ny][nx]);
            visited[ny][nx] = false;    // 다음 탐색을 위해 방문 체크 해제
        }
    }

    // '+'의 상,하,좌,우 중 가장 작은 값을 빼자
    public static void exception(int y, int x, int sum){
        int min = Integer.MAX_VALUE;
        int dir = 4;

        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(dir <= 2) return; // 'ㅏ' 모양을 유지하지 않으므로 제외
            if(!isIn(ny, nx)){
                --dir;           // '+' 중 한 곳을 잃음
                continue;
            }

            sum += map[ny][nx];  // 새로운 위치를 더함
            min = Math.min(min, map[ny][nx]); // 최솟값을 갱신한다. (87번 라인을 위함)
        }

        if(dir == 4) sum -= min;  // '+' 모양이면 가장 작은 값을 빼자
        ans = Math.max(sum, ans); // 'ㅏ' 모양과 정답을 비교
    }

    // 범위 유효성 검사
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}