package Category.DFS_BFS;

import java.util.*;
import java.io.*;

public class BOJ1405_미친로봇 {

    private static final int[] dy = {0, 0, -1, 1};
    private static final int[] dx = {1, -1, 0, 0};

    private static int N;
    private static double result;
    private static double[] arr;
    private static int[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        N = Integer.parseInt(st.nextToken());
        arr = new double[4];
        for(int i = 0; i < 4; ++i){
            arr[i] = Double.parseDouble(st.nextToken())/100;
        }

        // 임의의 map 최대 크기로 만듦
        visited = new int[31][31];
        int x = 15;
        int y = 15;
        visited[y][x] = 1;

        // 중복순열
        duplicatedPermutation(N, new double[N], y, x, 1.);

        // 확률 구하기 : 각 경우의 수들의 "확률 곱"들을 "모두 합"한다.
        System.out.println(result);
    }

    // per : 하나의 경우의 수에 존재하는 각 방향들의 확률을 곱해 나아간다.
    public static void duplicatedPermutation(int cnt, double[] selected, int y, int x, double per){
        if(cnt == 0){
            // result : 모든 경우의 수들의 확률 합
            result += per;
            return;
        }

        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(visited[ny][nx] == 1) continue; // 단순한 이동겨리가 아닌 경우 (이미 방문된 곳을 지남)
            visited[ny][nx] = 1;
            duplicatedPermutation(cnt-1, selected, ny, nx, per*arr[d]);
            visited[ny][nx] = 0;
        }
    }
}
