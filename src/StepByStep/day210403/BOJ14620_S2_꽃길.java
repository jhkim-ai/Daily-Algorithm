package StepByStep.day210403;

import java.util.*;
import java.io.*;

public class BOJ14620_S2_꽃길 {

    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int ans = Integer.MAX_VALUE;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combination(3, new Point[3], 1, 1);
        System.out.println(ans);
    }

    // 조합
    static void combination(int cnt, Point[] selected, int startY, int startX) {
        // 기저조건
        if (cnt == 0) {
            visited = new boolean[N][N];

            for (int i = 0; i < 3; i++) {   // 3개의 꽃
                Point p = selected[i];      // 씨앗 위치
                visited[p.y][p.x] = true;
                for (int d = 0; d < 4; d++) { // 씨앗 주변으로 4방 방문
                    int ny = p.y + dy[d];
                    int nx = p.x + dx[d];
                    if(visited[ny][nx]) {      // 이미 방문됐다면 return (겹침)
                        visited = new boolean[N][N];
                        return;
                    }
                    visited[ny][nx] = true;
                }
            }
            
            int sum = 0;    // cost 값
            for (int y = 0; y < N; y++) {
                for (int x = 0; x <N; x++) {
                    if(visited[y][x])   // 꽃잎의 위치만 비용표시
                        sum += map[y][x];
                }
            }

            // print(sum); // 디버깅용
            ans = Math.min(sum, ans);
            return;
        }

        // 다음 조합
        for (int y = startY; y < N - 1; y++) {
            for (int x = startX; x < N - 1; x++) {
                selected[selected.length - cnt] = new Point(y, x);
                if(x == N-2)
                    combination(cnt-1, selected, y+1, 1);
                else
                    combination(cnt-1, selected, y, x+1);
            }
        }
    }
    
    // 디버깅 용
    static void print(int sum){
        System.out.println(sum + "===============");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(visited[i][j])
                    System.out.print(3+" ");
                else
                    System.out.print(0+ " ");
            }
            System.out.println();
        }
    }

    static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
