package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ20058_마법사상어와파이어스톰 {

    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static int N, Q, command, L, ans, count, idx;
    public static int[] commands;
    public static boolean[][] visited;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        Q = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];
        commands = new int[Q];
        ans = Integer.MIN_VALUE;

        // map 입력
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령어 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        // 명령어만큼 실행
        for (int c : commands) {
            idx = 0;
            command = c; // 명령어 저장
            fireStorm(); // 파이어스톰 실행
            searchIce(); // 4방향 중 인접한 칸의 얼음이 2개 이하면, -1
        }

        // 남은 얼음의 총 개수
        int totalIce = getTotalIce();
        sb.append(totalIce).append("\n");

        // 연결된 얼음 덩어리 중 가장 큰 덩어리 찾기
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(visited[y][x] || map[y][x] == 0) continue;
                visited[y][x] = true;
                count = 0;
                getPartition(y, x);
                ans = Math.max(count, ans);
            }
        }
        sb.append(ans);

        System.out.println(sb);
    }

    // FireStorm 실행
    public static void fireStorm() {
        L = (int) Math.pow(2, command); // 분할 Size 구하기
        divide(N, 0, 0);          // 1. 분할 시작
    }

    // 1. 분할
    public static void divide(int num, int y, int x) {

        // 1-2. 분할 Size 에 도달 시, 작동
        if (num == L) {
            // 2. 행렬 회전
            circulation(y, x);
            return;
        }

        // 1-1. 분할
        int n = num / 2;
        divide(n, y, x);
        divide(n, y + n, x);
        divide(n, y, x + n);
        divide(n, y + n, x + n);
    }

    // 2. 행렬 회전 : 각 분할된 부분행렬들을 명령어에 맞게 회전
    public static void circulation(int y, int x) {
        List<Integer>[] arrList = new ArrayList[L];

        // ArrayList 배열 초기화
        for (int i = 0; i < L; ++i) {
            arrList[i] = new ArrayList<>();
        }

        // 열을 배열에 저장
        int idx = 0;
        for (int nx = x; nx < x + L; nx++) {
            for (int ny = y; ny < y + L; ny++) {
                arrList[idx].add(map[ny][nx]);
            }
            ++idx;
        }

        // 재배열
        int i = 0;
        for (int ny = y; ny < y + L; ny++) {
            int j = L - 1;
            for (int nx = x; nx < x + L; nx++) {
                map[ny][nx] = arrList[i].get(j);
                --j;
            }
            ++i;
        }
    }

    // 3. 감소할 얼음 찾기 : 얼음이 2칸 이하로 인접해있다면, -1 한다.
    public static void searchIce(){
        // 앞에 것이 0이 되면, 뒤에도 영향을 미치는 것이 아님
        // 현재 순간을 기준으로 해야한다. => queue 로 관리
        Queue<int[]> q = new LinkedList<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int noIce = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] == 0){
                        noIce++;
                        continue;
                    }
                }
                if(noIce >= 2) q.offer(new int[]{y, x});
            }
        }

        while(!q.isEmpty()){
            int[] now = q.poll();
            if(map[now[0]][now[1]] == 0) continue;
            map[now[0]][now[1]]--;
        }
    }

    // 4. 남은 얼음의 총 개수
    public static int getTotalIce(){
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(map[y][x] > 0) sum += map[y][x];
            }
        }
        return sum;
    }

    // 5. 남은 얼음에서 연결된 것 중, 가장 큰 얼음 덩어리 (dfs)
    public static void getPartition(int y, int x){
        count++;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0) continue;
            visited[ny][nx] = true;
            getPartition(ny, nx);
        }
    }

    // 배열 범위 확인
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print() {
        System.out.println("==============");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
