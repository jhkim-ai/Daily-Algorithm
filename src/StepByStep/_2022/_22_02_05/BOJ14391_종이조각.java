package StepByStep._2022._22_02_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ14391_종이조각 {

    private static int N, M, ans;
    private static int[] sizes;
    private static char[][] map;
    private static boolean[][] visited;
    private static StringBuilder sb;

    private static Stack<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sizes = new int[N+M-1];
        map = new char[N][M];
        visited = new boolean[N][M];
        sb = new StringBuilder();
        ans = Integer.MIN_VALUE;
        list = new Stack<>();
        for(int y = 0; y < N; ++y){
            map[y] = br.readLine().toCharArray();
        }

        for(int i = 0; i < M; ++i){
            sizes[i] = i+1;
        }
        for(int i = 1; i < N; ++i){
            sizes[M+i-1] = -(i+1);
        }
//        System.out.println(Arrays.toString(sizes));
        dfs(0, 0, 0);
        System.out.println(ans);
    }

    public static void dfs(int y, int x, int sum){
        if(y == N-1 && x == M){
//            System.out.println(list);
//            System.out.println("=================================");
            ans = Math.max(ans, sum);
            return;
        }
        if(x == M){
            dfs(y+1, 0, sum);
            return;
        }
        if(!visited[y][x]) {
            for (int i = 0; i < sizes.length; ++i) {
                if (isAttach(y, x, sizes[i])) {
                    int num = attach(y, x, sizes[i], true); // 붙이기
                    list.push(num);
                    dfs(y, x+1, sum+num);
                    list.pop();
                    attach(y, x, sizes[i], false); // 떼기
                }
            }
        } else {
            dfs(y, x+1, sum);
        }
    }

    // flag = true --> 붙이기
    // flag = false --> 떼기
    public static int attach(int y, int x, int d, boolean flag){
        sb.setLength(0);
        if(d > 0){ // 가로로 붙이기
            for(int nx = x; nx < x + d; ++nx){
                visited[y][nx] = flag;
                sb.append(map[y][nx]);
            }
        } else { // 세로로 붙이기
            d = -d;
            for(int ny = y; ny < y + d; ++ny){
                visited[ny][x] = flag;
                sb.append(map[ny][x]);
            }
        }
        return Integer.parseInt(sb.toString());
    }

    public static boolean isAttach(int y, int x, int d){
        if(d > 0){ // 가로로 붙이기
            for(int nx = x; nx < x + d; ++nx){
                if(!isIn(y, nx) || visited[y][nx]){
                    return false;
                }
            }
        } else { // 세로로 붙이기
            d = -d;
            for(int ny = y; ny < y + d; ++ny){
                if(!isIn(ny, x) || visited[ny][x]){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

}
