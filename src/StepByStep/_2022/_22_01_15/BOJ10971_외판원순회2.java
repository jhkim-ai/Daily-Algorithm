package StepByStep._2022._22_01_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10971_외판원순회2 {

    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        N = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 알고리즘 시작
        permutation(N, new int[N], new boolean[N]); // 완전탐색 -> 순열
        System.out.println(ans);
    }

    public static void permutation(int cnt, int[] selected, boolean[] visited){
        if(cnt == 0){
            int sum = getCost(selected); // 나열된 배열 순서대로 경로 및 비용 탐색
            if(sum == -1){ // 길이 없다면 다음 순열로 pass
                return;
            }
            int lastRoute = map[selected[selected.length-1]][selected[0]]; // 마지막 되돌아오는 경로의 비용
            if(lastRoute != 0){ // 처음 시작점으로 되돌아 오는 경로가 있다면 비용 추가 및 최소 비용 비교
                sum += lastRoute;
                ans = Math.min(sum, ans);
            }
            return;
        }

        // 순열 공식
        for(int i = 0; i < N; ++i){
            if(!visited[i]){
                visited[i] = true;
                selected[selected.length - cnt] = i;
                permutation(cnt-1, selected, visited);
                visited[i] = false;
            }
        }
    }

    // 경로에 대한 비용 구하기
    public static int getCost(int[] selected){
        int sum = 0;
        for(int idx = 1; idx < selected.length; ++idx){
            int dist = map[selected[idx-1]][selected[idx]];
            if(dist == 0){
                return -1;
            }
            sum += dist;
        }
        return sum;
    }
}
